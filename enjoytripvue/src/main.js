// import "./assets/main.css";

import { createApp } from "vue";
import Antd from 'ant-design-vue';
// import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import 'ant-design-vue/dist/reset.css';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

const app = createApp(App);

// app.use(createPinia());
app.use(router);

app.use(Antd).mount("#app");
