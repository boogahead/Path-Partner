<script setup>

import {
  MDBAccordionItem, MDBTable,
  MDBBtn,
  MDBBadge,
  MDBInput
} from "mdb-vue-ui-kit";
import {ref} from "vue";

const searchInput = ref("");
</script>

<template>
  <MDBAccordionItem headerTitle="그룹 명" collapseId="그룹 아이디">
    <MDBTable align="middle" class="mb-3 bg-white" hover>
      <thead class="bg-light">
      <tr>
        <th>이름</th>
        <th>상태</th>
        <th>추방</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>
          <div class="d-flex align-items-center">
            <img src="https://mdbootstrap.com/img/new/avatars/8.jpg" alt=""
                 style="width: 45px; height: 45px"
                 class="rounded-circle"/>
            <div class="ms-3">
              <p class="fw-bold mb-1">John Doe</p>
              <p class="text-muted mb-0">john.doe@gmail.com</p>
            </div>
          </div>
        </td>
        <td>
          <MDBBadge badge="success" pill class="d-inline">회원</MDBBadge>
        </td>
        <td>
          <MDBBtn color="link" size="sm" rounded>
            Edit
          </MDBBtn>
        </td>
      </tr>
      <tr>
        <td>
          <div class="d-flex align-items-center">
            <img src="https://mdbootstrap.com/img/new/avatars/6.jpg" class="rounded-circle" alt=""
                 style="width: 45px; height: 45px"/>
            <div class="ms-3">
              <p class="fw-bold mb-1">Alex Ray</p>
              <p class="text-muted mb-0">alex.ray@gmail.com</p>
            </div>
          </div>
        </td>
        <td>
          <MDBBadge badge="success" pill class="d-inline">회원</MDBBadge>
        </td>
        <td>
          <MDBBtn color="link" size="sm" rounded class="fw-bold" :ripple="{ color: 'dark' }">
            Edit
          </MDBBtn>
        </td>
      </tr>
      <tr>
        <td>
          <div class="d-flex align-items-center">
            <img src="https://mdbootstrap.com/img/new/avatars/7.jpg" class="rounded-circle" alt=""
                 style="width: 45px; height: 45px"/>
            <div class="ms-3">
              <p class="fw-bold mb-1">Kate Hunington</p>
              <p class="text-muted mb-0">kate.hunington@gmail.com</p>
            </div>
          </div>
        </td>
        <td>
          <MDBBadge badge="warning" pill class="d-inline">수락대기</MDBBadge>
        </td>
        <td>
          <MDBBtn color="link" size="sm" rounded class="fw-bold" :ripple="{ color: 'dark' }">
            Edit
          </MDBBtn>
        </td>
      </tr>
      </tbody>
    </MDBTable>
    <div>
      <div class="row">
        <div class="col-10">
          <MDBInput label="Nick Name" v-model="searchInput"></MDBInput>
        </div>
        <div class="col d-flex justify-content-center">
          <MDBBtn color="warning">초대하기</MDBBtn>
        </div>
      </div>
    </div>
  </MDBAccordionItem>
</template>

<style scoped>

</style>