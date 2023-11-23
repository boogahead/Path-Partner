<script setup>
import {
  MDBBadge,
  MDBTable,
  MDBBtn,
  MDBCard,
  MDBCardBody,
  MDBIcon,
  MDBInput,
  MDBModal,
  MDBModalBody,
  MDBModalFooter,
  MDBModalHeader,
  MDBModalTitle, MDBTabContent, MDBTabItem, MDBTabNav, MDBTabPane, MDBTabs,
} from "mdb-vue-ui-kit";
import {onMounted, ref} from "vue";
import {checkGroupInvite, getGroupList, getPendingInviteList, registerGroup} from "@/api/GroupAPI";
import GroupAccordion from "@/components/Group/GroupAccordion.vue";
import FriendListItem from "@/components/Friend/FriendItem/FriendListItem.vue";
import GroupInviteReceivedItem from "@/components/Group/GroupItem/GroupInviteReceivedItem.vue";

const groupList = ref([])
const receivedGroupInviteList = ref([]);

const groupName = ref("")
const groupRegisterModalOpen = ref(false)

const groupRegisterModalClose = () => {
  groupName.value = "";
  groupRegisterModalOpen.value = false;
}

onMounted(() => {
  reloadGroup()
})

const reloadGroup = async () => {
  await getGroupList((response) => {
    groupList.value = response.data
  }, ({response}) => {
    alert("그룹을 불러올 수 없습니다.")
  })

  await checkGroupInvite((response) => {
    receivedGroupInviteList.value = response.data;
  }, (error) => {
    alert("받은 그룹 가입 요청을 불러올 수 없습니다.")
  })
}

const registerGroupAttempt = () => {
  if (groupName.value.length === 0) {
    alert("그룹 이름을 입력해주세요")
    return;
  }

  const group = {groupName: groupName.value}
  registerGroup(group, (response) => {
    console.log("그룹 등록 성공")
    reloadGroup();
    groupRegisterModalClose();
  }, ({response}) => {
    alert("그룹을 등록할 수 없습니다.")
  })
}

const groupTap = ref('group')
</script>

<template>
  <div class="mb-3">
    <div class="p-5 text-center bg-gradient bg-warning shadow-3 text-light mb-3 bg-opacity-75">
      <h1 class="mb-3">그룹 관리</h1>
    </div>
    <div class="container">
      <MDBCard>
        <MDBCardBody>
          <MDBTabs v-model="groupTap">
            <MDBTabNav justify tabsClasses="mb-3">
              <MDBTabItem tabId="group">
                <span class="me-3">그룹</span>
                <MDBBadge notification color="secondary" pill>{{ groupList.length }}</MDBBadge>
              </MDBTabItem>
              <MDBTabItem tabId="received">
                <span class="me-3">받은 요청</span>
                <MDBBadge notification color="danger" v-show="receivedGroupInviteList.length > 0" pill>{{ receivedGroupInviteList.length }}</MDBBadge>
              </MDBTabItem>
            </MDBTabNav>
            <MDBTabContent>
              <MDBTabPane tabId="group">
                <GroupAccordion :groupList="groupList"/>
                <div class="text-center mt-3">
                  <MDBBtn color="secondary" floating @click="groupRegisterModalOpen=true">
                    <MDBIcon icon="plus" size="2x"/>
                  </MDBBtn>
                </div>
              </MDBTabPane>
              <MDBTabPane tabId="received">
                <MDBTable>
                  <tbody>
                  <GroupInviteReceivedItem v-for="groupInvite in receivedGroupInviteList" :key="groupInvite.groupId" :groupInvite="groupInvite" @groupInviteActionEvent="reloadGroup"/>
                  </tbody>
                </MDBTable>
              </MDBTabPane>
            </MDBTabContent>
          </MDBTabs>
        </MDBCardBody>
      </MDBCard>
    </div>
  </div>

  <MDBModal
      id="loginModal"
      v-model="groupRegisterModalOpen"
  >
    <MDBModalHeader>
      <MDBModalTitle> Group Register</MDBModalTitle>
    </MDBModalHeader>
    <MDBModalBody>
      <div class="container">
        <div class="col justify-content-around mb-3">
          <MDBInput label="group name" v-model="groupName" class="mb-3"
                    @keyup.enter="registerGroupAttempt"></MDBInput>
        </div>
      </div>
    </MDBModalBody>
    <MDBModalFooter>
      <MDBBtn color="primary" @click="registerGroupAttempt">REGISTER</MDBBtn>
      <MDBBtn color="danger" @click="groupRegisterModalClose">CANCEL</MDBBtn>
    </MDBModalFooter>
  </MDBModal>
</template>

<style scoped>

</style>