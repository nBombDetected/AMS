import Vue from "vue";
let $global = new Vue({
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {});
    },
    confirmSubmit(func, formName, _this){
      let validate = true
      _this.$refs[formName].validate((valid) => {
        if (!valid) {
          validate = false
        }
      });
      if(!validate) return

      this.$confirm("是否确认提交?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(()=>{
        func()
        this.clean(this.$refs[formName])
      }).catch(() => {
      })
    },
    goBack(){
      history.go(-1)
    },
    clean(object){
      Object.keys(object).forEach(key=>object[key]='');
    },
  }})

export default $global
