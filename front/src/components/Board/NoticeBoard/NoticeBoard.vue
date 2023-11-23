<script setup>
import {MDBTable, MDBBtn} from "mdb-vue-ui-kit";
import {onMounted, ref} from "vue";
import {getArticleList} from "@/api/BoardAPI";
import router from "@/router/index";
import NoticeBoardItem from "@/components/Board/NoticeBoard/NoticeBoardItem/NoticeBoardItem.vue";
import {getAllNoticeArticle} from "@/api/NoticeAPI";

const noticeArticleList = ref([]);

onMounted(() => {
  reload();
})

const reload = () => {
  getAllNoticeArticle((response) => {
    noticeArticleList.value = response.data;
  }, (error) => {
    alert("공지글을 불러오지 못했습니다. 잠시후 다시 이용해주세요.")
  })
}

</script>

<template>
  <div class="d-flex justify-content-end mb-3">
    <MDBBtn color="info" @click="router.push({name:'notice_article_write'})">글 작성하기</MDBBtn>
  </div>
  <MDBTable class="align-middle mb-5 bg-white">
    <thead class="bg-light">
    <tr>
      <th>작성자</th>
      <th>제목</th>
      <th>작성일</th>
    </tr>
    </thead>
    <tbody>
    <NoticeBoardItem v-for="article in noticeArticleList" :key="article.noticeArticleId" :article="article"/>
    </tbody>
  </MDBTable>
</template>

<style scoped>

</style>