<script setup>
import {MDBTable, MDBBtn, MDBBadge} from "mdb-vue-ui-kit";
import Pagination from "@/components/Board/Pagination.vue";
import QnABoardItem from "@/components/Board/BoardItem/QnABoardItem.vue";
import {onMounted, ref} from "vue";
import {getArticleList, registArticle} from "@/api/BoardAPI";
import router from "@/router/index";

const articles = ref([]);

onMounted(async () => {
  await getArticleList(null, (response) => {
    articles.value = response.data.articles
    console.log(response.data)
  })
})

</script>

<template>
  <div class="d-flex justify-content-end mb-3">
    <MDBBtn color="info" @click="router.push({name:'article-write'})">글 작성하기</MDBBtn>
  </div>
  <MDBTable class="align-middle mb-5 bg-white">
    <thead class="bg-light">
    <tr>
      <th>작성자</th>
      <th>제목</th>
      <th>처리 상태</th>
      <th>작성일</th>
    </tr>
    </thead>
    <tbody>
    <QnABoardItem v-for="article in articles" :key="article.articleNo" :article="article"/>
    </tbody>
  </MDBTable>
  <Pagination/>
</template>

<style scoped>

</style>