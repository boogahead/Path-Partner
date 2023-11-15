<script setup>

import {MDBCard, MDBCardBody, MDBCheckbox} from "mdb-vue-ui-kit";
import {ref} from "vue";

const sidoSelected = ref("시도");
const sidoOptions = ref([{sidoCode: 11, sidoName: '서울'}]);
const sigunguSelected = ref("시군구");
const sigunguOptions = ref([]);

</script>

<template>
  <div>
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
                    <option selected>시도</option>
                    <option v-for="option in sidoOptions" :key="option.sidoCode">
                      {{ option.sidoName }}
                    </option>
                  </select>
                </div>
                <div class="col-2">
                  <select class="form-select" aria-label="시군구" v-model="sigunguSelected">
                    <option selected>시군구</option>
                    <option v-for="option in sigunguOptions" :key="option.gugunCode">
                      {{ option.gugunName }}
                    </option>
                  </select>
                </div>

              </div>
              <div class="col-1">

              </div>
              <div>
                <MDBCheckbox></MDBCheckbox>
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
              지도
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