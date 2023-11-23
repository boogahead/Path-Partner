import axios from "axios";
import qs from "qs";
import { useLoginUserStore } from "@/stores/loginUser";

axios.defaults.paramsSerializer = params => {
  return qs.stringify(params);
}

const { VITE_VUE_API_URL } = import.meta.env;

let isRefreshing = false;
let refreshPromise = null;

function regenerateAccessToken() {
  return new Promise((resolve, reject) => {
    const loginUser = JSON.parse(sessionStorage.getItem("loginUser"))
    let { refreshToken } = loginUser
    axios.post(`${VITE_VUE_API_URL}/auth/token`, { refreshToken: refreshToken })
    .then(response => {
      const newAccessToken = response.data.accessToken;
      loginUser.accessToken = newAccessToken;
      sessionStorage.setItem("loginUser",JSON.stringify(loginUser))
      resolve(newAccessToken);
    })
    .catch(error => {
      reject(error);
    });
  });
}

function localAuthorizedAxios() {
  const loginUser = JSON.parse(sessionStorage.getItem("loginUser"))
  const { accessToken } = loginUser
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      "Access-Control-Allow-Origin": "*",
      "Authorization": 'Bearer ' + accessToken,
    },
  });

  instance.interceptors.request.use((config) => {
    return config;
  }, (error) => {
    return Promise.reject(error);
  });

  instance.interceptors.response.use(
      (response) => {
        return response;
      },
      async (error) => {
        const {
          config,
          response: { status },
        } = error;

        if (status === 401) {
          if (!isRefreshing) {
            isRefreshing = true;

            refreshPromise = regenerateAccessToken()
            .then(newAccessToken => {
              instance.defaults.headers.common["Authorization"] = 'Bearer ' + newAccessToken;
              config.headers.Authorization = 'Bearer ' + newAccessToken;
              return instance(config);
            })
            .catch(error => {
              // Handle token regeneration error
              throw error;
            })
            .finally(() => {
              isRefreshing = false;
              refreshPromise = null;
            });

            return refreshPromise;
          } else {
            // If refresh is already in progress, wait for it to complete
            return refreshPromise.then(() => instance(config));
          }
        }

        return Promise.reject(error);
      }
  );

  return instance;
}

export { localAuthorizedAxios };