import {localAxios} from "@/utils/API-commons";

const local = localAxios();

function login(loginInfo, success, fail) {
  local.post(`/login`, JSON.stringify(loginInfo)).then(success).catch(fail);
}

function register(registerInfo, success, fail) {
  local.post(`/user`, JSON.stringify(registerInfo)).then(success).catch(fail)
}

export {
  login,
  register
}