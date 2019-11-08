<template>
  <div class="buysucess">
    <div class="back">
      <van-icon name="arrow-left" @click="back" />
    </div>
    <div class="card">
      <van-field v-model="productName" left-icon="paid" center clearable label="名称" disabled></van-field>
      <van-field v-model="price" left-icon="paid" center clearable label="金额" disabled></van-field>
      <van-field v-model="num" left-icon="paid" center clearable label="数量" disabled></van-field>
      <van-field v-model="orderId" left-icon="paid" center clearable label="订单号" disabled></van-field>
      <div style="margin-top:60px">
        <van-button type="primary" size="large" @click="backhome">返回首页</van-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      productName: JSON.parse(this.$route.params.list).productName,
      price: JSON.parse(this.$route.params.list).price,
      num: JSON.parse(this.$route.params.list).num,
      orderId: JSON.parse(this.$route.params.list).orderId
    };
  },
  methods: {
    back() {
      this.$router.go(-1);
    },
    backhome(){
        this.$router.push({name:"home"})
    }
  }
};
</script>

<style>
</style>