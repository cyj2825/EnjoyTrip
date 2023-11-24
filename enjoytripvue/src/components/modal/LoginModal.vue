<script setup>
import { ref, watch, inject } from 'vue';
import { UserOutlined, LockOutlined } from '@ant-design/icons-vue';
// JWT를 위해 추가
import { storeToRefs } from 'pinia';
import { useRouter } from 'vue-router';
import { useMemberStore } from '@/stores/member';
import { message } from 'ant-design-vue';
const router = useRouter();

const memberStore = useMemberStore();
const { isLogin } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;

const loginModal = inject('stateLogin');

const login = ref({
  userId: '',
  userPwd: '',
});

const userIdErrMsg = ref('');
const userPwdErrMsg = ref('');

watch(
  () => login.value.userId,
  (value) => {
    let len = value.length;
    if (len == 0) {
      userIdErrMsg.value = '아이디를 입력해주세요!';
    } else userIdErrMsg.value = '';
  },
  { immediate: true }
);
watch(
  () => login.value.userPwd,
  (value) => {
    let len = value.length;
    if (len == 0) {
      userPwdErrMsg.value = '비밀번호를 입력해주세요!';
    } else userPwdErrMsg.value = '';
  },
  { immediate: true }
);

function onSubmit() {
  if (userIdErrMsg.value) {
    message.warning("아이디를 입력해주세요!")
  } else if (userPwdErrMsg.value) {
    message.warning("비밀번호를 입력해주세요!")
  } else {
    loginMember();
  }
}

const loginMember = async () => {
  console.log('login 진행 중!!!');
  login.value = {
    userId: login.value.userId.trim(),
    userPwd: login.value.userPwd.trim(),
  };
  await userLogin(login.value);
  let token = sessionStorage.getItem('accessToken');

  console.log('isLogin: ', isLogin);
  if (isLogin) {
    loginModal.value = !loginModal.value;
    router.push({ name: 'main' });
    // 이름 찍기 위해서 token을 가지고 getUserInfo로 가서 가져온다 -> member.js
    getUserInfo(token);
  }
};

function moveSignup() {
  loginModal.value = !loginModal.value;
  router.push({ name: 'member-signup' });
}
</script>

<template>
  <a-modal
    v-model:open="loginModal"
    :closable="false"
    :footer="null"
    centered="true"
  >
    <a-space class="loginHeader">
      <div :style="{ display: 'flex', alignItems: 'flex-start' }">
        <img src="@/assets/login.gif" :style="{ width: '50px' }" />
        <h1 class="title">로그인</h1>
      </div>
    </a-space>
    <div class="formContainer">
      <form @submit.prevent="onSubmit">
        <a-input
          :style="{ marginTop: '10px' }"
          id="idValue"
          v-model:value="login.userId"
          size="large"
          placeholder="아이디"
        >
          <template #prefix>
            <user-outlined />
          </template>
        </a-input>
        <a-input-password
          :style="{ marginTop: '12px' }"
          id="pwValue"
          v-model:value="login.userPwd"
          size="large"
          placeholder="비밀번호"
        >
          <template #prefix>
            <LockOutlined />
          </template>
        </a-input-password>
        <div
          :style="{
            display: 'flex',
            justifyContent: 'space-between',
            marginTop: '12px',
          }"
        >
          <a-button
            :style="{ color: '#ff7f50' }"
            type="link"
            @click="moveSignup"
            >회원가입</a-button
          >
          <a-button :style="{ color: '#ff7f50' }" type="link"
            >비밀번호를 잊어버리셨나요?</a-button
          >
        </div>

        <a-flex :justify="'center'" class="buttonWrapper">
          <button
            type="submit"
            @keyup.enter="onSubmit"
            @click.prevent="onSubmit"
            class="login"
          >
            로그인
          </button>
        </a-flex>
      </form>
    </div>
  </a-modal>
</template>
<style>
.loginHeader {
  display: flex;
  justify-content: space-between;
}

.title {
  font-weight: 900;
  padding-left: 10px;
  margin-top: 5px;
}

.buttonWrapper {
  margin-top: 2rem;
}

.login {
  background-color: #ff7f50;
  border: none;
  padding: 0.5rem 2rem;
  font-weight: 800;
  border-radius: 3rem;
  font-size: larger;
  color: aliceblue;
}
</style>
