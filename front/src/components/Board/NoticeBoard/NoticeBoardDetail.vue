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

const route = useRoute();
const router = useRouter();

const {noticeArticleId} = route.params;
const noticeArticleDetail = ref({});
const comments = ref([]);
const activeItem = ref('')
const commentContent = ref('');
const loginUserStore = useLoginUserStore();
const {id, name} = storeToRefs(loginUserStore);

onMounted(async () => {
  // await getArticle(noticeArticleId, (response) => {
  //   detail.value = response.data
  // })
  // await getCommentList(noticeArticleId, (response) => {
  //   comments.value = response.data;
  // })
  noticeArticleDetail.value = {
    noticeArticleId: "id",
    title: "공지사항1",
    content: "공지사항 내용1",
    creationDate: "2023-11-16",
    uuid: "작성자uuid",
  }
})

const removeArticle = () => {
  deleteArticle(detail.value.noticeArticleId, (response) => {
    if (response.status === 200) {
      router.back()
    } else {
      alert("삭제에 실패했습니다. 나중에 다시 시도해 주세요.")
    }
  })
}

const writeComment = () => {
  const comment = {
    articleNo: detail.value.noticeArticleId,
    userId: id.value,
    userName: name.value,
    content: commentContent.value
  }

  registComment(comment, (response) => {
    if(response.status === 200) {
      // commentContent.value = '';
      // comments.value.unshift(comment)
      router.go(0);
    } else {
      alert("답변 등록에 실패했습니다. 잠시후 다시 시도해 주세요.")
    }
  })
}

</script>

<template>
  <MDBCard class="mb-4">
    <MDBCardBody>
      <MDBCardTitle>
        <div class="d-flex mb-2 justify-content-center">
<!--          <div>-->
<!--            <h4>-->
<!--              <MDBBadge color="danger" class="em">처리중</MDBBadge>-->
<!--            </h4>-->
<!--          </div>-->
          <h1 class="m-auto">{{ noticeArticleDetail.title }}</h1>
<!--          <div>-->
<!--            <MDBBtn color="primary"-->
<!--                    @click="router.push({name:'notice_article_modify', params:{articleId: detail.articleNo}})">-->
<!--              수정-->
<!--            </MDBBtn>-->
<!--            <MDBBtn color="danger" @click="removeArticle">-->
<!--              삭제-->
<!--            </MDBBtn>-->
<!--          </div>-->
        </div>
      </MDBCardTitle>
      <hr class="hr">
      <MDBCardText class="p-3">
        {{ noticeArticleDetail.content }}
      </MDBCardText>
      <hr class="hr">
<!--      <div class="d-flex justify-content-around">-->
<!--        <MDBTextarea counter :max-length="200" rows="1" label="Comment"-->
<!--                     v-model="commentContent" class="p-3"/>-->
<!--        <MDBBtn color="info" @click="writeComment">답변 쓰기</MDBBtn>-->
<!--      </div>-->
    </MDBCardBody>
  </MDBCard>
<!--  <MDBAccordion v-model="activeItem">-->
<!--    <MDBAccordionItem icon="fas fa-question-circle fa-sm me-2 opacity-70"-->
<!--                      :header-title="`답변 ${comments.length}`" collapse-id="comments">-->
<!--      <CommentItem v-for="comment in comments" :key="comment.commentId" :comment="comment"/>-->
<!--    </MDBAccordionItem>-->
<!--  </MDBAccordion>-->
</template>

<style scoped>

</style>