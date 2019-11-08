<template>
  <div class="shopdetail">
    <div class="back">
        <van-icon name="arrow-left" @click="back" />
    </div>
    <!-- <van-swipe :autoplay="3000">
    <van-swipe-item v-for="thumb in goods.thumb" :key="thumb">-->
    <img :src="goods.thumb[0]" width="100%" height="210px" />
    <!-- </van-swipe-item>
    </van-swipe>-->
    <van-cell-group>
      <van-cell>
        <div class="goods-title">{{ goods.title }}</div>
        <div class="goods-price">{{ formatPrice(goods.price) }}</div>
      </van-cell>
    </van-cell-group>

    <van-cell-group class="goods-cell-group">
      <van-cell value="进入店铺" icon="shop-o" is-link @click="sorry">
        <template slot="title">
          <span class="van-cell-text">旗舰店</span>
          <van-tag class="goods-tag" type="danger">官方</van-tag>
        </template>
      </van-cell>
    </van-cell-group>

    <van-cell-group class="goods-cell-group">
      <van-cell title="查看商品详情" is-link @click="showdetail" />
    </van-cell-group>

    <van-goods-action>
      <van-goods-action-icon icon="chat-o" @click="sorry">客服</van-goods-action-icon>
      <van-goods-action-icon icon="cart-o" @click="onClickCart">购物车</van-goods-action-icon>
      <van-goods-action-button type="warning" @click="sorry">加入购物车</van-goods-action-button>
      <van-goods-action-button type="danger" @click="buy">立即购买</van-goods-action-button>
    </van-goods-action>
  </div>
</template>

<script>
import {
  Tag,
  Col,
  Icon,
  Cell,
  CellGroup,
  Swipe,
  Toast,
  SwipeItem,
  GoodsAction,
  GoodsActionIcon,
  GoodsActionButton,
  Dialog
} from "vant";
import axios from "axios";
export default {
  components: {
    [Tag.name]: Tag,
    [Col.name]: Col,
    [Icon.name]: Icon,
    [Cell.name]: Cell,
    [CellGroup.name]: CellGroup,
    [Swipe.name]: Swipe,
    [SwipeItem.name]: SwipeItem,
    [GoodsAction.name]: GoodsAction,
    [GoodsActionIcon.name]: GoodsActionIcon,
    [GoodsActionButton.name]: GoodsActionButton,
    [Dialog.Component.name]: Dialog.Component
  },
  data() {
    return {
      goods: {
        title: JSON.parse(this.$route.params.value).name,
        price: JSON.parse(this.$route.params.value).price,
        thumb: [
          "https://tsplus.zhibocloud.cn/api/v2/files/7837",
          "https://tsplus.zhibocloud.cn/api/v2/files/7838"
        ]
      }
    };
  },
  methods: {
    formatPrice() {
      return "¥" + this.goods.price.toFixed(2);
    },
    onClickCart() {
      this.$router.push({ name: "shopdetail", params: { userId: 123 } });
    },
    sorry() {
      Toast("暂无后续逻辑~");
    },
    showdetail() {
      Dialog.alert({
        title: "详情",
        message: JSON.parse(this.$route.params.value).moreDetail
      }).then(() => {
        // on close
      });
    },
    buy(){
      let data ={
        id:JSON.parse(this.$route.params.value).id
      }
      axios
        .post(
          `http://49.233.85.147:9999/zzshx/order/${data.id} `,data,
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
    },
    back(){
        this.$router.go(-1)
    }
  },
  mounted() {}
};
</script>

<style lang="less">
</style>