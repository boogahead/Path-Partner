<script setup>
import {MDBCard, MDBCardBody} from "mdb-vue-ui-kit";
import PlanArticleListItem from "@/components/Plan/PlanItem/PlanArticleListItem.vue";

const props = defineProps({
  attractions: Object
})

</script>

<template>
  <PlanArticleListItem v-for="attraction in attractions" :key="attraction.contentId"
                       :attraction="attraction"/>
</template>

<style scoped>

</style>