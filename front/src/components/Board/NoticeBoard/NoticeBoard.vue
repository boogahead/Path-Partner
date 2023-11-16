<script setup>
import {MDBTable, MDBBtn} from "mdb-vue-ui-kit";
import {onMounted, ref} from "vue";
import {getArticleList} from "@/api/BoardAPI";
import router from "@/router/index";
import NoticeBoardItem from "@/components/Board/NoticeBoard/NoticeBoardItem/NoticeBoardItem.vue";

const articles = ref([]);

onMounted(async () => {
  // await getArticleList(null, (response) => {
  //   articles.value = response.data.articles
  //   console.log(response.data)
  // })
  articles.value = [
    {
      noticeArticleId: "id",
      title: "공지사항1",
      content: "공지사항 내용1",
      creationDate: "2023-11-16",
      uuid: "작성자uuid",
    }
  ]
})

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
    <NoticeBoardItem v-for="article in articles" :key="article.noticeArticleId" :article="article"/>
    </tbody>
  </MDBTable>
</template>

<style scoped>

</style>