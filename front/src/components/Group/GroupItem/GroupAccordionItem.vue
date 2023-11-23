<script setup>

import {
  MDBAccordionItem,
  MDBTable,
  MDBBtn,
  MDBInput,
  MDBModalHeader,
  MDBModalBody,
  MDBModal,
  MDBModalTitle, MDBModalFooter
} from "mdb-vue-ui-kit";
import {computed, onMounted, ref, watch} from "vue";
import GroupMemberItem from "@/components/Group/GroupItem/GroupMemberItem.vue";
import {getGroupMember, getPendingInviteList, leaveGroup} from "@/api/GroupAPI";
import FriendListItem from "@/components/Friend/FriendItem/FriendListItem.vue";

const groupMaster = ref("")

const props = defineProps({
  groupInfo: Object
})

const emits = defineEmits(['groupActionEvent', 'groupInviteEvent'])

const groupMembers = ref([]);

onMounted(async () => {
  await reloadMembers();
})
const reloadMembers = async () => {
  await getGroupMember(props.groupInfo.groupId, async (response) => {
        groupMembers.value = response.data;
        console.log(props.groupInfo.groupName, "getGroupMember", groupMembers.value)
        for (let member of groupMembers.value) {
          console.log(member)
          if (member.groupMaster === true) {
            groupMaster.value = member.uuid;
            break;
          }
        }

        await getPendingInviteList(props.groupInfo.groupId, (response) => {
          groupMembers.value.push(...response.data)
          console.log(props.groupInfo.groupName, "getPendingInviteList", groupMembers.value)
        }, (error) => {
          alert("가입대기중인 그룹원을 불러올 수 없습니다.")
        })
      },
      (error) => {
        alert("그룹원을 불러올 수 없습니다.")
      }
  )
}

const leaveGroupAttempt = () => {
  leaveGroup(props.groupInfo.groupId, (response) => {
    reloadMembers();
  }, (error) => {
    if (error.response.status === 400) {
      alert("그룹장은 그룹이 비기 전에 나갈 수 없습니다.")
    } else {
      alert("그룹 나가기 실패")
    }
  })
}
</script>

<template>
  <MDBAccordionItem :headerTitle="groupInfo.groupName" collapseId="groupInfo.groupId">
    <MDBTable align="middle" class="mb-3 bg-white" hover>
      <tbody>
      <GroupMemberItem v-for="member in groupMembers" :key="member.uuid" :info="member"
                       :groupMaster="groupMaster" @groupMemberActionEvent="reloadMembers"/>
      </tbody>
    </MDBTable>
    <div class="d-flex justify-content-between">
      <MDBBtn color="warning" @click="$emit('groupInviteEvent',groupInfo.groupId)">
        친구 초대하기
      </MDBBtn>
      <MDBBtn color="danger" @click="leaveGroupAttempt">
        그룹 나가기
      </MDBBtn>
    </div>
  </MDBAccordionItem>
</template>

<style scoped>

</style>