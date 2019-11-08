import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
const originalPush = Router.prototype.push
Router.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}
export default new Router({
  routes: [{
      path: '/',
      name: 'login',
      component: () => import("@/components/login")
    }, {
      path: '/register',
      name: 'register',
      component: () => import("@/components/register")
    },
    {
      path: '/home',
      name: 'home',
      component: () => import("@/components/home"),
      children: [{
        path: '/homepage',
        name: 'homepage',
        component: () => import("@/components/home/homepage")
      }, {
        path: '/shoppingcart',
        name: 'shoppingcart',
        component: () => import("@/components/home/shoppingcart")
      }, {
        path: '/usercenter',
        name: 'usercenter',
        component: () => import("@/components/home/usercenter")
      }]
    }, {
      path: '/commodity',
      name: 'commodity',
      component: () => import("@/components/module/commodity")
    }, {
      path: '/shopdetail',
      name: 'shopdetail',
      component: () => import("@/components/module/shopdetail")
    },{
      path: '/buysuceess',
      name: 'buysuceess',
      component: () => import("@/components/module/buysuceess")
    },{
      path: '/showBill',
      name: 'showBill',
      component: () => import("@/components/module/showBill")
    }
  ]
})
