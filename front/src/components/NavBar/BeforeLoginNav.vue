<script setup>

import {
  MDBBtn,
  MDBModal,
  MDBModalBody,
  MDBModalFooter,
  MDBModalHeader,
  MDBModalTitle,
  MDBInput,
  MDBIcon
} from "mdb-vue-ui-kit";
import {ref, watch} from "vue";
import {findIdByEmail, login, logout, register, resetPasswordByIdAndEmail} from "@/api/AuthAPI";
import {useLoginUserStore} from "@/stores/loginUser";
import {storeToRefs} from "pinia";
import router from "@/router";

// 이메일 유효성 검사
const regex = new RegExp('^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+');

// 모달 on / off
const loginModalOpen = ref(false);
const signUpModalOpen = ref(false);
const findIdModalOpen = ref(false);
const resetPasswordModalOpen = ref(false);

// login
const loginUserStore = useLoginUserStore();
const {loginUserInfo, accessToken, refreshToken, isLogin} = storeToRefs(loginUserStore);
const loginId = ref("")
const loginPassword = ref("")

// sign up
const signUpId = ref("")
const signUpPasswordAndCheck = ref({
  password: "",
  checkPassword: ""
})
const signUpEmail = ref("")
const signUpNickName = ref("")

// find id
const findIdEmail = ref("");

// reset password
const resetPasswordId = ref("");
const resetPasswordEmail = ref("");
const resetPasswordTempPassword = ref("");

// 모달창 변경
const changeModal = (modal) => {
  if (modal === "login") {
    findIdModalOpen.value = false;
    signUpModalOpen.value = false;
    resetPasswordModalOpen.value = false;
    loginModalOpen.value = true;
  } else if (modal === "signUp") {
    findIdModalOpen.value = false;
    loginModalOpen.value = false;
    resetPasswordModalOpen.value = false;
    signUpModalOpen.value = true;
  } else if (modal === "findId") {
    signUpModalOpen.value = false;
    loginModalOpen.value = false;
    resetPasswordModalOpen.value = false;
    findIdModalOpen.value = true;
  } else if (modal === "resetPassword") {
    signUpModalOpen.value = false;
    loginModalOpen.value = false;
    findIdModalOpen.value = false;
    resetPasswordModalOpen.value = true;
  }

}

// 로그인 로직
const loginAttempt = () => {
  if (loginId.value.length === 0 || loginPassword.value.length === 0) {
    alert("아이디와 비밀번호를 모두 입력해주세요.")
    return;
  }

  const loginInfo = {
    id: loginId.value,
    password: loginPassword.value
  };

  login(loginInfo, (response) => {
    loginUserInfo.value = response.data.userInfo;
    accessToken.value = response.data.accessToken;
    refreshToken.value = response.data.refreshToken;
    isLogin.value = true;

    loginModalClose();
  }, ({response}) => {
    if (response.status === 401) {
      alert("로그인 실패. 아이디와 비밀번호를 다시 확인하세요.")
    } else {
      alert("지금 로그인할 수 없습니다. 잠시후 다시 로그인 해주세요.")
    }
  })
}

const loginModalClose = () => {
  loginId.value = "";
  loginPassword.value = "";
  loginModalOpen.value = false;
}

// 아이디 찾기 로직
const findIdAttempt = () => {
  // 이메일 유효성 검사
  if (findIdEmail.value.length === 0 || !regex.test(findIdEmail.value)) {
    alert("올바른 이메일을 입력해주세요.")
    return;
  }

  loginId.value = "";
  findIdByEmail(findIdEmail.value, (response) => {
    loginId.value = response.data.id;
  }, ({response}) => {
    if (response.status === 400) {
      alert("해당하는 계정을 찾을 수 없습니다.")
    } else {
      alert("지금은 사용할 수 없습니다. 잠시후 다시 시도해 주세요.")
    }
  })
}

const findIdModalClose = () => {
  findIdEmail.value = ""
  loginId.value = ""
  findIdModalOpen.value = false;
}

// 비밀번호 초기화 로직
const resetPasswordAttempt = () => {
  // 이메일 유효성 검사
  if (resetPasswordEmail.value.length === 0 || !regex.test(resetPasswordEmail.value)) {
    alert("올바른 이메일을 입력해주세요.")
    return;
  }

  const resetInfo = {
    id: resetPasswordId.value,
    email: resetPasswordEmail.value
  }

  resetPasswordByIdAndEmail(resetInfo, (response) => {
    resetPasswordTempPassword.value = response.data.tempPassword;
    loginId.value = resetPasswordId.value;
  }, (response) => {
    if (response.status === 400) {
      alert("해당하는 사용자를 찾을 수 없습니다.")
    } else {
      alert("지금은 사용할 수 없습니다. 잠시후 다시 시도해 주세요.")
    }
  })
}

const resetPasswordModalClose = () => {
  resetPasswordId.value = "";
  resetPasswordEmail.value = "";
  resetPasswordTempPassword.value = "";
  loginId.value = "";
  resetPasswordModalOpen.value = false;
}

// 회원가잆시 비밀번호 일치 표시
const isCorrect = ref(false)
watch(signUpPasswordAndCheck, () => {
  if (signUpPasswordAndCheck.value.checkPassword.length === 0
      || signUpPasswordAndCheck.value.password.length === 0) {
    isCorrect.value = false;
  } else {
    isCorrect.value = signUpPasswordAndCheck.value.checkPassword
        === signUpPasswordAndCheck.value.password;
  }
}, {deep: true})

// 회원 가입 로직
const signUpAttempt = () => {
  // 공백 체크
  if (signUpId.value.length === 0 || signUpPasswordAndCheck.value.password.length === 0
      || signUpPasswordAndCheck.value.checkPassword.length === 0 || signUpEmail.value.length === 0
      || signUpNickName.value.length === 0) {
    alert("비어있는 항목들을 모두 입력해주세요.")
    return;
  }

  // 이메일 체크
  if (!regex.test(signUpEmail.value)) {
    alert("올바른 이메일을 입력해주세요.")
    return;
  }

  // 비밀번호 확인 체크
  if (!isCorrect.value) {
    alert("비밀번호가 서로 일치하지 않습니다.")
    return;
  }

  const registerInfo = {
    id: signUpId.value,
    password: signUpPasswordAndCheck.value.password,
    nickname: signUpNickName.value,
    email: signUpEmail.value
  }

  register(registerInfo, (response) => {
    if (response.status === 201) {
      alert("계정 생성 성공")
      signUpdModalClose();
      changeModal("login");
    } else if (response.status === 200) {
      alert("계정 생성 실패. 입력값을 다시 확인해주세요.")
    } else {
      alert("지금은 사용할 수 없습니다. 잠시후 다시 시도해 주세요.")
    }

  })

}

const signUpdModalClose = () => {
  signUpId.value = ""
  signUpPasswordAndCheck.value = {
    password: "",
    checkPassword: ""
  }
  signUpEmail.value = ""
  signUpNickName.value = ""
  signUpModalOpen.value = false
}
</script>

<template>
  <div class="d-flex w-auto">
    <MDBBtn color="primary" aria-controls="loginModal" @click="loginModalOpen=true">
      Login
    </MDBBtn>
    <MDBBtn outline="primary" aria-controls="signInModal" @click="signUpModalOpen=true">
      Sign Up
    </MDBBtn>

    <!-- 모달창 -->
    <!-- 로그인 모달-->
    <MDBModal
        id="loginModal"
        v-model="loginModalOpen"
    >
      <MDBModalHeader>
        <MDBModalTitle> Login</MDBModalTitle>
      </MDBModalHeader>
      <MDBModalBody>
        <div class="container">
          <div class="col justify-content-around mb-3">
            <MDBInput label="id" v-model="loginId" class="mb-3"></MDBInput>
            <MDBInput label="password" type="password" :maxlength="20" v-model="loginPassword"
                      @keyup.enter="loginAttempt"></MDBInput>
          </div>
          <div class="col">
            <a href="" @click.prevent="changeModal('findId')" class="me-3">아이디 찾기</a>
            <a href="" @click.prevent="changeModal('resetPassword')"
               class="me-3 text-primary text-weight-bold">비밀번호 초기화</a>
          </div>
        </div>
      </MDBModalBody>
      <MDBModalFooter>
        <MDBBtn color="primary" @click="loginAttempt">LOGIN</MDBBtn>
        <MDBBtn color="danger" @click="loginModalClose">CANCEL</MDBBtn>
      </MDBModalFooter>
    </MDBModal>


    <!-- 회원가입 모달-->
    <MDBModal
        id="signUpModal"
        v-model="signUpModalOpen"
    >
      <MDBModalHeader>
        <MDBModalTitle>Sign Up</MDBModalTitle>
      </MDBModalHeader>
      <MDBModalBody>
        <div class="container">
          <div class="col justify-content-around mb-3">
            <MDBInput label="id" v-model="signUpId" counter :maxlength="16"></MDBInput>
            <MDBInput label="nickname" v-model="signUpNickName" counter :maxlength="8"
                      class="mt-4"></MDBInput>
            <MDBInput label="email" type="email" v-model="signUpEmail" class="mt-4"></MDBInput>
            <MDBInput label="password" type="password" v-model="signUpPasswordAndCheck.password"
                      class="mt-4"></MDBInput>
            <MDBInput label="password check" type="password"
                      v-model="signUpPasswordAndCheck.checkPassword"
                      class="mt-4" @keyup.enter="signUpAttempt">
              <MDBIcon icon="times" class="trailing text-danger" v-show="!isCorrect"></MDBIcon>
              <MDBIcon icon="check-circle" class="trailing text-success"
                       v-show="isCorrect"></MDBIcon>
            </MDBInput>
          </div>
        </div>
      </MDBModalBody>
      <MDBModalFooter>
        <MDBBtn color="primary" @click="signUpAttempt">SIGN UP</MDBBtn>
        <MDBBtn color="danger" @click="signUpdModalClose">CANCEL</MDBBtn>
      </MDBModalFooter>
    </MDBModal>

    <!-- 아이디 찾기 모달 -->
    <MDBModal
        id="findId"
        v-model="findIdModalOpen"
    >
      <MDBModalHeader>
        <MDBModalTitle> Find Id</MDBModalTitle>
      </MDBModalHeader>
      <MDBModalBody>
        <div class="container">
          <div class="col justify-content-around mb-3">
            <MDBInput label="email" type="email" v-model="findIdEmail"
                      @keyup.enter="findIdAttempt"></MDBInput>
          </div>
          <div class="col">
            찾은 아이디 : {{ loginId }}
          </div>
        </div>
      </MDBModalBody>
      <MDBModalFooter>
        <MDBBtn outline="primary" @click="changeModal('login')">LOGIN</MDBBtn>
        <MDBBtn color="primary" @click="findIdAttempt">FIND ID</MDBBtn>
        <MDBBtn color="danger" @click="findIdModalClose">CANCEL</MDBBtn>
      </MDBModalFooter>
    </MDBModal>

    <!-- 비밀번호 초기화 모달 -->
    <MDBModal
        id="resetPassword"
        v-model="resetPasswordModalOpen"
    >
      <MDBModalHeader>
        <MDBModalTitle> Reset Password</MDBModalTitle>
      </MDBModalHeader>
      <MDBModalBody>
        <div class="container">
          <div class="col justify-content-around mb-3">
            <MDBInput label="id" v-model="resetPasswordId" class="mb-3"></MDBInput>
            <MDBInput label="email" v-model="resetPasswordEmail" class="mb-3"
                      @keyup.enter="resetPasswordAttempt"></MDBInput>
          </div>
          <div class="col">
            임시 비밀번호 : {{ resetPasswordTempPassword }}
          </div>
        </div>
      </MDBModalBody>
      <MDBModalFooter>
        <MDBBtn outline="primary" @click="changeModal('login')">LOGIN</MDBBtn>
        <MDBBtn color="primary" @click="resetPasswordAttempt">RESET PASSWORD</MDBBtn>
        <MDBBtn color="danger" @click="resetPasswordModalClose">CANCEL</MDBBtn>
      </MDBModalFooter>
    </MDBModal>
  </div>
</template>

<style scoped>

</style>