<script setup>

import {
  MDBCard,
  MDBCardBody,
  MDBCheckbox,
  MDBBtn,
  MDBInput
} from "mdb-vue-ui-kit";
import {
  computed,
  onBeforeMount,
  onMounted,
  ref,
  watch
} from "vue";
import {getSidoCode, getSiGunGuCode} from "@/api/AreaAPI";
import KakaoMap from "@/components/Map/KakaoMap.vue";
import {search} from "@/api/AttractionAPI";
import qs from "qs";
import PlanArticleList from "@/components/Plan/PlanArticleList.vue";
import {useRoute, useRouter} from "vue-router";
import {getPlanArticle, updatePlan} from "@/api/PlanArticleAPI";
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

const sidoSelected = ref("0");
const sidoOptions = ref([]);
const sigunguSelected = ref("0");
const sigunguOptions = ref([]);
const contentTypeId = ref([false, false, false, false, false, false, false, false]);
const searchResult = ref([]);
const selectedAttractionList = ref([]);
const planTitle = ref("")
const imgSrc = ref([])
const isTitleEdit = ref(false)

const route = useRoute()
const router = useRouter()
const stompClient = ref(null);

onMounted(async () => {
  await getSidoCode((response) => {
    sidoOptions.value = response.data;
  })

  await getPlanArticle(route.params.planArticleId, (response) => {
    planTitle.value = response.data.planTitle;
    selectedAttractionList.value = [...JSON.parse(response.data.plan)];
    imgSrc.value = [...JSON.parse(response.data.imgSrc)];
  })

  const socket = new SockJS('/real-time-websocket');
  stompClient.value = new Client({
    webSocketFactory: () => socket,
    debug: (str) => console.log(str),
    onConnect: () => {
      stompClient.value.subscribe('/topic/planArticle/' + route.params.planArticleId, (message) => {
        const updatedPlan = JSON.parse(message.body);
        selectedAttractionList.value = updatedPlan.plan;
        imgSrc.value = updatedPlan.imgSrc;
      });
    }
  });
  stompClient.value.activate();
})

// 시도코드 선택시 시군구 코드 변경
watch(sidoSelected, () => {
  getSiGunGuCode(sidoSelected.value, (response) => {
    sigunguOptions.value = response.data;
  })
})

const sigunguOptionsComputed = computed(() => {
  return sigunguOptions.value;
})

const searchAttempt = async () => {
  const contentTypeValues = [12, 14, 15, 25, 28, 32, 38, 39]
  const contentSelected = contentTypeValues.filter((value, index) => contentTypeId.value[index])
  const params = {
    sidoCode: sidoSelected.value,
    sigunguCode: sigunguSelected.value,
    contentType: contentSelected
  }

  await search(params, (response) => {
    searchResult.value = response.data
  }, ({response}) => {
    alert("지금은 사용할 수 없습니다. 나중에 다시 시도해주세요.")
  })
}

const addAttractionHandler = (data) => {
  const temp = selectedAttractionList.value.filter(
      (attraction) => attraction.contentId === data.contentId)

  if (temp.length < 1) {
    selectedAttractionList.value.unshift(data);
    if(data.firstImage != null && data.firstImage !== "") {
      imgSrc.value.unshift({
        src:data.firstImage,
        alt:data.title,
        contentId:data.contentId
      })
    }
  }
}

const attractionListComputed = computed(() => {
  return selectedAttractionList.value;
})

watch([planTitle, selectedAttractionList, imgSrc], () => {
  const planArticle = {
    planArticleId: route.params.planArticleId,
    plan: JSON.stringify(selectedAttractionList.value),
    planTitle: planTitle.value,
    imgSrc: JSON.stringify(imgSrc.value),
  }

  updatePlan(planArticle, (response) => {
  }, (error) => {
  })
}, {deep: true})

const deleteAttractionEventHandler = (contentId) => {
  selectedAttractionList.value = selectedAttractionList.value.filter(
      (attraction) => attraction.contentId != contentId)
  imgSrc.value = imgSrc.value.filter((img) => img.contentId != contentId)
}

</script>

<template>
  <div class="mb-3">
    <div class="p-5 text-center bg-gradient bg-info shadow-3 text-light mb-3 bg-opacity-75 ">
      <div class="d-flex justify-content-center">
        <div class="me-3" v-if="isTitleEdit" @keyup.enter="isTitleEdit = !isTitleEdit">
          <MDBInput size="lg" label="Plan Title" v-model="planTitle" white ></MDBInput>
        </div>
        <div v-else>
          <h1 class="mb-3 d-inline me-3">{{ planTitle }}</h1>
        </div>
        <i class="fas fa-edit" @click="isTitleEdit = !isTitleEdit"></i>
      </div>
    </div>
    <div class="container mb-3">
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
                    <option v-for="option in sigunguOptionsComputed" :key="option.gugunCode"
                            :value="option.gugunCode">
                      {{ option.gugunName }}
                    </option>
                  </select>
                </div>
                <div class="col p-2">
                  <MDBCheckbox label="관광지" value="12" v-model="contentTypeId[0]" inline/>
                  <MDBCheckbox label="문화시설" value="14" v-model="contentTypeId[1]" inline/>
                  <MDBCheckbox label="행사/공연/축제" value="15" v-model="contentTypeId[2]" inline/>
                  <MDBCheckbox label="여행코스" value="25" v-model="contentTypeId[3]" inline/>
                  <MDBCheckbox label="레포츠" value="28" v-model="contentTypeId[4]" inline/>
                  <MDBCheckbox label="숙박" value="32" v-model="contentTypeId[5]" inline/>
                  <MDBCheckbox label="쇼핑" value="38" v-model="contentTypeId[6]" inline/>
                  <MDBCheckbox label="음식점" value="39" v-model="contentTypeId[7]" inline/>
                </div>
                <MDBBtn color="primary" class="col-1" @click="searchAttempt">조회하기</MDBBtn>
              </div>
            </div>
          </MDBCardBody>
        </MDBCard>
      </div>
      <div class="row justify-content-center">
        <div class="col-4">
          <MDBCard style="height: 748px" class="bg-secondary bg-opacity-25">
            <MDBCardBody class="overflow-y-scroll">
              <div class="text-center" v-if="attractionListComputed.length === 0">
                <h3>여행 장소를 추가해보세요.</h3>
              </div>
              <PlanArticleList :attractions="attractionListComputed" v-else
                               @deleteAttractionEvent="deleteAttractionEventHandler"/>
            </MDBCardBody>
          </MDBCard>
        </div>
        <div class="col-8">
          <MDBCard>
            <MDBCardBody>
              <KakaoMap :searchResult="searchResult" @addAttraction="addAttractionHandler"/>
            </MDBCardBody>
          </MDBCard>
        </div>
      </div>
    </div>
    <div class="container text-end">
      <MDBBtn color="danger" @click="$router.push({name:'plan'})">나가기</MDBBtn>
    </div>
  </div>
</template>

<style scoped>
</style>