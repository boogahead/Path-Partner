<script setup>

import {
  MDBBadge,
  MDBBtn,
  MDBTable,
  MDBInput,
  MDBDropdown,
  MDBDropdownMenu,
  MDBDropdownItem
} from "mdb-vue-ui-kit";
import FriendListItem from "@/components/Friend/FriendItem/FriendListItem.vue";
import {computed, onMounted, ref, watch} from "vue";
import {findAllUserByuNickname} from "@/api/UserAPI";

// uuid, id, email, nickname, profileImg
const props = defineProps({
  type: String,
  searchResult: Object
})

const emits = defineEmits(['friendActionEvent'])

const friendSearchDropdown = ref(false);
const nicknameInput = ref("");
const foundUser = ref([]);

// 입력에 따른 실시간 검색
watch(nicknameInput, () => {
  if (nicknameInput.value.length === 0) {
    foundUser.value = [];
    return;
  }

  friendSearchDropdown.value = true;
  findAllUserByuNickname(nicknameInput.value, (response) => {
    foundUser.value = response.data
  })
})

const foundUserComputed = computed(() => {
  return foundUser.value;
})

const onLeaveHandler = () => {
  friendSearchDropdown.value = false
}

const eventHandler = (event) => {
  emits('friendActionEvent')
}
</script>

<template>
  <div>
    <MDBTable align="middle" class="mb-3 bg-white" hover>
      <thead class="bg-light">
      </thead>
      <tbody>
      <FriendListItem v-for="friendInfo in searchResult" :info="friendInfo" :type="type"
                      @friendActionEvent="eventHandler"/>
      </tbody>
    </MDBTable>
    <div v-if="type === 'friend'" class="container">
      <div class="row">
        <div class="col">
          <MDBDropdown v-model="friendSearchDropdown">
            <MDBInput type="text" placeholder="닉네임 입력" @focus="friendSearchDropdown = true"
                      v-model="nicknameInput"></MDBInput>
            <MDBDropdownMenu @mouseleave="onLeaveHandler">
              <MDBDropdownItem disabled v-for="user in foundUserComputed">
                <MDBTable>
                  <thead class="bg-light">
                  </thead>
                  <tbody>
                  <FriendListItem :info="user" :type="'user'" @friendActionEvent="eventHandler"/>
                  </tbody>
                </MDBTable>
              </MDBDropdownItem>
            </MDBDropdownMenu>
          </MDBDropdown>
        </div>
      </div>
    </div>
  </div>


</template>

<style scoped>

</style>