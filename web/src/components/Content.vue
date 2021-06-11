<template>
  <div>
    <el-container>
      <el-aside style="width: 15rem">
        <Navigator></Navigator>
      </el-aside>
      <el-container>
        <el-header style="height: 2rem"></el-header>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Navigator from "./Navigator";
export default {
  name: 'Content',
  components: {
    Navigator
  }
}
</script>

<style scoped>

</style>
