<script setup>

import {
  MDBBadge,
  MDBBtn,
  MDBCard,
  MDBCardBody,
  MDBTabItem,
  MDBTable,
  MDBTabNav,
  MDBTabs,
  MDBTabContent,
  MDBTabPane
} from "mdb-vue-ui-kit";
import {ref} from "vue";
import FriendList from "@/components/Friend/FriendList.vue";

const tab = ref("friend");
</script>

<template>
  <div class="mb-3">
    <div class="p-5 text-center bg-gradient bg-danger shadow-3 text-light mb-3 bg-opacity-75">
      <h1 class="mb-3">친구 관리</h1>
    </div>
    <div class="container">
      <MDBCard>

        <MDBCardBody>
          <MDBTabs v-model="tab">
            <MDBTabNav justify tabsClasses="mb-3">
              <MDBTabItem tabId="friend">친구</MDBTabItem>
              <MDBTabItem tabId="send">보낸 요청</MDBTabItem>
              <MDBTabItem tabId="receive">받은 요청</MDBTabItem>
            </MDBTabNav>
            <MDBTabContent>
              <MDBTabPane tabId="friend">
                <FriendList/>
              </MDBTabPane>
              <MDBTabPane tabId="send">

              </MDBTabPane>
              <MDBTabPane tabId="receive">

              </MDBTabPane>
            </MDBTabContent>

          </MDBTabs>
        </MDBCardBody>
      </MDBCard>
    </div>
  </div>
</template>

<style scoped>

</style>