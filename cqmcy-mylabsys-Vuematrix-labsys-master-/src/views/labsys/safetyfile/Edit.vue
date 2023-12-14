<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="资料名称" prop="fileTitle">
        <el-input v-model="form.fileTitle" placeholder="请输入资料名称" />
      </el-form-item>
      <el-form-item label="资料类型" prop="safeClass">
        <el-radio-group v-model="form.safeClass">
          <el-radio label="1">防火安全</el-radio>
          <el-radio label="2">防电安全</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="资料格式" prop="fileFormat">
        <el-select v-model="form.fileFormat" placeholder="请选择">
          <el-option value="rar" >压缩格式</el-option>
          <el-option value="pdf" >pdf格式</el-option>
          <el-option value="jpg" >图片格式</el-option>
          <el-option value="mp4" >视频格式</el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="上传地址" prop="fileUrl">
        <el-input v-model="form.fileUrl" placeholder="请输入上传地址" />
      </el-form-item>
      <el-form-item label="审核状态" prop="checkState">
        <el-radio-group v-model="form.checkState">
          <el-radio
            v-for="item in stateList"
            :key="item.value"
            :label="item.value"
          >{{item.label}}
          </el-radio>
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
  import { getSafetyfile, addSafetyfile, uptSafetyfile } from '@/api/labsys/safetyfile'
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
      stateList:[{label:'已审核',value:"1"},{label:'待审核',value:'0'}],
      form: {
        fileNo: "0",
        classNo: "",
        fileTitle: "",
        fileType: "",
        safeClass: "",
        fileFormat: "",
        fileUrl: "",
        checkState: "1",
        comments: "",
      },
      rules: {
        fileTitle: [{ required: true, message: "请输入名称", trigger: "blur" }],
        fileUrl: [{ required: true, message: "请输入Url", trigger: "blur" }],
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs['form'].validate((valid) => {
          if (valid) {
            if (that.form.fileNo === "0") {
            addSafetyfile(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptSafetyfile(that.form).then((response) => {
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
      getSafetyfile(this.id).then(response => {
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
