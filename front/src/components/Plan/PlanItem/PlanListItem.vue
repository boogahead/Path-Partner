<script setup>
import {
  MDBCard,
  MDBCardBody,
  MDBCardText,
  MDBCardTitle,
  MDBCol,
  MDBBadge,
  MDBPopover,
  MDBCarousel,
  MDBBtn,
  MDBTable
} from "mdb-vue-ui-kit";
import {onMounted, ref, watch} from "vue";
import {useRouter} from "vue-router";
import {getGroupMember} from "@/api/GroupAPI";
import PlanGroupMemberitem from "@/components/Plan/PlanItem/PlanGroupMemberitem.vue";
import {useLoginUserStore} from "@/stores/loginUser";
import {storeToRefs} from "pinia";

const props = defineProps({
  plan: Object
})

const router = useRouter();
const popover = ref(false);
const planTitle = ref("")
const creationDate = ref("")
const articleId = ref("")
const items = ref([])
const carousel = ref(0);
const groupMember = ref([]);
const groupName = ref("")
const planArticleUuid = ref("")

const loginUser = useLoginUserStore();
const {loginUserInfo} = storeToRefs(loginUser);

onMounted(() => {
  if (props.plan && props.plan.imgSrc) {
    items.value = JSON.parse(props.plan.imgSrc);
  }
  creationDate.value = props.plan.creationDate;
  planTitle.value = props.plan.planTitle;
  articleId.value = props.plan.planArticleId;
  planArticleUuid.value = props.plan.uuid;
  getGroupMember(props.plan.groupId, (response) => {
    groupMember.value = response.data;
    groupName.value = groupMember.value[0].groupName;
  })

})

const isWriter = () => {
  return loginUserInfo.value.uuid === planArticleUuid.value
}
</script>

<template>
  <MDBCol>
    <MDBCard>
      <div style="height: 280px">
        <MDBCarousel
            v-if="items.length > 0"
            v-model="carousel"
            :items="items"
            :indicators="false"
            fade
        />
      </div>
      <MDBCardBody>
        <MDBCardTitle>{{ planTitle }}</MDBCardTitle>
        <MDBCardText>
          <MDBPopover v-model="popover">
            <template #reference>
              <MDBBadge color="primary" @click="popover = !popover">{{ groupName }}</MDBBadge>
            </template>
            <template #header>그룹원</template>
            <template #body>
              <div>
                <MDBTable>
                  <tbody>
                  <PlanGroupMemberitem v-for="member in groupMember" :info="member"/>
                  </tbody>
                </MDBTable>
              </div>
            </template>
          </MDBPopover>
          <hr class="hr"/>
          <div class="d-flex justify-content-between mt-3">
            <span class="mb-0">{{ creationDate }}</span>
            <div class="d-flex">
              <div class="text-secondary me-3" @click="router.push({name:'planEdit', params:{planArticleId:articleId}})">
                <i class="fas fa-edit"></i>
              </div>
              <div class="text-danger" v-if="isWriter">
                <i class="fas fa-trash-alt"></i>
              </div>
            </div>
          </div>
        </MDBCardText>
      </MDBCardBody>
    </MDBCard>
  </MDBCol>
</template>

<style scoped>

</style>