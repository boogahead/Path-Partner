import {defineStore} from "pinia";
import {ref} from "vue";

export const useLoginUserStore = defineStore('loginUser', () => {
  const id = ref("test");
  const name = ref("test")
  const isLogin = ref(true)

  return {
    id,
    name,
    isLogin
  }
})