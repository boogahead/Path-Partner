<script setup>

import {
  MDBBtn,
} from "mdb-vue-ui-kit";
import {onMounted, ref} from "vue";
import {
  acceptFriendRequest,
  cancelFriendRequest,
  deleteFriend, rejectFriendRequest,
  sendFriendRequest
} from "@/api/FriendAPI";
import {registerGroupInvite} from "@/api/GroupAPI";

const props = defineProps({
  info: Object,
  groupId: String,
  type: String
})
// uuid, id, email, nickname, prifileImg

const emits = defineEmits(['friendActionEvent'])

const profile = 'data:image/png;base64,' + btoa(
    new Uint8Array(props.info.profileImg)
    .reduce((data, byte) => data + String.fromCharCode(byte), ''));

const defaultProfileImgHandler = (event) => {
  event.target.src = "http://localhost/static/images/defaultPicture.jpg"
}

const deleteFriendAttempt = () => {
  deleteFriend(props.info.uuid, (response) => {
    emits('friendActionEvent')
  }, (error) => {
    alert("지금은 할 수 없습니다. 나중에 다시 시도해주세요.")
  })
}

const sendFriendRequestAttempt = () => {
  sendFriendRequest(props.info.uuid, (response) => {
    emits('friendActionEvent')
  }, () => {
    alert("지금은 할 수 없습니다. 나중에 다시 시도해주세요.")
  })
}

const acceptFriendRequestAttempt = () => {
  acceptFriendRequest(props.info.uuid, (response) => {
    emits('friendActionEvent')
  }, () => {
    alert("지금은 할 수 없습니다. 나중에 다시 시도해주세요.")
  })
}

const cancelFriendRequestAttempt = () => {
  cancelFriendRequest(props.info.uuid, (response) => {
    emits('friendActionEvent')
  }, () => {
    alert("지금은 할 수 없습니다. 나중에 다시 시도해주세요.")
  })
}

const rejectFriendRequestAttempt = () => {
  rejectFriendRequest(props.info.uuid, (response) => {
    emits('friendActionEvent')
  }, () => {
    alert("지금은 할 수 없습니다. 나중에 다시 시도해주세요.")
  })
}

const registerGroupInviteAttempt = () => {
  registerGroupInvite(props.groupId, props.info.uuid, (response) => {
    if(response.data === "") {
      alert("이미 초대를 보냈습니다.")
    }
  }, (error) => {
    alert("지금은 할 수 없습니다. 나중에 다시 시도해주세요.")
  })
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
          <p class="fw-bold mb-1">{{ info.nickname }}</p>
          <p class="text-muted mb-0">{{ info.email }}</p>
        </div>
      </div>
    </td>
    <td class="text-end">
      <MDBBtn v-if="type === 'friend'" color="danger" size="sm" rounded @click="deleteFriendAttempt">
        <i class="fas fa-trash-alt"/>
      </MDBBtn>
      <MDBBtn v-if="type === 'user'" color="success" size="sm" rounded
              @click="sendFriendRequestAttempt">
        <i class="fas fa-user-friends"></i>
      </MDBBtn>

      <MDBBtn v-if="type === 'sent'" color="danger" size="sm" rounded
              @click="cancelFriendRequestAttempt">
        <i class="fas fa-trash-alt"/>
      </MDBBtn>

      <MDBBtn v-if="type === 'received'" color="success" size="sm" rounded
              @click="acceptFriendRequestAttempt">
        <i class="fas fa-user-friends"></i>
      </MDBBtn>
      <MDBBtn v-if="type === 'received'" color="danger" size="sm" rounded
              @click="rejectFriendRequestAttempt">
        <i class="fas fa-trash-alt"/>
      </MDBBtn>

      <MDBBtn v-if="type === 'group'" color="success" size="sm" rounded
              @click="registerGroupInviteAttempt">
        <i class="fas fa-users"></i>
      </MDBBtn>
    </td>
  </tr>

</template>

<style scoped>

</style>