<template>
  <div class="showBill">
    <div class="usersearch">
      <van-search
        v-model="value"
        placeholder="请输入月份(如：2019-11-07)"
        show-action
        shape="round"
        @search="onSearch"
      >
        <div slot="action" @click="onSearch">搜索</div>
      </van-search>
    </div>
    <div style="display:flex">
      <van-cell-group class="user-group">
        <van-field v-model="income" center clearable label="月总支出" disabled></van-field>
      </van-cell-group>
      <van-cell-group class="user-group">
        <van-field v-model="expend" center clearable label="月总收入" disabled></van-field>
      </van-cell-group>
    </div>
    <div v-for="(value,index) in list" class="day" :key="index">
      <van-cell-group class="user-group">
        <van-field v-model="value.date" center clearable label="日期" disabled></van-field>
      </van-cell-group>
      <van-cell-group class="user-group">
        <van-field v-model="value.income" center clearable label="收入" disabled></van-field>
      </van-cell-group>
      <van-cell-group class="user-group">
        <van-field v-model="value.expend" center clearable label="支出" disabled></van-field>
      </van-cell-group>
    </div>
    <div style="margin-top:80px">
      <van-button type="primary" size="large" @click="$router.back(-1)">关闭</van-button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Icon, Cell, CellGroup } from "vant";
export default {
  components: {
    [Icon.name]: Icon,
    [Cell.name]: Cell,
    [CellGroup.name]: CellGroup
  },
  data() {
    return {
      income: "",
      expend: "",
      price: "",
      balance: "",
      productName: "",
      billType: "",
      value: "",
      list: []
    };
  },
  computed: {},
  methods: {
    bill(data) {
      axios
        .post(
          `http://49.233.85.147:9999/zzshx/bill/getBillDetailInfo/${data} `,
          {},
          {
            headers: { token: sessionStorage.getItem("token") }
          }
        )
        .then(res => {
          if (res.data.code == 200) {
            (this.income = res.data.resultBody.income),
              (this.expend = res.data.resultBody.expend);
            this.list = res.data.resultBody.billDetail;
          } else {
            this.$notify({ type: "danger", message: res.data.resultBody });
          }
        });
    },
    onSearch() {
      this.bill(this.value);
    }
  },
  mounted() {
  }
};
</script>

<style lang="less">
</style>