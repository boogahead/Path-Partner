<script setup>
import {
  MDBTabs,
  MDBTabNav,
  MDBTabItem,
  MDBCard, MDBCardBody
} from 'mdb-vue-ui-kit';
import {ref} from "vue";
import router from "@/router/index";

const tab = ref("qna");

const changeBoard = (id) => {
  router.push({name: id});
}
</script>

<template>
  <div>
    <div class="p-5 text-center bg-gradient bg-secondary shadow-3 text-light mb-3 bg-opacity-75">
      <h1 class="mb-3">게 시 판</h1>
    </div>
    <div class="container">
      <MDBCard>
        <MDBCardBody>
          <MDBTabs v-model="tab">
            <!-- Tabs navs -->
            <MDBTabNav justify tabsClasses="mb-3">
              <MDBTabItem tag="button" tab-id="qna" :to="{name:'qna'}" @click="changeBoard('qna')">
                <i class="fas fa-question-circle fa-fw me-2"></i>QnA
              </MDBTabItem>
              <MDBTabItem tag="button" tab-id="notice" :to="{name:'notice'}"
                          @click="changeBoard('notice')">
                <i class="fas fa-check fa-fw me-2"></i>공지사항
              </MDBTabItem>
            </MDBTabNav>
            <!-- Tabs navs -->
            <RouterView/>
          </MDBTabs>
        </MDBCardBody>
      </MDBCard>
    </div>
  </div>
</template>

<style scoped>

</style>