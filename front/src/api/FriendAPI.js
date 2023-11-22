import {localAxios} from "@/utils/API-commons";
import {localAuthorizedAxios} from "@/utils/Authorized-commons";

const local = localAuthorizedAxios();

function getMyFriendList(success, fail) {
  local.get(`/friend`).then(success).then(fail);
}

function sendFriendRequest(friend, success, fail) {
  local.post(`/friend`, friend).then(success).catch(fail);
}

function acceptFriendRequest(friend, success, fail) {
  local.patch(`/friend/accept`, friend).then(success).catch(fail);
}

function cancelFriendRequest(friendUuid, success, fail) {
  local.delete(`/friend/cancel/${friendUuid}`).then(success).catch(fail)
}

function deleteFriend(friendUuid, success, fail) {
  local.delete(`/friend`, {params: {friendUuid: friendUuid}}).then(
      success).catch(fail);
}