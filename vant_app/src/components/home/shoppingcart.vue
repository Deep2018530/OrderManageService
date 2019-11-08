<template>
  <div class="shoppingcart">
    <div class="usersearch">
      <van-search v-model="value" placeholder="请输入订单号" show-action shape="round" @search="onSearch">
        <div slot="action" @click="onSearch">搜索</div>
      </van-search>
    </div>
    <van-row class="user-links">
      <van-col span="6">
        <van-icon name="pending-payment" @click="Search('')" />全部
      </van-col>
      <van-col span="6">
        <van-icon name="logistics" @click="Search('已支付')" />已支付
      </van-col>
      <van-col span="6">
        <van-icon name="edit" @click="Search('申请退款')" />申请退款
      </van-col>
      <van-col span="6">
        <van-icon name="close" @click="Search('拒绝退款')" />拒绝退款
      </van-col>
      <van-col span="6">
        <van-icon name="gold-coin-o" @click="Search('成功退款')" />成功退款
      </van-col>
    </van-row>
    <div class="van-list">
      <van-card
        v-for="value in list"
        num="1"
        :price=value.order.amount
        :desc=value.order.status
        :title=value.order.id
        thumb="http://img3.imgtn.bdimg.com/it/u=1243984827,4172951716&fm=26&gp=0.jpg"
        :key="value"
      >
        <div slot="footer">
          <van-button size="mini" type="warning" @click="refund(value.order.id)">退款</van-button>
        </div>
      </van-card>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Row, Col, Icon, Cell, CellGroup, Card } from "vant";
export default {
  components: {
    [Row.name]: Row,
    [Col.name]: Col,
    [Icon.name]: Icon,
    [Cell.name]: Cell,
    [CellGroup.name]: CellGroup,
    [Card.name]: Card
  },
  data() {
    return {
      orderForm: {
        amountDown: "",
        amountUp: "",
        status: "",
        createTimeStart: "",
        createTimeEnd: "",
        id: "",
        nickName: "",
        productName: "",
        userId: ""
      },
      list:[]
    };
  },
  computed: {},
  methods: {
    refund(id) {
      axios
        .get(`http://49.233.85.147:9999/zzshx/order/refund/${id} `, {
          headers: { token: sessionStorage.getItem("token") }
        })
        .then(res => {})
        .catch(error => {
          console.log(error);
        });
    },
    onSearch() {
      this.orderForm.status = "";
      this.chaxun()
    },
    Search(type) {
      this.orderForm.status = type;
      this.chaxun()
    },
    chaxun() {
      axios
        .post(
          `http://49.233.85.147:9999/zzshx/order/1/999`,
          this.orderForm,
          {
            headers: { token: sessionStorage.getItem("token") }
          }
        )
        .then(res => {
          if (res.data.code == 200) {
            this.total = res.data.resultBody.totalElements;
            this.list = res.data.resultBody.content;
          } else {
            this.$message.error(res.data.resultBody);
          }
        });
    }
  }
};
</script>

<style lang="less">
</style>