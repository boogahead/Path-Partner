<script setup>

import {MDBBadge, MDBBtn} from "mdb-vue-ui-kit";
import {cancelGroupInvite, kickGroupMember} from "@/api/GroupAPI";
import {useLoginUserStore} from "@/stores/loginUser";
import {storeToRefs} from "pinia";
import {onMounted, ref} from "vue";

const props = defineProps({
  info: Object,
  groupMaster:String,
  type:String
})
// groupMemberDto
// groupId, groupName, uuid, id, email, nickname, prifileImg, groupMaster

const loginUserStore = useLoginUserStore();
const {loginUserInfo} = storeToRefs(loginUserStore);
const emits = defineEmits(['groupMemberActionEvent'])

const profile = 'data:image/png;base64,' + btoa(
    new Uint8Array(props.info.profileImg)
    .reduce((data, byte) => data + String.fromCharCode(byte), ''));

const defaultProfileImgHandler = (event) => {
  event.target.src = "http://localhost/static/images/defaultPicture.jpg"
}

const kickGroupMemberAttempt = () => {
  kickGroupMember(props.info.groupId, props.info.uuid, (response) =>{
  emits('groupMemberActionEvent')
  })
}

const cancelGroupInviteAttempt = () => {
  cancelGroupInvite(props.info.groupId, props.info.uuid, (response) =>{
    emits('groupMemberActionEvent')
  }, (error) => {})
}
</script>

<template>
  <tr>
    <td>
      <div class="d-flex align-items-center">
        <img :src="profile" alt=""
             style="width: 45px; height: 45px"
             class="rounded-circle"
             @error="defaultProfileImgHandler"
        />
        <div class="ms-3">
          <p class="fw-bold mb-1">
            <i v-if="info.groupMaster" class="fas fas fa-crown text-warning"></i>
            {{ info.nickname }}
          </p>
          <p class="text-muted mb-0">{{ info.email }}</p>
        </div>
      </div>
    </td>
    <td>
      <div class="text-center">
        <MDBBadge v-if="type ==='member'" badge="success" pill class="d-inline">회원</MDBBadge>
        <MDBBadge v-else badge="warning" pill class="d-inline">가입수락대기</MDBBadge>
      </div>
    </td>
    <td class="text-end">
      <MDBBtn v-if="groupMaster === loginUserInfo.uuid && !(info.uuid === loginUserInfo.uuid) && type === 'member'" color="danger" size="sm" rounded
              @click="kickGroupMemberAttempt">
        <i class="fas fa-trash-alt"/>
      </MDBBtn>

      <MDBBtn v-if="groupMaster === loginUserInfo.uuid && type === 'sent' " color="danger" size="sm" rounded
              @click="cancelGroupInviteAttempt">
        <i class="fas fa-trash-alt"/>
      </MDBBtn>
    </td>
  </tr>
</template>

<style scoped>

</style>