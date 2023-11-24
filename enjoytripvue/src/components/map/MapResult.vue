<template>
  <div class="resultMain">
    <a-result title="여행 계획 세우기를 완료했어요!">
      <template #icon>
        <smile-twoTone :style="{ color: 'coral' }" />
      </template>
      <template #extra>
        <a-button type="primary"><router-link :to="{name:'main'}">메인으로</router-link></a-button>
        <a-button type="primary"><router-link :to="{name:''}">내가 세운 여행계획 보기</router-link></a-button>
      </template>
    </a-result>
  </div>
</template>

<script setup>
import { SmileTwoTone } from "@ant-design/icons-vue";
</script>

<style scoped>
.resultMain {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
