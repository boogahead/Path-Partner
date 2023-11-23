<script setup>

import GroupAccordionItem from "@/components/Group/GroupItem/GroupAccordionItem.vue";
import {
  MDBAccordion,
  MDBBtn,
  MDBModal,
  MDBModalBody, MDBModalFooter,
  MDBModalHeader, MDBModalTitle,
  MDBTable
} from "mdb-vue-ui-kit";
import {onMounted, ref} from "vue";
import FriendListItem from "@/components/Friend/FriendItem/FriendListItem.vue";
import {getMyFriendList} from "@/api/FriendAPI";

const activeItem = ref("");

const props = defineProps({
  groupList: Object,
  type: String
})

onMounted(() => {
  getMyFriendList((response) => {
    friends.value = response.data
  })
})

const groupInviteModalOpen = ref(false)
const inviteGroupId = ref("")
const friends = ref([])

const groupInviteHandler = (groupId) => {
  inviteGroupId.value = groupId;
  groupInviteModalOpen.value = true;
}
</script>

<template>
  <MDBAccordion v-model="activeItem" class="mb-3">
    <GroupAccordionItem v-for="group in groupList" :key="group.groupId" :groupInfo="group"
                        :collapseId="group.groupId" @groupInviteEvent="groupInviteHandler"/>
  </MDBAccordion>

  <MDBModal
      id="loginModal"
      v-model="groupInviteModalOpen"
  >
    <MDBModalHeader>
      <MDBModalTitle> Group Invite</MDBModalTitle>
    </MDBModalHeader>
    <MDBModalBody>
      <div class="container">
        <div class="col justify-content-around mb-3">
          <div v-show="friends.length === 0">
            가입가능한 친구가 없습니다.
          </div>
          <MDBTable>
            <tbody>
            <FriendListItem v-for="friend in friends" type="group" :groupId="inviteGroupId"
                            :info="friend"/>
            </tbody>
          </MDBTable>
        </div>
      </div>
    </MDBModalBody>
  </MDBModal>
</template>

<style scoped>

</style>