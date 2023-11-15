import {localAxios} from "@/utils/API-commons";

const local = localAxios();

function getArticleList(param, success, fail) {
  local.get(`/board`, {params: param}).then(success).catch(fail);
}

function getArticle(articleno, success, fail) {
  local.get(`/board/${articleno}`).then(success).catch(fail);
}

function registArticle(article, success, fail) {
  local.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function getModifyArticle(articleno, success, fail) {
  local.get(`/board/modify/${articleno}`).then(success).catch(fail);
}

function putArticle(article, success, fail) {
  local.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  local.delete(`/board/${articleno}`).then(success).catch(fail);
}

function getCommentList(articleno, success, fail) {
  local.get(`/comment/${articleno}`).then(success).catch(fail);
}

function registComment(comment, success, fail) {
  local.post(`/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

function deleteComment(commentno, success, fail) {
  local.delete(`/comment/${commentno}`).then(success).catch(fail);
}

export {
  getArticleList,
  getArticle,
  registArticle,
  getModifyArticle,
  putArticle,
  deleteArticle,
  getCommentList,
  registComment,
  deleteComment,
};
