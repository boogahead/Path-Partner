<script setup>
import {MDBCard, MDBCardBody, MDBBtnClose, MDBCardHeader} from "mdb-vue-ui-kit";

const props = defineProps({
  attraction: Object
})

const emits = defineEmits(["deleteAttractionEvent"])

</script>

<template>
  <MDBCard class="mb-3">
    <MDBCardHeader>
      <div class="row justify-content-end">
        <MDBBtnClose @click="$emit('deleteAttractionEvent', attraction.contentId)"/>
      </div>
    </MDBCardHeader>
    <MDBCardBody>
      <div class="container p-1">
        <div class="row mb-3">
          <div class="col-4">
            <img :src="attraction.firstImage" alt="..." class="rounded img-fluid"
                 onerror="this.style.display='none'"/>
          </div>
          <div class="col">
            <h4>{{ attraction.title }}</h4>
          </div>
        </div>
        <div class="row text-muted">
          <p>{{ attraction.addr1 + attraction.addr2 }}</p>
        </div>
      </div>
    </MDBCardBody>
  </MDBCard>
</template>

<style scoped>

</style>