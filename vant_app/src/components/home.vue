<template>
  <div class="home">
    <router-view />
    <van-tabbar v-model="active" class="active_tab">
      <van-tabbar-item v-for="(item,index) in tabbars" :key="index" @click="tab(index,item.router)">
        <span :class="currIndex == index ? active:''">{{item.title}}</span>
        <template slot="icon" slot-scope="props">
          <van-icon :name="props.active ? item.active : item.normal"></van-icon>
        </template>
      </van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currIndex: 0,
      active: 0,
      tabbars: [
        {
          name: "home",
          router: "homepage",
          title: "首页",
          normal: "wap-home-o",
          active: "wap-home"
        },
        {
          name: "shoppingcart",
          router: "shoppingcart",
          title: "订单查询",
          normal: "shopping-cart-o",
          active: "shopping-cart"
        },
        {
          name: "mine",
          router: "usercenter",
          title: "我的",
          normal: "friends-o",
          active: "friends"
        }
      ]
    };
  },
  methods: {
    tab(index, val) {
      this.currIndex = index;
      this.$router.push(val);
    }
  },
  mounted(){
    this.tab(0,"homepage")
  }
};
</script>

<style>
</style>