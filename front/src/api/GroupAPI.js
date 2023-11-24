import {localAuthorizedAxios} from "@/utils/Authorized-commons";

const local = localAuthorizedAxios();

function registerGroup(travelGroup, success, fail) {
  local.post(`/group`, travelGroup).then(success).catch(fail)
}

function removeGroup(groupId, success, fail) {
  local.delete(`/group/${groupId}`).then(success).catch(fail)
}

function leaveGroup(groupId, success, fail) {
  local.delete(`group/leave/${groupId}`).then(success).catch(fail)
}

function getGroupMember(groupId, success, fail) {
  local.get(`/group/member/${groupId}`).then(success).catch(fail)
}

function getGroupList(success, fail) {
  local.get(`/group`).then(success).catch(fail);
}

function registerGroupInvite(groupInvite, success, fail) {
  local.post(`/group/invite`, JSON.stringify(groupInvite)).then(success).catch(fail);
}

function acceptGroupInvite(groupId, success, fail) {
  local.put(`/group/invite/${groupId}`).then(success).catch(fail)
}

function deleteGroupInvite(groupId, success, fail) {
  local.delete(`/group/invite/${groupId}`).then(success).catch(fail)
}

function checkGroupInvite(success, fail) {
  local.get(`/group/invite/received`).then(success).catch(fail)
}

function kickGroupMember(groupId, uuid, success, fail) {
  local.delete(`/group/${groupId}/${uuid}`).then(success).catch(fail)
}

function getPendingInviteList(groupId, success, fail) {
  local.get(`/group/invite/sent/${groupId}`).then(success).catch(fail);
}

function cancelGroupInvite(groupId, inviteTo, success, fail) {
  local.delete(`group/invite/${groupId}/${inviteTo}`).then(
      success).catch(fail)
}

export {
  removeGroup,
  registerGroup,
  registerGroupInvite,
  leaveGroup,
  deleteGroupInvite,
  getPendingInviteList,
  getGroupList,
  getGroupMember,
  acceptGroupInvite,
  cancelGroupInvite,
  checkGroupInvite,
  kickGroupMember,
}

