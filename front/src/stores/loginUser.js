import {defineStore} from "pinia";
import {ref} from "vue";

export const useLoginUserStore = defineStore('loginUser', () => {
  const loginUserInfo = ref({});
  const isLogin = ref(false);
  const accessToken = ref("");
  const refreshToken = ref("");
  return {
    loginUserInfo,
    accessToken,
    refreshToken,
    isLogin
  }
}, {persist: {storage: sessionStorage}})