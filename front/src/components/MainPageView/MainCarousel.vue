<script>
import { ref } from "vue";
import { MDBCarousel } from "mdb-vue-ui-kit";
export default {
  components: {
    MDBCarousel
  },
  setup() {
    const carouselItems = [
      {
        src: "https://mdbootstrap.com/img/Photos/Slides/img%20(15).webp",
        alt: "...",
        label: "Enjoy Trip!!",
        caption: "새로운 여행 장소를 찾아보세요!!"
      },
      {
        src: "https://mdbootstrap.com/img/Photos/Slides/img%20(22).webp",
        alt: "...",
        label: "나만의 여행 계획!",
        caption: "친구들과 실시간으로 여행 계획을 세워보세요!!"
      },
      {
        src: "https://mdbootstrap.com/img/Photos/Slides/img%20(23).webp",
        alt: "...",
        label: "다양한 여행 후기!",
        caption:
            "다른 사람들이 남긴 여행 후기를 확인해보세요!!"
      }
    ];
    const carousel = ref(0);
    return {
      carouselItems,
      carousel
    };
  }
};
</script>

<template>
  <MDBCarousel
      v-model="carousel"
      :items="carouselItems"
      fade
  />
</template>

<style scoped>

</style>