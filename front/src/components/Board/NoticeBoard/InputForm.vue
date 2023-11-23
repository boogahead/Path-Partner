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
import {modifyNoticeArticle, writeNoticeArticle} from "@/api/NoticeAPI";

const route = useRoute();
const router = useRouter();
const loginUserStore = useLoginUserStore();
const {loginUserInfo} = storeToRefs(loginUserStore);

const props = defineProps({
  detail: Object,
  isModify: Boolean,
})

const content = ref(props.detail.content);
const title = ref(props.detail.title);

onMounted(() => {
  if (props.isModify) {
    title.value = props.detail.title;
    content.value = props.detail.content;
  }
})
const writeNoticeArticleAttempt = () => {
  const article = {
    title: title.value,
    content: content.value,
  }
  writeNoticeArticle(article, (response) => {
    router.back();
  }, ({response}) => {
    if (response.status === 403) {
      alert("공지글은 관리자만 작성할 수 있습니다.")
    }
  })
}

const modifyNoticeArticleAttempt = () => {
  const article = {
    title: title.value,
    content: content.value,
    noticeArticleId: props.detail.noticeArticeId
  }

  modifyNoticeArticle(article, (response) => {
    if (response.status === 200) {
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
          <MDBInput class="m-auto" label="제목" size="lg" v-model="title"/>
        </div>
      </MDBCardTitle>
      <hr class="hr">
      <MDBCardText class="p-3">
        <MDBTextarea rows="10" label="본문" size="lg" v-model="content"/>
      </MDBCardText>
      <hr class="hr">
      <div class="d-flex justify-content-center">
        <MDBBtn color="success" v-if="isModify" @click="modifyNoticeArticleAttempt">
          수정
        </MDBBtn>
        <MDBBtn color="success" v-if="!isModify" @click="writeNoticeArticleAttempt">
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