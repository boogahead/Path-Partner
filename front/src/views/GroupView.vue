<script setup>
import {
  MDBAccordion,
  MDBAccordionItem,
  MDBCard, MDBCardBody,
  MDBTabItem,
  MDBTabNav,
  MDBTabs
} from "mdb-vue-ui-kit";
import {ref} from "vue";
import GroupListItem from "@/components/Group/GroupItem/GroupListItem.vue";

const activeItem = ref('collapseOne');
</script>

<template>
  <div class="mb-3">
    <div class="p-5 text-center bg-gradient bg-warning shadow-3 text-light mb-3 bg-opacity-75">
      <h1 class="mb-3">그룹 관리</h1>
    </div>
    <div class="container">
      <MDBCard>
        <MDBCardBody>
          <MDBAccordion v-model="activeItem" borderless>
            <GroupListItem/>
          </MDBAccordion>
        </MDBCardBody>
      </MDBCard>
    </div>
  </div>
</template>

<style scoped>

</style>