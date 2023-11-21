<script setup>
import {ref, watch, onMounted} from "vue";
import {
  MDBBtn,
  MDBInput,
  MDBModal,
  MDBModalBody, MDBModalFooter,
  MDBModalHeader,
  MDBModalTitle
} from "mdb-vue-ui-kit";

var map;
var clusterer;
const positions = ref([]);
const markers = ref([]);
const attractionDetailModal = ref(false);

// modal
const modalTitle = ref("");
const modalImgSrc = ref("");
const modalAltImgSrc = ref("");

const props = defineProps({
  searchResult: Object,
  selectedAttraction: Object
});

watch(
    () => props.selectedAttraction,
    () => {
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(props.selectedAttraction.latitude,
          props.selectedAttraction.longitude);

      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      map.panTo(moveLatLon);
    },
    {deep: true}
);

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
        import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

watch(
    () => props.searchResult.value,
    () => {
      positions.value = [];
      props.searchResult.forEach((attraction) => {
        let obj = {};
        obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);
        obj.title = attraction.title;
        obj.info = attraction;

        positions.value.push(obj);
      });
      loadMarkers();
    },
    {deep: true}
);

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);
  clusterer = new kakao.maps.MarkerClusterer({
    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
    minLevel: 7, // 클러스터 할 최소 지도 레벨
  });

  // loadMarkers();
};

const loadMarkers = () => {
  // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
  deleteMarkers();

  // 마커 이미지를 생성합니다
  //   const imgSrc = require("@/assets/map/markerStar.png");
  // 마커 이미지의 이미지 크기 입니다
  //   const imgSize = new kakao.maps.Size(24, 35);
  //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

  // 마커를 생성합니다
  markers.value = [];
  positions.value.forEach((position) => {
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: position.latlng, // 마커를 표시할 위치
      title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      // image: markerImage, // 마커의 이미지
    });

    kakao.maps.event.addListener(marker, 'click', () => {
      props.selectedAttraction = position.info;
      modalTitle.value = position.info.title;
      modalImgSrc.value = position.info.firstImage
      modalAltImgSrc.value = position.info.firstImage2
      attractionDetailModal.value = true;
    })
    markers.value.push(marker);
    clusterer.addMarker(marker);
  });

  // 4. 지도를 이동시켜주기
  // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
  const bounds = positions.value.reduce(
      (bounds, position) => bounds.extend(position.latlng),
      new kakao.maps.LatLngBounds()
  );

  map.setBounds(bounds);
};

const deleteMarkers = async () => {
  await clusterer.clear();
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
};
</script>

<template>
  <div>
    <div id="map"></div>

    <MDBModal
        id="attractionDetailModal"
        v-model="attractionDetailModal"
    >
      <MDBModalHeader>
        <MDBModalTitle> {{ modalTitle}}</MDBModalTitle>
      </MDBModalHeader>
      <MDBModalBody>
        <div class="container">
          <img :src="modalImgSrc" :alt="modalAltImgSrc" class="img-thumbnail"/>
        </div>
        <hr class="hr"/>
        <div>
          text
        </div>
      </MDBModalBody>
      <MDBModalFooter>
        <MDBBtn outline="primary">LOGIN</MDBBtn>
        <MDBBtn color="primary">RESET PASSWORD</MDBBtn>
        <MDBBtn color="danger">CANCEL</MDBBtn>
      </MDBModalFooter>
    </MDBModal>
  </div>

</template>

<style>
#map {
  width: 100%;
  height: 700px;
}
</style>
