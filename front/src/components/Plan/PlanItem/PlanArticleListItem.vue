<script setup>
import {MDBCard, MDBCardBody} from "mdb-vue-ui-kit";

const props = defineProps({
  attraction:Object
})
</script>

<template>
  <MDBCard class="mb-3">
    <MDBCardBody>
      <div class="container">
        <div class="row">
          <div class="col-4">
            <img :src="attraction.firstImage" alt="..." class="rounded img-fluid" onerror="this.style.display='none'"/>
          </div>
          <div class="col">
            <h4>{{attraction.title}}</h4>
          </div>
        </div>
        <div class="row text-muted">
          <p>{{attraction.addr1 + attraction.addr2}}</p>
        </div>
      </div>
    </MDBCardBody>
  </MDBCard>
</template>

<style scoped>

</style>