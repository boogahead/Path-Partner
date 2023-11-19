<script setup>

import {
  MDBBadge,
  MDBBtn,
  MDBCard,
  MDBCardBody,
  MDBTabItem,
  MDBTable,
  MDBTabNav,
  MDBTabs,
  MDBTabContent,
  MDBTabPane
} from "mdb-vue-ui-kit";
import {ref} from "vue";

const tab = ref("friend");
</script>

<template>
  <div class="mb-3">
    <div class="p-5 text-center bg-gradient bg-danger shadow-3 text-light mb-3 bg-opacity-75">
      <h1 class="mb-3">친구 관리</h1>
    </div>
    <div class="container">
      <MDBCard>

        <MDBCardBody>
          <MDBTabs v-model="tab">
            <MDBTabNav justify tabsClasses="mb-3">
              <MDBTabItem tabId="friend">친구</MDBTabItem>
              <MDBTabItem tabId="send">보낸 요청</MDBTabItem>
              <MDBTabItem tabId="receive">받은 요청</MDBTabItem>
            </MDBTabNav>
            <MDBTabContent>
              <MDBTabPane tabId="friend">
                <MDBTable align="middle" class="mb-3 bg-white" hover>
                  <thead class="bg-light">
                  <tr>
                    <th>이름</th>
                    <th>상태</th>
                    <th>관리</th>
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
                  </tbody>
                </MDBTable>
              </MDBTabPane>
              <MDBTabPane tabId="send">

              </MDBTabPane>
              <MDBTabPane tabId="receive">

              </MDBTabPane>
            </MDBTabContent>

          </MDBTabs>
        </MDBCardBody>
      </MDBCard>
    </div>
  </div>
</template>

<style scoped>

</style>