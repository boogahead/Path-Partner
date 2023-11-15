<script setup>

import {
  MDBCard,
  MDBCardBody,
  MDBCardImg,
  MDBCardText,
  MDBCardTitle,
  MDBCol,
  MDBBadge,
  MDBPopover,
  MDBSpinner,
  MDBCarousel,
  MDBBtn
} from "mdb-vue-ui-kit";
import {ref} from "vue";
import {useRouter} from "vue-router";

const props = defineProps({
  plan: Object
})

const router = useRouter();
const popover = ref(false);

const items = [
  {
    src: "https://mdbootstrap.com/img/Photos/Slides/img%20(15).webp",
    alt: "...",
  },
  {
    src: "https://mdbootstrap.com/img/Photos/Slides/img%20(22).webp",
    alt: "...",
  },
  {
    src: "https://mdbootstrap.com/img/Photos/Slides/img%20(23).webp",
    alt: "...",
  }
];
const carousel = ref(0);
</script>

<template>
  <MDBCol>
    <MDBCard>
      <MDBCarousel
          v-model="carousel"
          :items="items"
          fade
      />
<!--      <MDBCardImg src="https://mdbootstrap.com/img/new/standard/city/041.webp" top alt="..."/>-->
      <MDBCardBody>
        <MDBCardTitle>계획 제목</MDBCardTitle>
        <MDBCardText>
          <MDBPopover v-model="popover">
            <template #reference>
              <MDBBadge color="primary" @click="popover = !popover">그룹명</MDBBadge>
            </template>
            <template #header>그룹원</template>
            <template #body>
              <MDBSpinner/>
            </template>
          </MDBPopover>
          <hr class="hr"/>
          <div class="d-flex justify-content-between mt-3">
            <span class="mb-0">0000.00.00 ~ 0000.00.00</span>
            <MDBBtn color="secondary" rounded size="sm" @click="router.push({name:'plan_detail', params:{plan_article_id:1}})">자세히 보기</MDBBtn>
          </div>
        </MDBCardText>
      </MDBCardBody>
    </MDBCard>
  </MDBCol>
</template>

<style scoped>

</style>