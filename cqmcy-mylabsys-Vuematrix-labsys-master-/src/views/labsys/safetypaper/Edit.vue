<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="试卷名称" prop="paperTitle">
        <el-input v-model="form.paperTitle" placeholder="请输入试卷名称" />
      </el-form-item>
      <el-form-item label="试卷类别" prop="paperType">
        <el-radio-group v-model="form.paperType">
          <el-radio label="1">公共</el-radio>
          <el-radio label="2">专业</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="时长" prop="paperDuration">
        <el-input-number v-model="form.paperDuration" />
      </el-form-item>
      <el-form-item label="地址" prop="paperUrl">
        <el-input v-model="form.paperUrl" placeholder="请输入地址" />
      </el-form-item>
      <el-form-item label="简介" prop="paperDesc">
        <el-input
          v-model="form.paperDesc"
          type="textarea"
          placeholder="请输入简介"
        ></el-input>
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
  import { getSafetypaper, addSafetypaper, uptSafetypaper } from '@/api/labsys/safetypaper'
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
        paperNo: "0",
        paperTitle: "",
        paperType: "0",
        paperDuration: "0",
        paperUrl: "0",
        paperDesc: "0",
        shareType: "0",
        checkState: "1",
        comments: "",
      },
      rules: {
        roomName: [{ required: true, message: "请输入名称", trigger: "blur" }],
        areaNo: [{ required: true, message: "请选择", trigger: "blur" }],
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs['form'].validate((valid) => {
          if (valid) {
            if (that.form.paperNo === "0") {
            addSafetypaper(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptSafetypaper(that.form).then((response) => {
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
      getSafetypaper(this.id).then(response => {
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
