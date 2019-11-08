export default {
  isCard: function (val) {
    var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
    return reg.test(val)
  },
  isName: function (val) {
    var reg = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
    return reg.test(val);
  },
  queryString: function (val) {
    var uri = window.location.search;
    var re = new RegExp("" + val + "=([^&?]*)", "ig");
    return uri.match(re) ? uri.match(re)[0].substr(val.length + 1) : null;
  },
  isCnName: function (val) {
    var reg = new RegExp('^[^\u4e00-\u9fa5]+$')
    return !reg.test(val)
  },
  readCookie(name) {
    return localStorage.getItem(name);
  },
  setCookie(name, value) {
    localStorage.setItem(name, value)
  },
  removeCookie(name) {
    localStorage.removeItem(name)
  },
  dateFormat(fmt) {

    return Format(fmt)
  }
}
Date.prototype.Format = function (fmt) {
  //author: meizz
  var o = {
    'M+': this.getMonth() + 1, //月份
    'd+': this.getDate(), //日
    'h+': this.getHours(), //小时
    'm+': this.getMinutes(), //分
    's+': this.getSeconds(), //秒
    'q+': Math.floor((this.getMonth() + 3) / 3), //季度
    S: this.getMilliseconds() //毫秒
  }
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(
      RegExp.$1,
      (this.getFullYear() + '').substr(4 - RegExp.$1.length)
    )
  for (var k in o)
    if (new RegExp('(' + k + ')').test(fmt))
      fmt = fmt.replace(
        RegExp.$1,
        RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length)
      )
  return fmt
}