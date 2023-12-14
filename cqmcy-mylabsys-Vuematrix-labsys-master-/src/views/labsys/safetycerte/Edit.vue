<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="证书名称" prop="certeName">
        <el-input v-model="form.certeName" placeholder="请输入证书名称" />
      </el-form-item>
      <el-form-item label="人员姓名" prop="cuserName">
        <el-input v-model="form.cuserName" placeholder="请输入人员姓名" />
      </el-form-item>
      <el-form-item label="证书" prop="certeUrl">
        <el-input v-model="form.certeUrl" placeholder="请输入证书" />
      </el-form-item>
      <el-form-item label="获证时间" prop="certeDate">
        <el-date-picker
      v-model="form.certeDate"
      type="date"
      placeholder="选择日期">
    </el-date-picker>
      </el-form-item>
      <el-form-item label="有效期" prop="expireDate">
        <el-date-picker
      v-model="form.expireDate"
      type="date"
      placeholder="选择日期">
    </el-date-picker>
      </el-form-item>
      <el-form-item label="证书简介" prop="certeDesc">
        <el-input
          v-model="form.certeDesc"
          type="textarea"
          placeholder="请输入证书简介"
        ></el-input>
      </el-form-item>
      <el-form-item label="状态" prop="certeState">
        <el-radio-group v-model="form.certeState">
          <el-radio label="1">通过</el-radio>
          <el-radio label="2">未通过</el-radio>
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
  import { getSafetycerte, addSafetycerte, uptSafetycerte } from '@/api/labsys/safetycerte'
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
        certeNo: "0",
        cuserName: "",
        certeName: "0",
        certeUrl: "0",
        certeDesc: "0",
        certeDate: "0",
        expireDate: "0",
        certeState: "1",
        comments: "",
      },
      rules: {
        cuserName: [{ required: true, message: "请输入名称", trigger: "blur" }],
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs['form'].validate((valid) => {
          if (valid) {
            if (that.form.certeNo === "0") {
            addSafetycerte(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptSafetycerte(that.form).then((response) => {
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
      getSafetycerte(this.id).then(response => {
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
