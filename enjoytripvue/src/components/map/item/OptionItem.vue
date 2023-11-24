<script setup>
import { ref, onMounted, watch } from "vue";
import { gugunList, sidoList } from "@/api/map";
import { SearchOutlined } from "@ant-design/icons-vue";
const sidoOption = ref([]);
const attOption = ref([
  { label: "관광지", value: "12" },
  { label: "문화시설", value: "14" },
  { label: "축제, 공연, 행사", value: "15" },
  { label: "레포츠", value: "28" },
  { label: "숙박", value: "32" },
  { label: "쇼핑", value: "38" },
  { label: "음식점", value: "39" },
]);
const attKey = ref(); //attraction key
const CascadeOption = ref([]); // Cascade 창에서 선택한 벨류: [0]에 시도, [1]에 구군
const visible = ref(false);

const toggleVisible = () => {
  visible.value = !visible.value;
};
onMounted(() => {
  getSidoList();
});

function getSidoList() {
  sidoList(
    ({ data }) => {
      //console.log(data);
      let options = [];
      options.push({ text: "시도선택", value: "" });
      data.forEach((item) => {
        //console.log(item.sidoCode, item.sidoName);
        options.push({
          label: item.sidoName,
          value: item.sidoCode,
          isLeaf: false,
        });
      });
      sidoOption.value = options;
      //console.log(res)
    },
    (err) => {
      console.log(err);
    }
  );
}

watch(attKey, () => {
  //CascadeOption.value.push(parseInt(attKey.value));
  CascadeOption.value[2] = attKey.value;
});

const loadData = (selectedOptions) => {
  const targetOption = selectedOptions[selectedOptions.length - 1];
  targetOption.loading = true;
  // load options lazily
  gugunList(
    targetOption.value,
    ({ data }) => {
      targetOption.loading = false;
      let options = [];

      data.forEach((item) => {
        options.push({
          label: `${targetOption.label} ` + item.gugunName,
          value: item.gugunCode,
        });
      });
      targetOption.children = options;
    },
    (err) => {
      console.log(err);
    }
  );
};
</script>
<template>
  <div class="optionWrapper">
    <a-popover v-model:open="visible" trigger="click">
      <template #content>
        <a-flex vertical gap="small">
          <h3 class="popover-title">지역</h3>
          <a-cascader
            v-model:value="CascadeOption"
            :options="sidoOption"
            :load-data="loadData"
            placeholder="검색 시작하기"
            change-on-select
          />
          <h3 class="popover-title">관광타입</h3>
          <a-select
            v-model:value="attKey"
            @change="onSelect"
            :options="attOption"
            class="attOptions"
          />
        </a-flex>
        <a-button @click="$emit('changeOption', CascadeOption)"
        class="select-button"
            >검색하기</a-button
          >
      </template>
      <a class="searchIcon" @click="toggleVisible"><SearchOutlined /></a>
    </a-popover>
  </div>
</template>
<style>
.select-button{
  margin: 1rem 0 0 0;
}

.popover-title{
  font-weight: 900;
}
.optionWrapper {
  position: absolute;
  top: 3rem;
  right: 5rem;
  z-index: 9999;
  background-color: transparent;
  opacity: 99%;
}

.searchIcon {
  padding: 8px 8px 8px 8px;
  width: 2rem;
  height: 2rem;
  font-weight: 800;
  border: none;
  font-size: 1.6rem;
  background-color: coral;
  border-radius: 50%;
  color: white;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.16), 0 3px 6px rgba(0, 0, 0, 0.23);
}
.searchIcon:hover {
  color: brown;
}

/* .attOption{
  width: 50px;
} */
</style>
