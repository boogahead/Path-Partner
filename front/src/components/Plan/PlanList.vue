<script setup>
import {MDBIcon, MDBRow, MDBBtn, MDBCard, MDBCardBody} from "mdb-vue-ui-kit";
import PlanListItem from "@/components/Plan/PlanItem/PlanListItem.vue";
import {ref} from "vue";

const planList = ref([
  {planArticleId: 'test1'},
  {planArticleId: 'test2'},
  {planArticleId: 'test3'},
])
</script>

<template>
  <div class="container">
    <MDBCard>
      <MDBCardBody>
        <MDBRow :cols="['1','md-3']" class="g-4 mb-3">
          <PlanListItem v-for="plan in planList" :key="plan.planArticleId"/>
        </MDBRow>
        <div class="text-center mt-3">
          <MDBBtn color="primary" floating>
            <MDBIcon icon="plus" size="2x"/>
          </MDBBtn>
        </div>
      </MDBCardBody>
    </MDBCard>
  </div>
</template>

<style scoped>

</style>