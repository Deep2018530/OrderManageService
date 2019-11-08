import Vue from 'vue'
import VeeValidate, {
  Validator
} from 'vee-validate'
import cn from 'vee-validate/dist/locale/zh_CN'
import VueI18n from 'vue-i18n'
Vue.use(VueI18n)

const i18n = new VueI18n({
  locale: 'zh_CN'
})
Vue.use(VeeValidate, {
  i18n,
  i18nRootKey: 'validation',
  dictionary: {
    cn
  }
})
const dict = {
  zh_CN: {
    messages: {
      required: (username) => `${username}不能为空!`
    }
  } // name接受alias的值.
}
Validator.extend('pwd', {
  getMessage: () => `原始密码错误`,
  validate: value =>
    !/^[\u4e00-\u9fa5]+$/.test(value) && sessionStorage.getItem('password') == value
})
Validator.extend('newPwd', {
  getMessage: () => `请输入正确的密码`,
  validate: value =>
    !/^[\u4e00-\u9fa5]+$/.test(value)
})
Validator.extend('againPwd', {
  getMessage: () => `请再次确认密码`,
  validate: value =>
    !/^[\u4e00-\u9fa5]+$/.test(value) && sessionStorage.getItem('newPwd') == value
})
Validator.extend('userZh', {
  getMessage: () => `请输入正确的用户名`,
  validate: value =>
    /^[^\u4e00-\u9fa5]+$/.test(
      value
    )
})
Validator.extend('userName', {
  getMessage: () => `姓名输入错误`,
  validate: value =>
    /^[\u4E00-\u9FA5|·]{2,20}$/.test(
      value
    )
})
Validator.extend('idCard', {
  getMessage: () => `身份证号输入错误`,
  validate: value =>
    /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/.test(
      value
    )
})
Validator.extend('isMobile', {
  getMessage: () => `手机号输入错误`,
  validate: value =>
    /^[1][1-9][0-9]{9}$/.test(
      value
    )
})
Validator.extend('isNull', {
  getMessage: () => `请检查表单项`,
  validate: value => {
    if (/\S/.test(value) || value == null || value == "") {
      return true
    }
    return false
  }
})


Validator.localize(dict)
