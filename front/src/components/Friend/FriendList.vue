<script setup>

import {MDBBadge, MDBBtn, MDBTable} from "mdb-vue-ui-kit";
import FriendListItem from "@/components/Friend/FriendListItem.vue";
import {onMounted, ref} from "vue";

const friendInfoList = ref([])
// uuid, id, email, nickname, profileImg

onMounted(() => {

})
</script>

<template>
  <MDBTable align="middle" class="mb-3 bg-white" hover>
    <thead class="bg-light">
    </thead>
    <tbody>
    <tr>
      <FriendListItem v-for="friendInfo in friendInfoList" :friendInfo="friendInfo"/>
    </tr>
    </tbody>
  </MDBTable>
</template>

<style scoped>

</style>