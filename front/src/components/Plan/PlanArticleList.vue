<script setup>
import {MDBCard, MDBCardBody} from "mdb-vue-ui-kit";
import PlanArticleListItem from "@/components/Plan/PlanItem/PlanArticleListItem.vue";
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import {onMounted, ref} from "vue";

const props = defineProps({
  attractions: Object,
  groupId: String
})

const emits = defineEmits(['deleteAttractionEvent'])

const deleteAttractionEventHandler = (contentId) => {
  emits('deleteAttractionEvent', contentId)
}

const stompClient = ref(null);

onMounted(() => {
  const socket = new SockJS('ws://localhost:5173/real-time-websocket');
  stompClient.value = new Client({
    webSocketFactory: () => socket,
    debug: (str) => console.log(str),
    onConnect: () => {
      stompClient.value.subscribe('/topic/planArticle' + props.groupId, (message) => {
        // Handle the message
        // For example, update the attractions list
        props.attractions = JSON.parse(message.body);
      });
    }
  });
  stompClient.value.activate();
})
</script>

<template>
  <PlanArticleListItem v-for="attraction in attractions" :key="attraction.contentId"
                       :attraction="attraction" @deleteAttractionEvent="deleteAttractionEventHandler"/>
</template>

<style scoped>

</style>