<script setup>

import InputForm from "@/components/Board/InputForm.vue";
import {useRoute} from "vue-router";
import {onMounted, ref} from "vue";
import {getArticle} from "@/api/BoardAPI";

const route = useRoute();
const {articleno} = route.params;
const detail = ref({});

onMounted(async () => {
  await getArticle(articleno, (response) => {
    detail.value = response.data
    console.log("detail",detail.value)
  })
})

</script>

<template>
  <InputForm :isModify="true" :detail="detail"/>
</template>

<style scoped>

</style>