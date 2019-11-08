<template>
  <div
    id="app"
    v-on:touchstart="bodyTouchStart"
    v-on:touchmove="bodyTouchMove"
    v-on:touchend="bodyTouchEnd"
  >
    <!-- content Router -->
    <transition :name="direction">
      <!-- <keep-alive> -->
      <router-view />
      <!-- </keep-alive> -->
    </transition>
  </div>
</template>

<script>
var swidth = document.documentElement.clientWidth
export default {
  name: 'App',
  data() {
    return {
      //transitionName: '', //路由transition
      direction: '',
      // touchLeft 划动起点界限，起点在靠近屏幕左侧时才有效
      touchLeft: (swidth * 2) / 5,
      // touchStartPoint 记录起始点X坐标
      touchStartPoint: 0,
      // distance 记录划动的距离
      distance: 0,
      // 回退按钮的dom，根据页面上是否存在此dom来判断该路由是否可回退
      backBtn: null
    }
  },
  mounted() {},
  methods: {
    bodyTouchStart: function(event) {
      this.backBtn = document.getElementById('leftToDom')
      if (this.backBtn) {
        // 获得起点X坐标，初始化distance为0
        this.touchStartPoint = event.targetTouches[0].pageX
        this.distance = 0
      }
    },
    bodyTouchMove: function(event) {
      if (this.backBtn && this.touchStartPoint < this.touchLeft) {
        // 只监听单指划动，多指划动不作响应
        if (event.targetTouches.length > 1) {
          return
        }
        // 实时计算distance
        this.distance = event.targetTouches[0].pageX - this.touchStartPoint
        // 根据distance在页面上做出反馈。这里演示通过返回按钮的背景变化作出反馈
        if (this.distance > 0 && this.distance < 100) {
          this.backBtn.style.backgroundPosition =
            ((this.distance - 100) / 100) * 50 + 'px 0'
        } else if (this.distance >= 100) {
          this.backBtn.style.backgroundPosition = '0 0'
        } else {
          this.backBtn.style.backgroundPosition = '-50px 0'
        }
      }
    },
    bodyTouchEnd: function(event) {
      if (this.backBtn && this.touchStartPoint < this.touchLeft) {
        // 划动结束，重置数据
        this.touchStartPoint = 0
        this.backBtn.style.backgroundPosition = '-50px 0'
        // 当划动距离超过100px时，触发返回事件
        if (this.distance > 100) {
          // 返回前修改样式，让过渡动画看起来更快
          document.getElementById('app').classList.add('quickback')
          this.$router.back()
          setTimeout(function() {
            document.getElementById('app').classList.remove('quickback')
          }, 250)
        }
      }
    }
  },
  watch: {
    //使用watch 监听$router的变化
    $route(to, from) {
      if (sessionStorage.getItem('tokenid') != null) {
        this.resetSetItem('accessToken', sessionStorage.getItem('tokenid'))
      }
      document.documentElement.scrollTop = 0
      document.body.scrollTop = 0
      if (from.name == null) {
        this.direction = ''
      } else {
        const toDepth = to.path.split('/').length
        const fromDepth = from.path.split('/').length
        this.direction = toDepth < fromDepth ? 'slide-right' : 'slide-left'
      }
      if (from.name == '') {
        $('').hide()
      }
      if (to.name == '') {
        $('').show()
      }
      document.documentElement.scrollTop = 0
      document.body.scrollTop = 0
    }
  }
}
</script>

<style lang="less">
html {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  font-family: NotoSansSC-Regular;
  body {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
    font-family: NotoSansSC-Regular;
    background: rgba(250, 250, 250, 1);
  }
  .appView {
    position: absolute;
    width: 100%;
    background: #fff;
    min-height: 100vh;
    transition: transform 0.24s ease-out;
  }
  #app.quickback .appView {
    // transition: all 0.3s;
  }
  .slide-left-enter {
    transform: translate3d(100%, 0, 0);
  }
  .slide-left-leave-active {
    transform: translate3d(-50%, 0, 0);
  }
  .slide-right-enter {
    transform: translate3d(-50%, 0, 0);
  }
  .slide-right-leave-active {
    transform: translate3d(100%, 0, 0);
  }

  #app {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    width: 100%;
    overflow-x: hidden;
    position: relative;
    background: rgba(255, 255, 255, 1);
    .slide-right-enter-active,
    .slide-right-leave-active,
    .slide-left-enter-active,
    .slide-left-leave-active {
      will-change: transform;
      transition: all 200ms;
      position: absolute;
    }
    .slide-right-enter {
      opacity: 0;
      transform: translate3d(-100%, 0, 0);
    }
    .slide-right-leave-active {
      opacity: 0;
      transform: translate3d(100%, 0, 0);
    }
    .slide-left-enter {
      opacity: 0;
      transform: translate3d(100%, 0, 0);
    }
    .slide-left-leave-active {
      opacity: 0;
      transform: translate3d(-100%, 0, 0);
    }
  }
}
</style>
