import axios from "axios";
import qs from "qs";

axios.defaults.paramsSerializer = params => {
  return qs.stringify(params);
}

const { VITE_VUE_API_URL } = import.meta.env;

// Function to regenerate access token
function regenerateAccessToken() {
  return new Promise((resolve, reject) => {
    const loginUser = JSON.parse(sessionStorage.getItem("loginUser"))
    const { refreshToken } = loginUser;

    axios.post(`${VITE_VUE_API_URL}/auth/token`, { refreshToken: refreshToken })
    .then(response => {
      const newAccessToken = response.data.accessToken;
      resolve(newAccessToken);
    })
    .catch(error => {
      reject(error);
    });
  });
}

// local vue api axios instance
function localAuthorizedAxios() {
  const loginUser = JSON.parse(sessionStorage.getItem("loginUser"))
  const { accessToken } = loginUser;

  let errorCount = 0;
  const maxErrorCount = 3; // 최대 허용 에러 횟수
  let isRefreshing = false;
  let refreshPromise;

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
        errorCount = 0;
        return response;
      },
      async (error) => {
        const {
          config,
          response: { status },
        } = error;

        // 페이지가 새로고침되어 저장된 accessToken이 없어진 경우.
        // 토큰 자체가 만료되어 더 이상 진행할 수 없는 경우.
        if (status === 401) {
          errorCount += 1;

          if (!isRefreshing) {
            isRefreshing = true;

            if (errorCount <= maxErrorCount) {
              console.log("regenerate")
              const originalRequest = config;

              try {
                const newAccessToken = await regenerateAccessToken();
                instance.defaults.headers.common["Authorization"] = 'Bearer ' + newAccessToken;
                originalRequest.headers.Authorization = 'Bearer ' + newAccessToken;

                isRefreshing = false;
                return instance(originalRequest);
              } catch (error) {
                // Handle the error if something goes wrong during token regeneration
                isRefreshing = false;
              }
            }
          }

          return Promise.reject(error);
        }
      }
  );

  return instance;
}

export { localAuthorizedAxios };