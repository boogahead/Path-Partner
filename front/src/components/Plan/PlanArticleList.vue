<script setup>
import {MDBCard, MDBCardBody} from "mdb-vue-ui-kit";
import PlanArticleListItem from "@/components/Plan/PlanItem/PlanArticleListItem.vue";

const props = defineProps({
  attractions: Object
})

const emits = defineEmits(['deleteAttractionEvent'])

const deleteAttractionEventHandler = (contentId) => {
  emits('deleteAttractionEvent', contentId)
}
</script>

<template>
  <PlanArticleListItem v-for="attraction in attractions" :key="attraction.contentId"
                       :attraction="attraction" @deleteAttractionEvent="deleteAttractionEventHandler"/>
</template>

<style scoped>

</style>