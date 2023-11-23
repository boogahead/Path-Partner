<script setup>

import GroupAccordionItem from "@/components/Group/GroupItem/GroupAccordionItem.vue";
import {
  MDBAccordion,
  MDBModal,
  MDBModalBody,
  MDBModalHeader, MDBModalTitle,
  MDBTable
} from "mdb-vue-ui-kit";
import {onMounted, ref, watch} from "vue";
import FriendListItem from "@/components/Friend/FriendItem/FriendListItem.vue";
import {getMyFriendList} from "@/api/FriendAPI";
import {useRouter} from "vue-router";

const activeItem = ref("");
const router = useRouter();

const props = defineProps({
  groupList: Object,
  type: String
})

const emits = defineEmits(['groupActionEvent'])

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

watch(groupInviteModalOpen, (after, before) => {
  if(before && !after) {
    router.go(0);
  }
  console.log(before, after)
})

</script>

<template>
  <MDBAccordion v-model="activeItem" class="mb-3">
    <GroupAccordionItem v-for="group in groupList" :key="group.groupId" :groupInfo="group"
                        :collapseId="group.groupId" @groupInviteEvent="groupInviteHandler" @groupActionEvent="$emit('groupActionEvent')"/>
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