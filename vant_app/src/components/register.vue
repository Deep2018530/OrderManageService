<template>
  <div class="login">
      <div class="back">
        <van-icon name="arrow-left" @click="back" />
    </div>
    <div class="zoomIn">
      <van-cell-group>
        <van-field
          label="用户名"
          placeholder="请输入用户名"
          left-icon="contact"
          v-model="nickName"
          required
          clearable
        />
        <van-field v-model="password" label="密码" left-icon="edit" placeholder="请输入密码" required />
        <van-field v-model="mobile" label="手机号" left-icon="edit" placeholder="请输入手机号" required />
        <van-field v-model="email" label="邮箱" left-icon="edit" placeholder="请输入邮箱" required />
        <van-field v-model="gender" label="性别" left-icon="edit" placeholder="请输入性别" required />
        <van-field v-model="address" label="地址" left-icon="edit" placeholder="请输入地址" required />
      </van-cell-group>
      <div style="margin-top:80px">
        <van-button type="primary" size="large" @click="handleLogin">确定</van-button>
      </div>
    </div>
  </div>
</template> <script>
import axios from "axios";
import { Dialog } from "vant";
export default {
  components: {
    [Dialog.Component.name]: Dialog.Component
  },
  data() {
    return {
      password: "",
      nickName: "",
      mobile: "",
      email: "",
      gender: "",
      address: "",
      portrait: ""
    };
  },

  methods: {
    handleLogin() {
      let data = {};
      data.password = this.password;
      data.nickName = this.nickName;
      data.mobile = this.mobile;
      data.email = this.email;
      data.gender = this.gender;
      data.address = this.address;
      data.portrait = this.portrait;
      axios
        .post(`http://49.233.85.147:9999/zzshx/user/regist`, data, {
          headers: { token: "213456798" }
        })
        .then(res => {
          if (res.data.code != 200) {
            return;
          }
          Dialog.confirm({
            title: "成功",
            message: "是否登录"
          })
            .then(() => {
              this.login();
            })
            .catch(() => {
              this.password = "";
              this.nickName = "";
              this.mobile = "";
              this.email = "";
              this.gender = "";
              this.address = "";
              this.portrait = "";
            });
        })
        .catch(error => {
          console.log(error);
        });
    },
    login() {
      axios
        .get(
          `http://49.233.85.147:9999/zzshx/user/login/${this.nickName}/${this.password} `,
          {
            headers: { token: "213456798" }
          }
        )
        .then(res => {
          sessionStorage.setItem("token", res.data.resultBody.token);
          this.$router.push({ name: "home" });
        })
        .catch(error => {
          console.log(error);
        });
    },
    back(){
        this.$router.go(-1)
    }
  }
};
</script>
<style scoped>
</style>>