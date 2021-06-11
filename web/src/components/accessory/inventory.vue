<template>
  <div>
    <!--suppress HtmlUnknownTag -->
    <el-table
      :data = accessories
      style="width: 100%">
      <el-table-column
        prop="itemId"
        label="物品ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="物品名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="数量">
      </el-table-column>
      <el-table-column
        prop="unitOfMeasurement"
        label="计量单位">
      </el-table-column>
      <el-table-column
        prop="modifierName"
        label="修改人">
      </el-table-column>
      <el-table-column
        prop="modifyTimestamp"
        label="修改时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="insertStockoutForm(scope.row)">出库</el-button>
          <el-button type="text" size="small" @click="insertStockinForm(scope.row)">入库</el-button>
          <el-button type="text" size="small" @click="editItemForm(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row :gutter="10" type="flex">
     <el-button type="primary" style="margin: 1rem 0 0 auto" @click="insertItemForm">新建</el-button>
    </el-row>

<!--    物品信息表 -->
    <el-dialog
      title="物品信息表"
      :visible.sync="itemDialogVisible"
      width="30%"
      :before-close="this.$global.handleClose">
      <el-form ref="itemForm" :model="itemForm" label-width="80px" :rules="itemFormRules">
        <el-form-item label="物品ID" prop="itemId">
          <el-input v-model="itemForm.itemId" placeholder="请输入物品ID" @blur="findItemId" type="number"
                    :disabled="itemFormType==='update'"/>
          <div>
            <el-tag type="success" v-if="itemIdFlag">物品ID可用</el-tag>
            <el-tag type="danger" v-if="!itemIdFlag">物品ID已存在</el-tag>
          </div>
        </el-form-item>
        <el-form-item label="物品名称" prop="name">
          <el-input v-model="itemForm.name" placeholder="请输入物品名称"/>
        </el-form-item>
        <el-form-item label="计量单位" prop="unitOfMeasurement" :disabled="itemFormType==='update'">
          <el-input v-model="itemForm.unitOfMeasurement" placeholder="请输入计量单位"/>
        </el-form-item>
        <el-form-item label="初始库存" prop="amount">
          <el-input v-model="itemForm.amount" placeholder="请输入初始库存" type="number"
                    :disabled="itemFormType==='update'"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="itemDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitItemForm">确 定</el-button>
      </span>
    </el-dialog>

<!--    出库表单 -->
    <el-dialog
      title="出库单"
      :visible.sync="stockoutFormVisible"
      width="30%"
      :before-close="this.$global.handleClose">
      <el-form ref="stockoutForm" :model="stockoutForm" label-width="80px" :rules="stockoutFormRules">
        <el-form-item label="物品ID" prop="itemId">
          <el-input v-model="stockoutForm.itemId" disabled></el-input>
        </el-form-item>
        <el-form-item label="物品名称" prop="itemName">
          <el-input v-model="stockoutForm.itemName" disabled></el-input>
        </el-form-item>
        <el-form-item label="出库数量" prop="amount">
          <el-input v-model="stockoutForm.amount" placeholder="请输入出库数量"></el-input>
        </el-form-item>
        <el-form-item label="出库类型" prop="typeCode">
          <el-select v-model="stockoutForm.typeCode" placeholder="请选择出库类型">
            <el-option
              v-for="item in [{value: 0, label: '消耗'}, {value: 1, label: '借出'}]"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="领取人" prop="receiver">
          <el-input v-model="stockoutForm.receiver" placeholder="请输入领取人姓名"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="comment">
          <el-input v-model="stockoutForm.comment" placeholder="备注"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="stockoutFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitStockoutForm">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="入库"
      :visible.sync="stockinFormVisible"
      width="30%"
      :before-close="this.$global.handleClose">
      <el-form ref="stockinForm" :model="stockinForm" label-width="80px" :rules="stockoutFormRules">
        <el-form-item label="物品ID" prop="itemId">
          <el-input v-model="stockinForm.itemId" disabled></el-input>
        </el-form-item>
        <el-form-item label="物品名称" prop="itemName">
          <el-input v-model="stockinForm.itemName" disabled></el-input>
        </el-form-item>
        <el-form-item label="数量" prop="amount">
          <el-input v-model="stockinForm.amount" placeholder="请输入入库数量" type="number"></el-input>
        </el-form-item>
        <el-form-item label="计量单位" prop="unitOfMeasurement">
          <el-input v-model="stockinForm.unitOfMeasurement" disabled=""></el-input>
        </el-form-item>
        <el-form-item label="供应商" prop="supplierId">
          <el-select
            filterable
            remote
            :remote-method = "findSupplier"
            v-model="stockinForm.supplierId"
            placeholder="请选择供应商">
            <el-option
              v-for="item in suppliers"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="stockinForm.comment" placeholder="备注" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="stockinFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitStockinForm">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "accessory-inventory",
  data(){return {
    itemDialogVisible: false,
    itemForm: {
      itemId: "",
      name: "",
      unitOfMeasurement: "",
      amount: "",
    },
    itemIdFlag: true,
    itemFormRules: {
      itemId: [{required: true, message: "请输入物品ID"}],
      name: [{required: true, message: "请输入物品名称", trigger: "blur"}],
      unitOfMeasurement: [{required: true, message: "请输入计量单位", trigger: "blur"}],
      amount: [{required: true, message: "请输入初始库存", trigger: "blur"}],
    },
    itemFormType: "insert",
    accessories:[],
    stockoutForm: {
      itemId: "",
      itemName: "",
      amount: "",
      receiver: "",
      comment: "",
      typeCode: "",
    },
    stockoutFormRules: {
      amount: [{required: true, message: "请输入数量"}],
      receiver: [{required: true, message: "请输入领用人"}],
      typeCode: [{required: true, message: "请选择出库类型"}]
    },
    stockoutFormVisible: false,

    stockinForm: {
      itemId: "",
      itemName: "",
      unitOfMeasurement: "",
      amount: "",
      supplierId: "",
      comment: "",
    },
    stockinFormVisible: false,
    stockinFormRules: {
      amount: [{required: true, message: "请输入数量"}],
    },
    suppliers: [],
  }},

  methods: {
    findItemId(){
      if(this.itemForm.itemId===""){
        alert("请输入物品ID")
        return
      }
      this.$axios.get("http://localhost:18080/accessory/accessory/findItemId?itemId="+this.itemForm.itemId)
      .then(
        response=>{
          this.itemIdFlag = response.data["itemIdFlag"] == false
        }
      )
    },

    submitItemForm(){
      if(this.itemFormType==="insert" && !this.itemIdFlag) {
        alert("物品ID已存在，请更换物品ID");
        return;
      }
      this.$global.confirmSubmit(this.submitItemFormHandler, "itemForm", this)
    },
    submitItemFormHandler(){
      this.$axios.post("http://localhost:18080/accessory/accessory/insert", {
        accessory: this.itemForm
      }, {withCredentials: true, notification: true})
      this.itemDialogVisible = false
    },

    submitStockoutForm(){
      this.$global.confirmSubmit(this.submitStockoutFormHandler, "stockoutForm", this);
    },
    submitStockoutFormHandler(){
      this.$axios.post("http://localhost:18080/accessory/stockout/insertStockoutRecord", {
        stockoutRecord: this.stockoutForm
      }, {withCredentials: true, notification: true})
      this.stockoutFormVisible = false
    },

    insertItemForm() {
      this.itemFormType = "insert"
      this.itemDialogVisible=true
      this.$global.clean(this.itemForm)
      this.itemIdFlag = true
    },
    editItemForm(item) {
      this.itemFormType = "update"
      this.$global.clean(this.itemForm)
      this.itemDialogVisible = true
      Object.assign(this.itemForm, {
        itemId: item.itemId,
        name: item.name,
        unitOfMeasurement: item.unitOfMeasurement,
        amount: item.amount,
      })
    },

    insertStockoutForm(item){
      this.stockoutFormVisible = true
      this.$global.clean(this.stockoutForm)
      Object.assign(this.stockoutForm, {
        itemId: item.itemId,
        itemName: item.name,
      })
    },

    insertStockinForm(item) {
      this.stockinFormVisible = true
      this.$global.clean(this.stockinForm)
      Object.assign(this.stockinForm, {
        itemId: item.itemId,
        itemName: item.name,
        unitOfMeasurement: item.unitOfMeasurement,
      })
      this.findSupplier("")
    },

    submitStockinForm(){this.$global.confirmSubmit(this.submitStockinFormHandler, "stockinForm", this);},
    submitStockinFormHandler(){
      this.$axios.post("http://localhost:18080/accessory/stockin/insert", {
        stockinRecord: this.stockinForm
      }, {withCredentials: true, notification: true});
    },
    findSupplier(name) {
      this.$axios.post("http://localhost:18080/supplier/supplier/findAll/by", {
        searchCommand: {column: "name", content: name, criteria: ""}
      }).then(
        response=>{
          this.suppliers = response.data["suppliers"]
        }
      )
    }
  },

  mounted() {
    this.$nextTick(()=>{
      this.$axios.get("http://localhost:18080/accessory/accessory/findAll",{withCredentials: true})
        .then(response=>{
          this.accessories = response.data["accessories"]
        })
    })
  }
}
</script>

<style scoped>

</style>
