import {localAxios} from "@/utils/API-commons";
import {localAuthorizedAxios} from "@/utils/Authorized-commons";

const local = localAuthorizedAxios();

function getMyFriendList(success, fail) {
  local.get(`/friend`).then(success).catch(fail);
}

// success -> response response.status, data ...
// fail -> ()
function sendFriendRequest(friend, success, fail) {
  local.post(`/friend`, {friendTo: friend}).then(success).catch(fail);
}

function acceptFriendRequest(friend, success, fail) {
  local.put(`/friend/accept`, {friendFrom:friend}).then(success).catch(fail);
}

function cancelFriendRequest(friendUuid, success, fail) {
  local.delete(`/friend/cancel/${friendUuid}`).then(success).catch(fail)
}

function deleteFriend(friendUuid, success, fail) {
  local.delete(`/friend`, {params: {friendUuid: friendUuid}}).then(
      success).catch(fail);
}

function getFriendRequestSent(success, fail) {
  local.get(`/friend/sent`).then(success).catch(fail);
}

function getFriendRequestReceived(success, fail) {
  local.get(`/friend/received`).then(success).catch(fail)
}

function rejectFriendRequest(friendFrom, success, fail) {
  local.delete(`/friend/reject/${friendFrom}`).then(success).catch(fail);
}

export {
  getMyFriendList,
  sendFriendRequest,
  acceptFriendRequest,
  cancelFriendRequest,
  deleteFriend,
  getFriendRequestSent,
  getFriendRequestReceived,
  rejectFriendRequest
}