<script setup>

import {MDBBadge, MDBBtn} from "mdb-vue-ui-kit";

defineProps({
  article: Object
})
</script>

<template>
  <tr>
    <td>
      <div class="d-flex align-items-center">
        <div style="width: 45px; height: 45px"
             class="rounded-circle bg-secondary"/>
        <div class="ms-3">
          <p class="fw-bold mb-1">관리자</p>
        </div>
      </div>
    </td>
    <td>
      <RouterLink :to="{name:'notice_detail', params:{noticeArticleId:article.noticeArticleId}}">
        <p class="fw-normal mb-1">{{ article.title }}</p>
      </RouterLink>
    </td>
<!--    <td>-->
<!--      <MDBBadge badge="danger" pill class="d-inline">처리중</MDBBadge>-->
<!--    </td>-->
    <td>
      <p class="fw-normal mb-1">{{ article.creationDate }}</p>
    </td>
  </tr>
</template>

<style scoped>

</style>