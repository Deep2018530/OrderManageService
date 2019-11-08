// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import $ from 'jquery'
import router from './router'
import './util/validate' // 验证规则
import 'vant/lib/index.css'
import '@/common/css/common.less'
import 'lib-flexible/flexible.js'
import FastClick from 'fastclick'
import bus from "vue-bus"
import store from './store/store'
import touch from 'vue-directive-touch'
Vue.use(touch)
Vue.use(bus)
FastClick.attach(document.body)
Vue.config.productionTip = false
import {
  Tabbar,
  TabbarItem,
  Icon,
  Checkbox,
  CheckboxGroup,
  Card,
  SubmitBar,
  Toast,
  Search,
  Swipe,
  SwipeItem,
  Lazyload,
  Button,
  Grid,
  GridItem,
  Image,
  Divider,
  Field,
  Dialog,
  Cell,
  CellGroup,
  Stepper,
  Notify
} from 'vant';
Vue.use(Notify).use(Tabbar).use(TabbarItem).use(Icon).use(Checkbox).use(CheckboxGroup).use(Card).use(SubmitBar).use(Toast).use(Search).use(Swipe).use(SwipeItem).use(Lazyload).use(Button).use(Grid).use(GridItem).use(Image).use(Divider).use(Field).use(Dialog).use(Cell).use(CellGroup).use(Stepper);
Vue.filter('strFilter', function (value, regStr, relStr) {
  let str = ''
  if (value === undefined) {
    return str
  } else {
    str = value.replace(new RegExp(regStr, 'g'), relStr)
    return str
  }
})

new Vue({
  el: '#app',
  router,
  store,
  components: {
    App
  },
  template: '<App/>'
})
window.onresize = setHtmlFontSize

function setHtmlFontSize() {
  const htmlWidth = document.documentElement.clientWidth || document.body.clientWidth
  const htmlDom = document.getElementsByTagName('html')[0]
  htmlDom.style.fontSize = htmlWidth / 10 + 'px'
};
setHtmlFontSize()
