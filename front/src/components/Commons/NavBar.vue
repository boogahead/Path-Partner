<script setup>
import {
  MDBNavbar,
  MDBNavbarToggler,
  MDBNavbarBrand,
  MDBNavbarNav,
  MDBCollapse,
} from 'mdb-vue-ui-kit';
import {ref} from 'vue';
import AfterLoginNav from "@/components/NavBar/AfterLoginNav.vue";
import BeforeLoginNav from "@/components/NavBar/BeforeLoginNav.vue";
import {useLoginUserStore} from "@/stores/loginUser";
import {storeToRefs} from "pinia";

const navBarCollapse = ref(false);
const loginUserStore = useLoginUserStore();
const {id, name, isLogin} = storeToRefs(loginUserStore);

</script>


<template>
  <MDBNavbar expand="lg" light bg="light" container position="sticky">
    <MDBNavbarBrand href="/" active>Enjoy Trip</MDBNavbarBrand>
    <MDBNavbarToggler
        @click="navBarCollapse = !navBarCollapse"
        target="#navbarContent"
    ></MDBNavbarToggler>
    <MDBCollapse v-model="navBarCollapse" id="navbarContent">
      <MDBNavbarNav class="mb-2 mb-lg-0 justify-content-end" right>
        <AfterLoginNav v-if="isLogin"/>
      </MDBNavbarNav>
      <BeforeLoginNav v-if="!isLogin"/>
    </MDBCollapse>
  </MDBNavbar>
</template>

<style scoped>

</style>