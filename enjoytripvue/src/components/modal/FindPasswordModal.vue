<script setup>
import { ref, watch, inject } from 'vue';
import { UserOutlined, LockOutlined } from '@ant-design/icons-vue';
const loginModal = inject('stateLogin');

const login = ref({
  userId: ' ',
  userPwd: ' ',
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
    alert(userIdErrMsg.value);
  } else if (userPwdErrMsg.value) {
    alert(userPwdErrMsg.value);
  } else {
    loginMember();
  }
}

function loginMember() {
  console.log('로그인하자', login.value);

  // todo: 나중에 수정해야 함
  // registUser(
  //   member.value,
  //   ({ data }) => {
  //     console.log('regist.....................sucess, data: ', data);
  //   },
  //   (err) => {
  //     console.log(err);
  //   }
  // );
  // API 호출
}
</script>
<!-- <template>
    <div id="modal-container" @click="()=>{toggleLogin()}">
        <div class="p-5" id="loginModal">
        <h1 class="text-center mt-3">로그인</h1>
  <div id="loginForm" class="mt-3">
    <form @submit.prevent="onSubmit">
      <div class="mb-3">
        <label for="userId" class="form-label">아이디 : </label>
        <input
          type="text"
          class="form-control"
          v-model="login.userId"          
          placeholder="아이디..."
        />
      </div>
      <div class="mb-3">
        <label for="userPwd" class="form-label">비밀번호 : </label>
        <input
          type="password"
          class="form-control"
          v-model = "login.userPassword"          
          placeholder="비밀번호..."
        />
      </div>
      <div class="mt-3 col-auto text-center">
        <button type="submit" class="btn btn-outline-primary mb-3">
          로그인
        </button>
      </div>
    </form>
  </div>
    </div>
    </div>
</template> -->

<template>
  <a-modal
    v-model:open="loginModal"
    :closable="false"
    :footer="null"
    centered="true"
  >
    <a-space class="loginHeader">
      <h1 class="title">로그인</h1>
      <div>비밀번호를 잊어버리셨나요?</div>
    </a-space>
    <div class="formContainer">
      <form @submit.prevent="onSubmit">
        <a-input
          v-model="login.userId"
          size="large"
          placeholder="이메일 (example@gmail.com)"
          class="idInput"
        >
          <template #prefix>
            <user-outlined />
          </template>
        </a-input>
        <a-input
          v-model="login.userId"
          size="large"
          placeholder="비밀번호"
          class="pwdInput"
        >
          <template #prefix>
            <LockOutlined />
          </template>
        </a-input>
        <a-flex :justify="'flex-end'" class="buttonWrapper">
          <button type="submit" @click.prevent="onSubmit" class="login">
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
.idInput {
  border-radius: 8px 8px 0 0;
}
.pwdInput {
  border-radius: 0 0 8px 8px;
}

.title {
  font-weight: 900;
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
