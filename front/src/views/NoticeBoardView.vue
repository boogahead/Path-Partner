<script setup>
import {
  MDBTabs,
  MDBTabNav,
  MDBTabItem,
  MDBCard, MDBCardBody
} from 'mdb-vue-ui-kit';
import {onMounted, ref} from "vue";

const tab = ref("notice");

const noticeArticleList = ref([]);

onMounted(() => {
})
</script>

<template>
  <div>
    <div class="p-5 text-center bg-gradient bg-secondary shadow-3 text-light mb-3 bg-opacity-75">
      <h1 class="mb-3">공지사항</h1>
    </div>
    <div class="container">
      <MDBCard>
        <MDBCardBody>
          <MDBTabs v-model="tab">
            <!-- Tabs navs -->
            <MDBTabNav justify tabsClasses="mb-3">
              <MDBTabItem tag="button" tab-id="notice">
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