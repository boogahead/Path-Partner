<script setup>

import {
  MDBBadge,
  MDBCard,
  MDBCardBody,
  MDBTabItem,
  MDBTabNav,
  MDBTabs,
  MDBTabContent,
  MDBTabPane
} from "mdb-vue-ui-kit";
import {onMounted, ref} from "vue";
import FriendList from "@/components/Friend/FriendList.vue";
import {getFriendRequestReceived, getFriendRequestSent, getMyFriendList} from "@/api/FriendAPI";

const tab = ref("friend");
const friendList = ref([]);
const sentFriendRequestList = ref([]);
const receivedFriendRequestList = ref([]);

onMounted(async () => {
  await reloadList();
})

const reloadList = async () => {
  await getMyFriendListAttempt();
  await getMyFriendRequestSentAttempt();
  await getMyFriendRequestReceivedAttempt();
}
const getMyFriendListAttempt = () => {
  getMyFriendList((response) => {
    friendList.value = response.data;
  }, (error) => {
    console.log(error)
    alert("친구 목록을 불러올 수 없습니다.")
  })
}

const getMyFriendRequestSentAttempt = () => {
  getFriendRequestSent((response) => {
    sentFriendRequestList.value = response.data;
  }, (error) => {
    console.log(error)
    alert("보낸 요청 목록을 불러올 수 없습니다.")
  })
}

const getMyFriendRequestReceivedAttempt = () => {
  getFriendRequestReceived((response) => {
    receivedFriendRequestList.value = response.data;
  }, (error) => {
    console.log(error)
    alert("받은 요청 목록을 불러올 수 없습니다.")
  })
}
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
              <MDBTabItem tabId="friend">
                <span class="me-3">친구</span>
                <MDBBadge notification color="secondary" pill>{{friendList.length}}</MDBBadge>
              </MDBTabItem>
              <MDBTabItem tabId="sent">
                <span class="me-3">보낸 요청</span>
                <MDBBadge notification color="danger" pill v-show="sentFriendRequestList.length > 0">{{sentFriendRequestList.length}}</MDBBadge>
              </MDBTabItem>
              <MDBTabItem tabId="received">
                <span class="me-3">받은 요청</span>
                <MDBBadge notification color="danger" pill v-show="receivedFriendRequestList.length > 0">{{receivedFriendRequestList.length}}</MDBBadge>
              </MDBTabItem>
            </MDBTabNav>
            <MDBTabContent>
              <MDBTabPane tabId="friend" @friendActionEvent="reloadList">
                <FriendList :searchResult="friendList" type="friend" @friendActionEvent="reloadList"/>
              </MDBTabPane>
              <MDBTabPane tabId="sent" @friendActionEvent="reloadList">
                <FriendList :searchResult="sentFriendRequestList" type="sent" @friendActionEvent="reloadList"/>
              </MDBTabPane>
              <MDBTabPane tabId="received" @friendActionEvent="reloadList">
                <FriendList :searchResult="receivedFriendRequestList" type="received" @friendActionEvent="reloadList"/>
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