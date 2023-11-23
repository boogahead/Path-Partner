import 'mdb-vue-ui-kit/css/mdb.min.css';
// import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

import {createApp} from 'vue'
import {createPinia} from 'pinia'

import App from './App.vue'
import router from './router/index'

const app = createApp(App)
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate)
window.global=window;
app.use(pinia)
app.use(router)

app.mount('#app')
