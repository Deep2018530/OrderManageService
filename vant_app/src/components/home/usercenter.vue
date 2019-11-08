<template>
  <div class="usercenter">
    <div class="usercenterheader">
      <div class="usersetting">
        <van-icon name="contact" />
      </div>
    </div>
    <img
      class="user-poster"
      src="https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3190441126,995644236&fm=26&gp=0.jpg"
    />
    <van-cell-group class="user-group">
      <van-field v-model="nickName" left-icon="friends-o" center clearable label="昵称" disabled>
      </van-field>
    </van-cell-group>
    <van-cell-group class="user-group">
      <van-field v-model="mobile" left-icon="phone-o" center clearable label="电话" disabled>
      </van-field>
    </van-cell-group>
    <van-cell-group class="user-group">
      <van-field v-model="email" left-icon="envelop-o" center clearable label="邮箱" disabled>
      </van-field>
    </van-cell-group>
    <van-cell-group class="user-group">
      <van-field v-model="balance" left-icon="paid" center clearable label="余额" disabled>
        <van-button slot="button" size="normal" round type="primary" @click="showdialog">充值</van-button>
      </van-field>
    </van-cell-group>
    <van-cell-group class="user-group">
      <van-field left-icon="records" center clearable label="账单" disabled>
        <van-button class="showtext" slot="button" icon="arrow" type="default" @click="showtext"/>
      </van-field>
    </van-cell-group>
    
    <van-dialog v-model="show" title="请输入金额" show-cancel-button :beforeClose="onSubmit">
      <van-field v-model="number" type="number" placeholder="请输入金额" />
    </van-dialog>
  </div>
</template>

<script>
import { Row, Col, Icon, Cell, CellGroup } from "vant";
import axios from "axios";
export default {
  components: {
    [Row.name]: Row,
    [Col.name]: Col,
    [Icon.name]: Icon,
    [Cell.name]: Cell,
    [CellGroup.name]: CellGroup
  },
  data() {
    return {
      nickName: "",
      mobile:"",
      email:"",
      show:false,
      balance:0,
      number:0
    };
  },
  methods: {
    onSubmit(action, done) {
      if(action == "cancel"){
        done()
        return
      }else{
        if(this.number >0){
        axios
        .get(
          `http://49.233.85.147:9999/zzshx/recharge/${this.number} `,
          {
            headers: { token: sessionStorage.getItem("token")}
          }
        )
        .then(res => {
          if(res.data.code == 200){
            this.$notify({ type: 'success', message: '充值成功' })
            this.user()
          }else{
             this.$notify({ type: 'danger', message: res.data.resultBody })
          }
          
        })}else{
          this.$notify({ type: 'danger', message: "金额不能为负" })
        }
      }
      done()
    },
    user() {
      axios
        .get(
          `http://49.233.85.147:9999/zzshx/account/getUserAccountInfo/${sessionStorage.getItem("userID")} `,
          {
            headers: { token: sessionStorage.getItem("token")}
          }
        )
        .then(res => {
          if(res.data.code == 200){
            this.nickName = res.data.resultBody.nickName,
            this.mobile = res.data.resultBody.mobile,
            this.email = res.data.resultBody.email,
            this.balance = res.data.resultBody.balance
          }else{
             this.$notify({ type: 'danger', message: res.data.resultBody })
          }
          
        })
    },
    showtext(){
      this.$router.push({name:"showBill"})
    },
    showdialog() {
      this.show = true
    }
  },
  mounted(){
    this.user()
  }
};
</script>

<style lang="less">
.user {
  &-poster {
    width: 100%;
    height: 53vw;
    display: block;
  }

  &-group {
    margin-bottom: 15px;
  }

  &-links {
    padding: 15px 0;
    font-size: 18px;
    text-align: center;
    background-color: #fff;

    .van-icon {
      display: block;
      font-size: 24px;
    }
  }
  
}
.van-icon-cash-back-record {
  &::before {
    content: "充值";
  }
}
</style>
