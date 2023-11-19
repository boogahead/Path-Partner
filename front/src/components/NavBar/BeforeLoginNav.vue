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
import {ref} from "vue";
import {login} from "@/api/AuthAPI";

const loginModalOpen = ref(false);
const signInModalOpen = ref(false);

const loginId = ref("")
const loginPassword = ref("")

const signInId = ref("")
const signInPassword = ref("")
const signInCheckPassword = ref("")
const signInEmail = ref("")
const signInNickName = ref("")

const loginAttempt = () => {
  const loginInfo = {
    id: loginId.value,
    password: loginPassword.value
  };
  login(loginInfo, (response) => {

  })
}

const isCorrect = ref(false)
const checkPassword = () => {
  if(signInPassword.value.length > 0) {
    isCorrect.value = (signInPassword.value === signInCheckPassword.value)
  }
}

</script>

<template>
  <div class="d-flex w-auto">
    <MDBBtn color="primary" aria-controls="loginModal" @click="loginModalOpen=true">
      Login
    </MDBBtn>
    <MDBModal
        id="loginModal"
        tabindex="-1"
        v-model="loginModalOpen"
    >
      <MDBModalHeader>
        <MDBModalTitle> Login</MDBModalTitle>
      </MDBModalHeader>
      <MDBModalBody>
        <div class="container">
          <div class="col justify-content-around mb-3">
            <MDBInput label="id" v-model="loginId" class="mb-3"></MDBInput>
            <MDBInput label="password" v-model="loginPassword"></MDBInput>
          </div>
          <div class="col">
            <a href="" class="me-3">아이디 찾기</a>
            <a href="">비밀번호 초기화</a>
          </div>
        </div>
      </MDBModalBody>
      <MDBModalFooter>
        <MDBBtn color="primary" @click="loginAttempt">LOGIN</MDBBtn>
        <!--        <MDBBtn color="danger" @click="loginModalOpen = false">CANCEL</MDBBtn>-->
      </MDBModalFooter>
    </MDBModal>

    <MDBBtn outline="primary" aria-controls="signInModal" @click="signInModalOpen=true">
      Sign in
    </MDBBtn>
    <MDBModal
        id="signInModal"
        tabindex="-1"
        v-model="signInModalOpen"
    >
      <MDBModalHeader>
        <MDBModalTitle>Sign In</MDBModalTitle>
      </MDBModalHeader>
      <MDBModalBody>
        <div class="container">
          <div class="col justify-content-around mb-3">
            <MDBInput label="id" v-model="signInId" counter :maxlength="16"></MDBInput>
            <MDBInput label="nickname" v-model="signInNickName" counter :maxlength="8" class="mt-4"></MDBInput>
            <MDBInput label="email" type="email" v-model="signInEmail" class="mt-4"></MDBInput>
            <MDBInput label="password" type="password" v-model="signInPassword" class="mt-4"></MDBInput>
            <MDBInput label="password check" type="password" v-model="signInCheckPassword" class="mt-4" @change="checkPassword">
              <MDBIcon icon="times" class="trailing text-danger" v-show="!isCorrect"></MDBIcon>
              <MDBIcon icon="check-circle" class="trailing text-success" v-show="isCorrect"></MDBIcon>
            </MDBInput>
          </div>
        </div>
      </MDBModalBody>
      <MDBModalFooter>
        <MDBBtn color="primary" @click="">SIGN IN</MDBBtn>
        <!--        <MDBBtn color="danger" @click="loginModalOpen = false">CANCEL</MDBBtn>-->
      </MDBModalFooter>
    </MDBModal>
  </div>
</template>

<style scoped>

</style>