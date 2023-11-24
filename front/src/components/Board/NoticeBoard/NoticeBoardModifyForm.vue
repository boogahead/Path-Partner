<script setup>

import InputForm from "@/components/Board/NoticeBoard/InputForm.vue";
import {useRoute} from "vue-router";
import {onMounted, ref} from "vue";
import {getNoticeArticle} from "@/api/NoticeAPI";

const route = useRoute();
const {noticeArticleId} = route.params;
const detail = ref({});

onMounted(async () => {
  await getNoticeArticle(noticeArticleId, (response) => {
    detail.value = response.data
  }, (error) => {
    alert("공지글을 불러오지 못했습니다. 잠시후 다시 시도해주세요.")
  })
})

</script>

<template>
  <InputForm :isModify="true" :detail="detail"/>
</template>

<style scoped>

</style>