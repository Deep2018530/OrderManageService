<template>
  <div class="login">
    <div class="zoomIn">
      <van-cell-group>
        <van-field
          label="用户名"
          placeholder="请输入用户名"
          left-icon="contact"
          v-model="username"
          required
          clearable
        />
        <van-field
          v-model="password"
          type="password"
          label="密码"
          left-icon="edit"
          placeholder="请输入密码"
          required
        />
      </van-cell-group>
      <div class="zhuce" @click="tozhuce">注册</div>
      <div style="margin-top:80px">
        <van-button type="primary" size="large" @click="handleLogin">确定</van-button>
      </div>
    </div>
  </div>
</template> <script>
import axios from "axios";
export default {
  data() {
    return { password: "", username: "" };
  },

  methods: {
    handleLogin() {
      axios
        .get(
          `http://49.233.85.147:9999/zzshx/user/login/${this.username}/${this.password} `,
          {
            headers: { token: "213456798" }
          }
        )
        .then(res => {
          sessionStorage.setItem("token", res.data.resultBody.token);
          sessionStorage.setItem("userID", res.data.resultBody.userRoles[0].id);
          this.$router.push({ name: "home" });
        })
        .catch(error => {
          console.log(error);
        });
    },
    tozhuce(){
      this.$router.push({name:"register"})
    }
  },
  mounted(){
  }
};
</script>
<style scoped>
</style>>