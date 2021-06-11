<template>
<div>
  <el-tabs v-model="claimTab" type="card" @tab-click="handleClick">
    <el-tab-pane label="待归还" name="unfinished"></el-tab-pane>
    <el-tab-pane label="已归还" name="finished"></el-tab-pane>
  </el-tabs>

  <el-table
    :data = "issueRecords"
    style="width: 100%">
    <el-table-column
      prop="id"
      label="编号"
      fixed="left"
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
      prop="claimer"
      label="领用人"
      width="150">
    </el-table-column>
    <el-table-column
      prop="issueAmount"
      label="领取数量"
      width="150">
    </el-table-column>
    <el-table-column
      prop="modifyTimestamp"
      label="领用时间"
      width="150">
    </el-table-column>
    <el-table-column
      prop="reclaimedAmount"
      label="已还数量"
      width="150">
    </el-table-column>
    <el-table-column
      prop="reclaimTimestamp"
      label="归还时间"
      width="150">
    </el-table-column>
    <el-table-column
      prop="modifier"
      label="经手人"
      width="150">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="100">
      <template slot-scope="scope">
        <el-button @click="insertReclaimForm(scope.row)" type="text" size="small">归还</el-button>
        <el-button type="text" size="small">编辑</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog
    title="物品归还"
    :visible.sync="reclaimFormVisible"
    width="30%"
    :before-close="this.$global.handleClose">
    <el-form ref="reclaimForm" :model="reclaimForm" label-width="80px">
      <el-form-item label="领用单号" prop="id">
        <el-input v-model="reclaimForm.id" disabled></el-input>
      </el-form-item>
      <el-form-item label="物品ID" prop="itemId">
        <el-input v-model="reclaimForm.itemId" disabled></el-input>
      </el-form-item>
      <el-form-item label="物品名称" prop="itemName">
        <el-input v-model="reclaimForm.itemName" disabled></el-input>
      </el-form-item>
      <el-form-item label="归还数量" prop="reclaimAmount">
        <el-input v-model="reclaimForm.reclaimAmount" type="number"></el-input>
      </el-form-item>
      <el-form-item label="计量单位" prop="unitOfMeasurement">
        <el-input v-model="reclaimForm.unitOfMeasurement"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="reclaimFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="submitReclaimForm">确 定</el-button>
    </span>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "accessory-claim-record",
  data(){return {
    claimTab: "unfinished",
    issueRecords: [],
    reclaimFormVisible: false,
    reclaimForm: {
      id: "",
      itemId: "",
      itemName: "",
      reclaimAmount: "",
      unitOfMeasurement: "",
    },
    reclaimFormRules: {
      reclaimAmount: [{required: true, message: "归还数量"}]
    }
  }},
  methods: {
    handleClick(tab) {
      this.issueRecords = []
      if(tab.name==="unfinished") {
        this.findAllUnfinished();
      }
      if(tab.name==="finished") {

      }
    },

    findAllUnfinished(){
      this.$axios.get("http://localhost:18080/accessory/issue/findAll/unfinished",{withCredentials: true})
      .then(
        response=>{
          this.issueRecords = response.data["issueRecords"]
        }
      )
    },

    insertReclaimForm(issueRecord){
      this.$global.clean(this.reclaimForm);
      Object.assign(this.reclaimForm, {
        id: issueRecord.id,
        itemId: issueRecord.itemId,
        itemName: issueRecord.itemName,
        unitOfMeasurement: issueRecord.unitOfMeasurement,
      })
      this.reclaimFormVisible = true
    },

    submitReclaimForm(){},
    submitReclaimFormHandler() {
      this.$axios.post("http://localhost:18080/")
    }
  },
  mounted() {
    this.findAllUnfinished();
  }
}
</script>

<style scoped>

</style>
