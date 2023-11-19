<script setup>

import {MDBCard, MDBCardBody, MDBAccordion, MDBAccordionItem } from "mdb-vue-ui-kit";
import {ref} from "vue";
import PlanDetailItem from "@/components/Plan/PlanItem/PlanDetailItem.vue";
import {useRoute} from "vue-router";

const route = useRoute();

const planArticleId = route.params.planArticleId;
const activeItem = ref('');
const planDetails = ref([
  {contentId : 1},
  {contentId : 2},
  {contentId : 3},
]);
</script>

<template>
  <div class="container">
    <MDBCard>
      <MDBCardBody>
        <MDBAccordion v-model="activeItem" borderless>
          <PlanDetailItem v-for="detail in planDetails" :plan-detail="detail" :key="detail.contentId"/>
        </MDBAccordion>
      </MDBCardBody>
    </MDBCard>
  </div>
</template>

<style scoped>

</style>