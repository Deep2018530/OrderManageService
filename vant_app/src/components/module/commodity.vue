<template>
  <div class="commodity">
    <van-divider>特惠套餐</van-divider>
    <van-grid :column-num="3" :gutter="10">
      <van-grid-item v-for="value in list" :key="value.id" @click="showdetails(value)">
        <van-image width="90%" src="https://img.yzcdn.cn/vant/cat.jpeg"></van-image>
        <p class="namep">{{value.name}}</p>
        <span class="sumspan">￥:{{value.price}}</span>
        <div class="commoditybutton">
          <van-button icon="plus" type="warning" @click.stop size="small" />
          <van-button icon="cart-o" type="warning" @click.stop size="small" @click="buy(value.id)">购买</van-button>
        </div>
      </van-grid-item>
    </van-grid>
  </div>
</template>

<script>
import axios from "axios";
import buysuceessVue from './buysuceess.vue';
export default {
  props: ['list'],
  data() {
    return {};
  },
  computed: {},
  methods: {
    showdetails(value) {
       this.$router.push({ name: 'shopdetail', params: { value: JSON.stringify(value) }});
    },
    buy(id){
      let data ={
        id
      }
      axios
        .post(
          `http://49.233.85.147:9999/zzshx/order/${id} `,data,
          {
            headers: { token: sessionStorage.getItem("token") }
          }
        )
        .then(res => {
            if(res.data.code == 200){
                this.$notify({ type: 'success', message: '购买成功' })
                this.$router.push({name:"buysuceess",params:{list:JSON.stringify(res.data.resultBody.orderDetailNewVoList[0])}})
            }else{
                    this.$notify({ type: 'danger', message: '购买失败' })
            }
        })
    }
  }
};
</script>

<style lang="less">
</style>