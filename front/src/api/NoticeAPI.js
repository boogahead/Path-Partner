import {localAuthorizedAxios} from "@/utils/Authorized-commons";

const local = localAuthorizedAxios();

function getAllNoticeArticle(success, fail) {
  local.get(`/notice`).then(success).catch(fail)
}

function getNoticeArticle(noticeArticleId, success, fail) {
  local.get(`/notice/${noticeArticleId}`).then(success).catch(fail)
}

function writeNoticeArticle(noticeArticle, success, fail) {
  local.post(`/notice`, noticeArticle).then(success).catch(fail)
}

function modifyNoticeArticle(noticeArticle, success, fail) {
  local.put(`notice`, noticeArticle).then(success).catch(fail)
}

function deleteNoticeArticle(noticeArticleId, success, fail) {
  local.delete(`/notice/${noticeArticleId}`).then(success).catch(fail)
}

export {
  getAllNoticeArticle,
  getNoticeArticle,
  writeNoticeArticle,
  modifyNoticeArticle,
  deleteNoticeArticle,
}