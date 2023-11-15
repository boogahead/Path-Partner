import {localAxios} from "@/utils/API-commons";

const local = localAxios();

function getSidoCode(success, fail) {
  local.get(`/area/sido`).then(success).catch(fail);
}

function getSiGunGuCode(sidoCode, success, fail) {
  local.get(`/area/gungu`).then(success).catch(fail);
}

export {
  getSidoCode,
  getSiGunGuCode
}