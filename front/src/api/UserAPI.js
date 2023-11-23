import {localAuthorizedAxios} from "@/utils/Authorized-commons";

const local = localAuthorizedAxios();

function getUserInfo(uuid, success, fail) {
  local.get(`/user/${uuid}`).then(success).catch(fail);
}

function deleteUser(uuid, success, fail) {
  local.delete(`/user/${uuid}`).then(success).catch(fail);
}

function withdrawal(success, fail) {
  local.delete(`/user`).then(success).catch(fail);
}

function modifyUser(user, success, fail) {
  local.put(`/user`, user).then(success).catch(fail);
}

function findAllUserByNickname(nickname, success, fail) {
  local.get(`/user/find/${nickname}`).then(success).catch(fail);
}

export {
  getUserInfo,
  deleteUser,
  withdrawal,
  modifyUser,
  findAllUserByNickname
}