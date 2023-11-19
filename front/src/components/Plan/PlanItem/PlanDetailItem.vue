<script setup>

import {
  MDBAccordionItem,
  MDBCard,
  MDBCardBody,
  MDBCardTitle,
  MDBCardText,
  MDBBtn
} from "mdb-vue-ui-kit";

const props = defineProps({
  planDetail:Object
})
</script>

<template>
  <MDBAccordionItem headerTitle="여행지 이름" :collapseId="planDetail.contentId">
    <div class="d-flex">
      <div class="container">
        <div class="row">
          <div class="col">
            <img
                src="https://mdbootstrap.com/img/new/standard/city/041.webp"
                class="img-thumbnail"
                alt="..."
            />
          </div>
          <div class="col">
            <MDBCard style="min-height: 387px">
              <MDBCardBody>
                <MDBCardTitle>여행지 주소 및 풀네임</MDBCardTitle>
                <hr class="hr"/>
                <MDBCardText>
                  대충 설명, 일정, 메모 등
                </MDBCardText>
                <hr class="hr"/>
              </MDBCardBody>
            </MDBCard>
          </div>
        </div>
      </div>
    </div>
  </MDBAccordionItem>
</template>

<style scoped>

</style>