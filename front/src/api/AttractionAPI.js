import {localAxios} from "@/utils/API-commons";
import {localAuthorizedAxios} from "@/utils/Authorized-commons";

const local = localAuthorizedAxios();

function search(params, success, fail) {
  local.get(`/attraction`, {params}).then(success).catch(fail);
}

export {
  search,
}