import axios from 'axios'
import dialog from './catchErr'

// 官网
export const login = params => axios.get(`http://49.233.85.147:9999/zzshx/user/login/`, params).then(res => res.data).catch(error => {
  dialog(error)
})