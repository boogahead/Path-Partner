<script setup>
import {
  MDBIcon,
  MDBRow,
  MDBBtn,
  MDBCard,
  MDBCardBody,
  MDBModalHeader,
  MDBModalBody, MDBModal, MDBModalTitle, MDBInput, MDBModalFooter
} from "mdb-vue-ui-kit";
import PlanListItem from "@/components/Plan/PlanItem/PlanListItem.vue";
import {onMounted, ref} from "vue";
import {getPlanArticleList, writePlan} from "@/api/PlanArticleAPI";
import {getGroupList} from "@/api/GroupAPI";
import {useLoginUserStore} from "@/stores/loginUser";
import {storeToRefs} from "pinia";
import router from "@/router";

const myGroupList = ref([]);
const planList = ref([]);
const loginUser = useLoginUserStore();
const {loginUserInfo} = storeToRefs(loginUser);

onMounted(() => {
  getPlanArticleList((response) => {
    planList.value = response.data;

  })
  getGroupList((response) => [
    myGroupList.value = response.data
  ])
})

const planTitle = ref("")
const writePlanModalOpen = ref(false)
const selectedGroup = ref("")
const writePlanModalClose = () => {
  writePlanModalOpen.value = false;
}

const writePlanAttempt = () => {
  let planArticle = {
    plan: JSON.stringify([]),
    groupId: selectedGroup.value,
    uuid: loginUserInfo.value.uuid,
    imgSrc: JSON.stringify([]),
    planTitle: planTitle.value
  }
  let planArticleId = null;
  writePlan(planArticle, (response) => {
    console.log(response.data)
    planArticleId = response.data
    router.push({name: 'planEdit', params: {planArticleId: planArticleId}})
  })
}

</script>

<template>
  <div>
    <div class="container">
      <MDBCard>
        <MDBCardBody>
          <MDBRow :cols="['1','md-3']" class="g-4 mb-3">
            <PlanListItem v-for="plan in planList" :key="plan.planArticleId" :plan="plan"/>
          </MDBRow>
          <div class="text-center mt-3">
            <MDBBtn color="primary" floating @click="writePlanModalOpen = true">
              <MDBIcon icon="plus" size="2x"/>
            </MDBBtn>
          </div>
        </MDBCardBody>
      </MDBCard>
    </div>

    <MDBModal
        id="writePlanModal"
        v-model="writePlanModalOpen"
    >
      <MDBModalHeader>
        <MDBModalTitle> Write Plan</MDBModalTitle>
      </MDBModalHeader>
      <MDBModalBody>
        <div class="container">
          <div class="col justify-content-around mb-3">
            <MDBInput label="Plan Title" v-model="planTitle" class="mb-3"></MDBInput>
          </div>
          <div>
            <p>그룹을 선택해주세요.</p>
            <select v-model="selectedGroup">
              <option v-for="group in myGroupList" :key="group.groupId" :value="group.groupId">
                {{ group.groupName }}
              </option>
            </select>
          </div>
        </div>
      </MDBModalBody>
      <MDBModalFooter>
        <MDBBtn color="primary" @click="writePlanAttempt">생성하기</MDBBtn>
        <MDBBtn color="danger" @click="writePlanModalClose">취소</MDBBtn>
      </MDBModalFooter>
    </MDBModal>
  </div>
</template>

<style scoped>

</style>