<template>
  <div class="homepage">
    <van-search v-model="value" placeholder="请输入搜索关键词" show-action shape="round" @search="onSearch">
      <div slot="action" @click="onSearch">搜索</div>
    </van-search>
    <van-swipe :autoplay="3000">
      <van-swipe-item v-for="image in images" :key="image">
        <img :src="image" width="100%" height="200px" />
      </van-swipe-item>
    </van-swipe>
    <commodity v-for="item in 2" :key="item" :list="list"></commodity>
  </div>
</template>

<script>
import commodity from "@/components/module/commodity";
import axios from "axios";
export default {
  components: {
    commodity: commodity
  },
  data() {
    return {
      value: "",
      images: [
        "https://tsplus.zhibocloud.cn/api/v2/files/7837",
        "https://tsplus.zhibocloud.cn/api/v2/files/7838",
        "https://tsplus.zhibocloud.cn/api/v2/files/19637"
      ],
      page:1,
      size:6,
      list:[]
    };
  },
  methods: {
    getlist() {
      axios.get(
        `http://49.233.85.147:9999/zzshx/product/list/${this.page}/${this.size} `,
        {
          headers: { token: sessionStorage.getItem("token") }
        }
      ).then(res => {
        this.list = res.data.resultBody.content
      });
    },
    onSearch(){}
  },
  mounted(){
    this.getlist()
  }
};
</script>

<style>
</style>