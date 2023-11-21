<script setup>
import {
  MDBNavbarItem, MDBIcon,
  MDBBadge,
  MDBDropdown,
  MDBDropdownToggle,
  MDBDropdownMenu,
  MDBDropdownItem,
} from "mdb-vue-ui-kit";
import {ref} from "vue";
import {logout} from "@/api/AuthAPI";
import router from "@/router";
import {useLoginUserStore} from "@/stores/loginUser";
import {storeToRefs} from "pinia";


const loginUserStore = useLoginUserStore();
const {loginUserInfo, accessToken, refreshToken, isLogin} = storeToRefs(loginUserStore);

const notificationDropdown = ref(false);
const avatarDropdown = ref(false);

const logoutAttempt = () => {
  loginUserInfo.value = "";
  accessToken.value = "";
  refreshToken.value = "";
  isLogin.value = false;
  router.replace({name:'home'})
  logout()
}
// getNotification()
// 알람 수 등록 및 알림 컴포넌트 생성 필요

// getProfileIcon()
// 프로필 사진 불러오기


</script>

<template>
  <MDBNavbarItem :to="{name:'search'}" active>
    관광지 검색
  </MDBNavbarItem>
  <MDBNavbarItem :to="{name:'plan'}" active>
    여행 계획
  </MDBNavbarItem>
  <MDBNavbarItem :to="{name:'review'}" active>
    여행 리뷰
  </MDBNavbarItem>
  <MDBNavbarItem :to="{name:'notice'}" active>
    공지사항
  </MDBNavbarItem>
  <MDBNavbarItem :to="{name:'group'}" active>
    그룹 관리
  </MDBNavbarItem>
  <MDBNavbarItem :to="{name:'friend'}" active>
    친구 관리
  </MDBNavbarItem>
  <!--  <MDBDropdown class="nav-item" v-model="notificationDropdown">-->
  <!--    <MDBDropdownToggle tag="a" class="nav-link"-->
  <!--                       @click="notificationDropdown = !notificationDropdown"-->
  <!--    >-->
  <!--      <MDBIcon icon="bell"/>-->
  <!--      <MDBBadge notification color="danger" pill>1</MDBBadge>-->
  <!--    </MDBDropdownToggle>-->
  <!--    <MDBDropdownMenu>-->
  <!--      <MDBDropdownItem href="#">Action</MDBDropdownItem>-->
  <!--      <MDBDropdownItem href="#">Another Action</MDBDropdownItem>-->
  <!--      <MDBDropdownItem href="#">Something else here</MDBDropdownItem>-->
  <!--    </MDBDropdownMenu>-->
  <!--  </MDBDropdown>-->
  <MDBDropdown class="nav-item" v-model="avatarDropdown">
    <MDBDropdownToggle tag="a" class="nav-link" @click="avatarDropdown = !avatarDropdown"
    ><img
        src="https://mdbootstrap.com/img/Photos/Avatars/img (31).webp"
        class="rounded-circle"
        height="22"
        alt=""
        loading="lazy"
    />
    </MDBDropdownToggle>
    <MDBDropdownMenu>
      <MDBDropdownItem href="#">Settings</MDBDropdownItem>
      <MDBDropdownItem tag="button" @click="logoutAttempt">Logout</MDBDropdownItem>
    </MDBDropdownMenu>
  </MDBDropdown>
</template>

<style scoped>

</style>