<script setup>

import {MDBBadge, MDBBtn} from "mdb-vue-ui-kit";
import {acceptGroupInvite, deleteGroupInvite} from "@/api/GroupAPI";

const props = defineProps({
  groupInvite: Object
})

const emits = defineEmits(['groupInviteActionEvent'])

const acceptGroupInviteAttempt = () => {
  acceptGroupInvite(props.groupInvite.groupId, (response) => {
    emits('groupInviteActionEvent')
  }, (error) => {
    alert("가입 수락 실패")
  })
}

const deleteGroupInviteAttempt = () => {
  deleteGroupInvite(props.groupInvite.groupId, (response) => {
    emits('groupInviteActionEvent')
  }, (error) => {
    alert("가입 요청 거절 실패")
  })
}

</script>

<template>
  <tr>
    <td>
      <div class="d-flex align-items-center">
        <div class="ms-3">
          <p class="fw-bold mb-1">
            {{ groupInvite.groupName }}
          </p>
        </div>
      </div>
    </td>
    <td class="text-end">
      <MDBBtn color="success" size="sm" rounded @click="acceptGroupInviteAttempt">
        <i class="fas fa-check"></i>
      </MDBBtn>
      <MDBBtn color="danger" size="sm" rounded @click="deleteGroupInviteAttempt">
        <i class="fas fa-trash-alt"/>
      </MDBBtn>
    </td>
  </tr>
</template>

<style scoped>

</style>