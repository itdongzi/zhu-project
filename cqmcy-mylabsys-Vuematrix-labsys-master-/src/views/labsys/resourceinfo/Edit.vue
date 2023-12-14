<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="资源名称" prop="rescName">
        <el-input v-model="form.rescName" placeholder="请输入资源名称" />
      </el-form-item>
      <el-form-item label="资源类型" prop="classNo">
        <el-select v-model="form.classNo" placeholder="请选择">
          <el-option
            v-for="item in classList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="共享方式" prop="shareType">
        <el-radio-group v-model="form.shareType">
          <el-radio label="1">共享</el-radio>
          <el-radio label="0">私有</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="资源简介" prop="rescDesc">
        <el-input
          v-model="form.rescDesc"
          type="textarea"
          placeholder="请输入资源简介"
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
  import { getResourceinfo, addResourceinfo, uptResourceinfo } from '@/api/labsys/resourceinfo'
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
      classList:[{label:'可预约',value:'1'},{label:'不可预约',value:'0'}],
      form: {
        rescNo: "0",
        rescName: "",
        classNo: "0",
        rescDesc: "0",
        orderNo: "0",
        shareType: "0",
        checkState: "1",
        comments: "",
      },
      rules: {
        rescName: [{ required: true, message: "请输入名称", trigger: "blur" }],
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs['form'].validate((valid) => {
          if (valid) {
            if (that.form.rescNo === "0") {
            addResourceinfo(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptResourceinfo(that.form).then((response) => {
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
      getResourceinfo(this.id).then(response => {
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
