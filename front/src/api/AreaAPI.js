import {localAxios} from "@/utils/API-commons";
import {localAuthorizedAxios} from "@/utils/Authorized-commons";

const local = localAuthorizedAxios();

function getSidoCode(success, fail) {
  local.get(`/area/sido`).then(success).catch(fail);
}

function getSiGunGuCode(sidoCode, success, fail) {
  local.get(`/area/gugun/${sidoCode}`).then(success).catch(fail);
}

export {
  getSidoCode,
  getSiGunGuCode
}