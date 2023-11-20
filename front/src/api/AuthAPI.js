import {localAxios} from "@/utils/API-commons";

const local = localAxios();

function login(loginInfo, success, fail) {
  local.post(`/auth/login`, JSON.stringify(loginInfo)).then(success).catch(
      fail);
}

function logout(success, fail) {
  const loginUser= sessionStorage.getItem("loginUser")
  const {accessToken} = JSON.parse(loginUser);
  local.get(`/auth/logout`, {headers: {Authorization: 'Bearer ' + accessToken}}).then(
      success).catch(fail);
}

function register(registerInfo, success, fail) {
  local.post(`/auth/register`, JSON.stringify(registerInfo)).then(
      success).catch(fail);
}

function findIdByEmail(email, success, fail) {
  local.get(`/auth/${email}`).then(success).catch(fail);
}

function resetPasswordByIdAndEmail(resetInfo, success, fail) {
  local.post(`/auth/password`, JSON.stringify(resetInfo)).then(success).catch(
      fail);
}

export {
  login,
  logout,
  register,
  findIdByEmail,
  resetPasswordByIdAndEmail
}