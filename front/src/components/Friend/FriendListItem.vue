<script setup>

import {
  MDBBtn,
} from "mdb-vue-ui-kit";
import {onMounted, ref} from "vue";

const props = defineProps({
  friendInfo:Object
})
// uuid, id, email, nickname, prifileImg

</script>

<template>
  <td>
    <div class="d-flex align-items-center">
      <img :src="'data:image/png;base64,' + btoa(String.fromCharCode.apply(null,friendInfo.profileImg))" alt=""
           style="width: 45px; height: 45px"
           class="rounded-circle"/>
      <div class="ms-3">
        <p class="fw-bold mb-1">{{friendInfo.nickname}}</p>
        <p class="text-muted mb-0">{{friendInfo.email}}</p>
      </div>
    </div>
  </td>
  <td class="text-end">
    <MDBBtn color="success" size="sm" rounded>
      <i class="fas fa-users"/>
    </MDBBtn>
    <MDBBtn color="danger" size="sm" rounded>
      <i class="fas fa-trash-alt"/>
    </MDBBtn>
  </td>
</template>

<style scoped>

</style>