<script setup>
const props = defineProps({
  info: Object,
})
const profile = 'data:image/png;base64,' + btoa(
    new Uint8Array(props.info.profileImg)
    .reduce((data, byte) => data + String.fromCharCode(byte), ''));

const defaultProfileImgHandler = (event) => {
  event.target.src = "http://localhost/static/images/defaultPicture.jpg"
}
</script>

<template>
  <tr>
    <td>
      <div class="d-flex align-items-center">
        <img :src="profile" alt=""
             style="width: 45px; height: 45px"
             class="rounded-circle"
             @error="defaultProfileImgHandler"
        />
        <div class="ms-3">
          <p class="fw-bold mb-1">
            <i v-if="info.groupMaster" class="fas fas fa-crown text-warning"></i>
            {{ info.nickname }}
          </p>
          <p class="text-muted mb-0">{{ info.email }}</p>
        </div>
      </div>
    </td>
  </tr>
</template>

<style scoped>

</style>