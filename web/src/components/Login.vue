<template>
  <div>
    <el-form ref="loginForm" :rules="rules" :model="loginForm" label-width="80px" class="login_form">
      <el-form-item label="用户名" prop="name">
        <el-input v-model="loginForm.name" placeholder="请输入登录邮箱"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input placeholder="请输入密码" v-model="loginForm.password" show-password></el-input>
      </el-form-item>
      <el-button type="primary" style="width: 100%" @click="login">登录</el-button>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data(){return {
    loginForm: {
      name: "",
      password: "",
    },
    rules: {
      name: [{required: true, message: "请输入用户名", trigger: "blur"}],
      password: [{required: true, message: "请输入密码", trigger: "blur"}]
    }
  }},
  methods: {
    login(){
      let validate = true
      this.$refs["loginForm"].validate((valid) => {
        if (!valid) {
          alert('请填写所有必填项')
          validate = false
        }
      });
      if(!validate) return

      this.$axios.post("user/login",{
        name: this.loginForm.name,
        password: this.loginForm.password,
      }).then(
        response=>{
          sessionStorage.setItem("token", response.data["token"])
          sessionStorage.setItem("userRealName", response.data["userRealName"])
          sessionStorage.setItem("userId", response.data["userId"])
          this.$router.push("/home")
        }
      )
    }
  }
}
</script>

<style scoped>
.login_form{
  width: 400px;
  height: 500px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-60%, -50%);
}
</style>
