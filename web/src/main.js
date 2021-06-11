// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from "element-ui"
import "element-ui/lib/theme-chalk/index.css"
import $global from "./global.js"
import axios from "axios"
Vue.config.productionTip = false

let instanceAxios = axios.create({
  timeout: 5000,
  baseURL: "http://127.0.0.1:18080/",
  changeOrigin: true,
  headers: {
    "X-user-token": "",
    "X-user-id": "",
    "Content-Type": "application/json;charset=utf-8",
    "Access-Control-Allow-Headers": "Content-Type,Authorization",
    "Access-Control-Allow-Methods": "*",
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Credentials": "true",
    "withCredentials": false
  },
  responseType: "json",
  proxy: {
    protocol: 'http',
    host: '127.0.0.1',
    port: 18080,
  },
  withCredentials: false,
  notification: false,
})
instanceAxios.interceptors.request.use(function (config){
  if(config.withCredentials==true){
    console.log("添加访问权限控制")
    config.headers["withCredentials"] = true
    config.headers["X-user-token"] = sessionStorage.getItem("token")
    config.headers["X-user-id"] = sessionStorage.getItem("userId");
  }
  else {
    console.log("无权限访问控制")
    config.headers["withCredentials"] = false
  }
  return config
})

instanceAxios.interceptors.response.use(
  response=>{
    if(response.config["notification"]===false) return Promise.resolve(response).catch((e)=>{})
    alert(response.data["msg"])
    if(response.data["code"]===200) {
      return Promise.resolve(response).catch((e)=>{})
    } else {
      return Promise.reject(response).catch((e)=>{})
    }
  },
)

Vue.prototype.$global = $global
Vue.prototype.$axios = instanceAxios

Vue.use(ElementUI)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
})
