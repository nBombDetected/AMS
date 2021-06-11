import Vue from 'vue'
import Router from 'vue-router'
import Login from "../components/Login"
import Home from "../components/Home"
import Content from "../components/Content"
import accessoryInventory from "../components/accessory/inventory"
import accessoryClaimRecord from "../components/accessory/claim-record"
import accessoryConsumeRecord from "../components/accessory/consume-record"
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: "/login",
      name: "Login",
      component: Login
    },
    {
      path: "/content",
      name: "Content",
      component: Content,
      children: [
        {
          path: "/home",
          name: "Home",
          component: Home
        },
        {
          path: "/accessory/inventory",
          name: "accessory-inventory",
          component: accessoryInventory,
        },
        {
          path: "/accessory/claim-record",
          name: "accessory-claim-record",
          component: accessoryClaimRecord,
        },
        {
          path: "/accessory/consume-record",
          name: "accessory-consume-record",
          component: accessoryConsumeRecord,
        }
      ]
    }
  ]
})
