<script setup>
import {MDBPageItem, MDBPageNav, MDBPagination} from "mdb-vue-ui-kit";
</script>

<template>
  <nav>
    <MDBPagination class="justify-content-center">
      <MDBPageNav prev icon></MDBPageNav>
      <MDBPageItem href="#">1</MDBPageItem>
      <MDBPageItem icon href="#">2</MDBPageItem>
      <MDBPageItem href="#">3</MDBPageItem>
      <MDBPageNav next icon></MDBPageNav>
    </MDBPagination>
  </nav>
</template>

<style scoped>

</style>