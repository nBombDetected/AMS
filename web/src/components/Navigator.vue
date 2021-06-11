<template>
  <div>
      <h5>管理系统</h5>
      <el-menu
        default-active="1"
        @open="handleOpen"
        @close="handleClose"
        unique-opened
        :router=true>
        <el-menu-item index="/home">
          <i class="el-icon-house"></i>
          <span slot="title">主页</span>
        </el-menu-item>

        <el-submenu index="accessory">
          <template slot="title">
            <i class="el-icon-files"></i>
            <span>零配件管理</span>
          </template>

          <el-menu-item-group>
            <el-menu-item index="/accessory/inventory">零配件库存</el-menu-item>
            <el-menu-item index="/accessory/claim-record">领用记录</el-menu-item>
            <el-menu-item index="/accessory/consume-record">消耗品使用记录</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
  </div>
</template>

<script>
export default {
  name: "Navigator",
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style scoped>

</style>
