import {localAxios} from "@/utils/API-commons";

const local = localAxios();

const loginUser= sessionStorage.getItem("loginUser")
const {accessToken} = JSON.parse(loginUser);

function getSidoCode(success, fail) {
  local.get(`/area/sido`, {headers: {Authorization: 'Bearer ' + accessToken}}).then(success).catch(fail);
}

function getSiGunGuCode(sidoCode, success, fail) {
  local.get(`/area/gugun/${sidoCode}`, {headers: {Authorization: 'Bearer ' + accessToken}}).then(success).catch(fail);
}

export {
  getSidoCode,
  getSiGunGuCode
}