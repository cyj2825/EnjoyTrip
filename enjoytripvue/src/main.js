// import "./assets/main.css";

import { createApp } from "vue";
import Antd from 'ant-design-vue';
import { createPinia } from "pinia";
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

import App from "./App.vue";
import router from "./router";
import 'ant-design-vue/dist/reset.css';
import VueCookies from "vue-cookies"
// import "bootstrap/dist/css/bootstrap.min.css";
// import "bootstrap";

const app = createApp(App)
const pinia = createPinia()

app.use(VueCookies);
pinia.use(piniaPluginPersistedstate)

app.use(pinia)
// app.use(createPinia().use(piniaPluginPersistedstate));
app.use(router)

// app.mount('#app')
app.use(Antd).mount("#app");
