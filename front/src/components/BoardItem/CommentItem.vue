<script setup>
import {MDBCardTitle, MDBCardBody, MDBCard, MDBBtn} from "mdb-vue-ui-kit";
import {ref} from "vue";
import {deleteComment} from "@/api/BoardAPI";
import {useRouter} from "vue-router";

const props = defineProps({
  comment: Object
})

const router = useRouter();
const removeComment = () => {
  console.log(props.comment)
  deleteComment(props.comment.commentNo, (response) => {
    if(response.status === 200) {
      router.go(0);
    } else {
      alert("답변 삭제에 실패했습니다. 잠시후 다시 이용해 주세요.")
    }
  })
}


</script>

<template>
  <MDBCard class="mb-2">
    <MDBCardBody class="bg-primary bg-gradient bg-opacity-25">
      <MDBCardTitle>
        <div class="d-flex mb-2 justify-content-between">
          <div class="d-flex ju">
            <div style="width: 45px; height: 45px"
                 class="rounded-circle bg-success"/>
            <div class="ms-3">
              <!--          <p sclass="fw-bold mb-1">{{ comment.userName }}</p>-->
              <p class="fw-bold mb-1">{{comment.userName}}</p>
            </div>
          </div>
          <div>
<!--            <MDBBtn color="primary">-->
<!--              수정-->
<!--            </MDBBtn>-->
            <MDBBtn color="danger" @click="removeComment">
              삭제
            </MDBBtn>
          </div>
        </div>
      </MDBCardTitle>
      {{comment.content}}
    </MDBCardBody>
  </MDBCard>

</template>

<style scoped>

</style>