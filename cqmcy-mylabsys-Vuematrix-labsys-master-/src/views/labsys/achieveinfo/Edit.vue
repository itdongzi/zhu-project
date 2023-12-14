<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="成果名称" prop="achieTitle">
        <el-input v-model="form.achieTitle" placeholder="请输入成果名称" />
      </el-form-item>
      <el-form-item label="成果类别" prop="classNo">
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
      <el-form-item label="成果地址" prop="achieUrl">
        <el-input v-model="form.achieUrl" placeholder="请输入成果地址" />
      </el-form-item>
      <el-form-item label="审核状态" prop="achieState">
        <el-radio-group v-model="form.achieState">
          <el-radio label="1">暂存</el-radio>
          <el-radio label="2">归档</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="简介" prop="achieDesc">
        <el-input
          v-model="form.achieDesc"
          type="textarea"
          placeholder="请输入成果简介"
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
  import { getAchieveinfo, addAchieveinfo, uptAchieveinfo } from '@/api/labsys/achieveinfo'
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
        achieNo: "0",
        achieTitle: "",
        userNo: "",
        classNo: "1",
        achieUrl: "",
        achieDesc: "0",
        achieDate: "",
        achieState: "1",
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
            if (that.form.achieNo === "0") {
            addAchieveinfo(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptAchieveinfo(that.form).then((response) => {
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
      getAchieveinfo(this.id).then(response => {
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
