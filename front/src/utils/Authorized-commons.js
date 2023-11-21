import axios from "axios";

import qs from "qs";

axios.defaults.paramsSerializer = params => {
  return qs.stringify(params);
}
const {VITE_VUE_API_URL} = import.meta.env;

// local vue api axios instance
function localAuthorizedAxios() {
  const loginUser = sessionStorage.getItem("loginUser")
  const {accessToken} = JSON.parse(loginUser);

  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      "Access-Control-Allow-Origin": "*",
      // "Access-Control-Allow-Credential": true
      "Authorization": 'Bearer ' + accessToken,
    },
  });
  return instance;
}

export {localAuthorizedAxios};
