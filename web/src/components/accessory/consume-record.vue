<template>
<div>
  <el-table
    :data = "consumptionRecords"
    style="width: 100%">
    <el-table-column
      fixed="left"
      prop="id"
      label="编号"
      width="100">
    </el-table-column>
    <el-table-column
      prop="itemId"
      label="物品ID"
      width="150">
    </el-table-column>
    <el-table-column
      prop="itemName"
      label="物品名称"
      width="150">
    </el-table-column>
    <el-table-column
      prop="amount"
      label="数量"
      width="150">
    </el-table-column>
    <el-table-column
      prop="receiver"
      label="领取人"
      width="150">
    </el-table-column>
    <el-table-column
      prop="modifyTimestamp"
      label="领取时间"
      width="180">
    </el-table-column>
    <el-table-column
      prop="modifier"
      label="经手人"
      width="150">
    </el-table-column>
    <el-table-column
      prop="comment"
      label="备注"
      width="180">
    </el-table-column>
  </el-table>
</div>
</template>

<script>
export default {
  name: "accessory-consume-record",
  data(){return {
    consumptionRecords: [],
  }},
  methods: {

  },
  beforeMount() {
    this.$axios.get("http://localhost:18080/accessory/consumption/findAll",{withCredentials: true}).then(
      response=>{
        this.consumptionRecords = response.data["consumptionRecords"]
      }
    )
  }
}
</script>

<style scoped>
</style>
