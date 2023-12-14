<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="考核人员" prop="userNo">
        <el-input v-model="form.userNo" placeholder="请输入考核人员" />
      </el-form-item>
      <el-form-item label="考核试卷" prop="paperNo">
        <el-input v-model="form.paperNo" placeholder="请输入考核试卷" />
      </el-form-item>
      <el-form-item label="考核资料" prop="testUrl">
        <el-input v-model="form.testUrl" placeholder="请输入考核试卷" />
      </el-form-item>
      <el-form-item label="考核日期" prop="testDate">
        <el-date-picker
      v-model="form.testDate"
      type="date"
      placeholder="选择日期">
    </el-date-picker>
      </el-form-item>
      <el-form-item label="考核得分" prop="testUrl">
        <el-input-number v-model="form.testScore" />
      </el-form-item>
      <el-form-item label="考核情况" prop="testDesc">
        <el-input
          v-model="form.testDesc"
          type="textarea"
          placeholder="请输入考核情况"
        ></el-input>
      </el-form-item>
      <el-form-item label="考核状态" prop="testState">
        <el-radio-group v-model="form.testState">
          <el-radio label="1">不合作</el-radio>
          <el-radio label="2">合格</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="备注" prop="comments">
        <el-input
          v-model="form.comments"
          type="textarea"
          placeholder="请输入备注"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="doOk">确 定</el-button>
        <el-button size="small" @click="doCancel">取 消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import { getSafetytest, addSafetytest, uptSafetytest } from '@/api/labsys/safetytest'
  //import { treeAreainfo } from "@/api/labsys/areainfo";

export default {
  name: "Edit",
  props: {
    layerid: {
      // 自动注入的layerid
      type: String,
      default: "",
    },
    id: {
      // 传递的数据
      type: String,
      default: "",
    },
    lydata: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  data() {
    return {
      form: {
        testNo: "0",
        userNo: "",
        paperNo: "0",
        testUrl: "0",
        testDate: "0",
        testScore: "0",
        testDesc: "0",
        testState: "1",
        comments: "",
      },
      rules: {
        roomName: [{ required: true, message: "请输入名称", trigger: "blur" }],
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs['form'].validate((valid) => {
          if (valid) {
            if (that.form.testNo === "0") {
            addSafetytest(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptSafetytest(that.form).then((response) => {
                that.$layer.msg(response.msg)
                that.$parent.getDataSource()
                that.$layer.close(that.layerid)
            });
          }
          } else {
            console.log('error submit!!');
            return false;
          }
        });
    },
    doCancel() {
      this.$layer.close(this.layerid)
    },
    /*
    getAreainfo() {
      treeAreainfo().then((res) => {
        this.areaList = res.data;
      });
    },
     */
  },
  mounted() {
    if (this.id !== "") {
      const that = this;
      getSafetytest(this.id).then(response => {
        that.form = response.data
      })
    }
    //this.getAreainfo();
  },
};
</script>

<style scoped lang="scss">
.wrapbox {
  margin: 0;
  padding: 30px;
  width: 100%;
}
</style>
