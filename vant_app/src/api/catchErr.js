import {
  Dialog
} from 'vant'
let errorMsg = {
  title: '提示', // 加上标题
  message: '系统错误,请重新登录!', // 改变弹出框的内容
  closeOnPopstate: true,
  beforeClose(action, done) {
    window.location.hash = '#/login'
    done()
  }
}
export default function dialog(status) {
  if (status.request.status === 0) { Dialog.alert(errorMsg) }

}
