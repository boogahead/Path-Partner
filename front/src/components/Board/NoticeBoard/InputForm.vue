<script setup>
import {onMounted, ref, watch} from "vue";
import {putArticle, registArticle} from "@/api/BoardAPI";
import {useRoute, useRouter} from "vue-router";
import {useLoginUserStore} from "@/stores/loginUser";
import {
  MDBCard,
  MDBCardBody,
  MDBCardTitle,
  MDBCardText,
  MDBBtn,
  MDBInput,
  MDBTextarea
} from "mdb-vue-ui-kit";
import {storeToRefs} from "pinia";

const route = useRoute();
const router = useRouter();
const loginUserStore = useLoginUserStore();
const {loginUserInfo} = storeToRefs(loginUserStore);

const props = defineProps({
  detail: Object,
  isModify: Boolean,
})

const content = ref("");
const title = ref("");

onMounted(() => {

})
const writeNoticeArticleAttempt = () => {
  const article = {
    title: title.value,
    content: content.value,

  }
  wr(article, (response) => {
    if(response.status === 201) {
      router.back();
    } else {
      alert("등록에 실패했습니다. 나중에 다시 시도해 주세요.")
    }
  })
}

const modifyArticle = () => {
  const article = {
    articleNo: props.detail.articleNo,
    userId: id.value,
    userName: name.value,
    subject: subject.value,
    content: content.value
  }
  putArticle(article, (response) => {
    if(response.status === 200) {
      router.back();
    } else {
      alert("수정에 실패했습니다. 나중에 다시 시도해 주세요.")
    }
  })
}

</script>

<template>
  <MDBCard class="mb-4">
    <MDBCardBody>
      <MDBCardTitle>
        <div class="d-flex mb-2 justify-content-center p-3">
          <MDBInput class="m-auto" label="제목" size="lg" v-model="subject"/>
        </div>
      </MDBCardTitle>
      <hr class="hr">
      <MDBCardText class="p-3">
        <MDBTextarea rows="10" label="본문" size="lg" v-model="content"/>
      </MDBCardText>
      <hr class="hr">
      <div class="d-flex justify-content-center">
        <MDBBtn color="success" v-if="isModify" @click="modifyArticle">
          수정
        </MDBBtn>
        <MDBBtn color="success" v-if="!isModify" @click="writeArticle">
          등록
        </MDBBtn>
        <MDBBtn color="danger" @click="router.back()">
          취소
        </MDBBtn>
      </div>
    </MDBCardBody>
  </MDBCard>
</template>

<style scoped>

</style>