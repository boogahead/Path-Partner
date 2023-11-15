<script setup>
import {RouterLink, RouterView} from 'vue-router'
import NavBar from "@/components/Commons/NavBar.vue";
</script>

<template>
  <NavBar/>
  <RouterView/>
</template>

<style scoped>
#app {
  font-family: Roboto, Helvetica, Arial, sans-serif;
}
</style>
