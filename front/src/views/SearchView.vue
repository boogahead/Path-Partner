<script setup>

import {MDBCard, MDBCardBody, MDBCheckbox, MDBBtn} from "mdb-vue-ui-kit";
import {computed, onMounted, ref, watch} from "vue";
import {getSidoCode, getSiGunGuCode} from "@/api/AreaAPI";
import KakaoMap from "@/components/Map/KakaoMap.vue";

const sidoSelected = ref("0");
const sidoOptions = ref([]);
const sigunguSelected = ref("0");
const sigunguOptions = ref([]);

// 시도코드 가져오기
onMounted(async () => {
  await getSidoCode((response) => {
    sidoOptions.value = response.data;
  })
})

// 시도코드 선택시 시군구 코드 변경
watch(sidoSelected, async () => {
  await getSiGunGuCode(sidoSelected.value, (response) => {
    sigunguOptions.value = response.data;
  })
})

const sigunguOptionsComputed = computed(() => {
  return sigunguOptions.value;
})


</script>

<template>
  <div class="mb-3">
    <div class="p-5 text-center bg-gradient bg-info shadow-3 text-light mb-3 bg-opacity-75 ">
      <h1 class="mb-3">관광지 검색</h1>
    </div>
    <div class="container">
      <div class="mb-3">
        <MDBCard>
          <MDBCardBody>
            <div class="container">
              <div class="row justify-content-center">
                <div class="col-2">
                  <select class="form-select" aria-label="시도" v-model="sidoSelected">
                    <option selected value="0">시도</option>
                    <option v-for="option in sidoOptions" :key="option.sidoCode"
                            :value="option.sidoCode">
                      {{ option.sidoName }}
                    </option>
                  </select>
                </div>
                <div class="col-2">
                  <select class="form-select" aria-label="시군구" v-model="sigunguSelected">
                    <option selected value="0">시군구</option>
                    <option v-for="option in sigunguOptionsComputed" :key="option.gugunCode">
                      {{ option.gugunName }}
                    </option>
                  </select>
                </div>
                <div class="col p-2">
                  <MDBCheckbox label="관광지" inline/>
                  <MDBCheckbox label="문화시설" inline/>
                  <MDBCheckbox label="행사/공연/축제" inline/>
                  <MDBCheckbox label="여행코스" inline/>
                  <MDBCheckbox label="레포츠" inline/>
                  <MDBCheckbox label="숙박" inline/>
                  <MDBCheckbox label="쇼핑" inline/>
                  <MDBCheckbox label="음식점" inline/>
                </div>
                <MDBBtn color="primary" class="col-1">조회하기</MDBBtn>
              </div>
            </div>
          </MDBCardBody>
        </MDBCard>
      </div>
      <div class="row justify-content-center">
        <div class="col">
          <MDBCard>
            <MDBCardBody>
              상세 정보
            </MDBCardBody>
          </MDBCard>
        </div>
        <div class="col-8">
          <MDBCard>
            <MDBCardBody>
              <KakaoMap/>
            </MDBCardBody>
          </MDBCard>
        </div>
        <!--        <div class="col">-->
        <!--          <MDBCard>-->
        <!--            <MDBCardBody>-->
        <!--              검색 결과-->
        <!--            </MDBCardBody>-->
        <!--          </MDBCard>-->
        <!--        </div>-->
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>