import {localAuthorizedAxios} from "@/utils/Authorized-commons";
import qs from "qs";
const local = localAuthorizedAxios();

function writePlan(planArticle, success, fail) {
  local.post(`/plan`, planArticle).then(success).catch(fail);
}

function deletePlan(planArticleId, success, fail) {
  local.delete(`/plan/${planArticleId}`).then(success).catch(fail);
}

function getPlanArticleList(success, fail) {
  local.get(`/plan`).then(success).catch(fail);
}

function getPlanArticle(planArticleId, success, fail) {
  local.get(`/plan/${planArticleId}`).then(success).catch(fail);
}

function updatePlan(planArticle, success, fail) {
  local.put(`/plan`, planArticle).then(success).catch(fail);
}

export {
  writePlan,
  deletePlan,
  getPlanArticle,
  getPlanArticleList,
  updatePlan
}