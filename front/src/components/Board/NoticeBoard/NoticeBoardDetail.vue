<script setup>
import {computed, onMounted, ref} from "vue";
import {deleteArticle, getArticle, getCommentList, registComment} from "@/api/BoardAPI";
import {useRoute, useRouter} from "vue-router";
import {
  MDBCard,
  MDBCardBody,
  MDBCardTitle,
  MDBCardText,
  MDBBtn,
  MDBBadge, MDBAccordion, MDBAccordionItem, MDBTextarea
} from "mdb-vue-ui-kit";
import CommentItem from "@/components/Board/BoardItem/CommentItem.vue";
import {useLoginUserStore} from "@/stores/loginUser";
import {storeToRefs} from "pinia";
import {deleteNoticeArticle, getNoticeArticle} from "@/api/NoticeAPI";

const route = useRoute();
const router = useRouter();

const {noticeArticleId} = route.params;
const noticeArticleDetail = ref({});

const loginUser = useLoginUserStore();
const {loginUserInfo} = storeToRefs(loginUser);

onMounted(async () => {
  await getNoticeArticle(noticeArticleId, (response) => {
    noticeArticleDetail.value = response.data
  })
})

const deleteNoticeArticleAttempt = () => {
  deleteNoticeArticle(noticeArticleDetail.value.noticeArticleId, (response) => {
      router.back()
  })
}

</script>

<template>
  <MDBCard class="mb-4">
    <MDBCardBody>
      <MDBCardTitle>
        <div class="d-flex mb-2 justify-content-center">
          <h1 class="m-auto">{{ noticeArticleDetail.title }}</h1>
          <div>
            <MDBBtn color="primary"
                    @click="router.push({name:'notice_article_modify', params:{noticeArticleId: noticeArticleDetail.noticeArticleId}})">
              수정
            </MDBBtn>
            <MDBBtn color="danger" @click="deleteNoticeArticleAttempt">
              삭제
            </MDBBtn>
          </div>
        </div>
      </MDBCardTitle>
      <hr class="hr">
      <MDBCardText class="p-3">
        {{ noticeArticleDetail.content }}
      </MDBCardText>
      <hr class="hr">
    </MDBCardBody>
  </MDBCard>
</template>

<style scoped>

</style>