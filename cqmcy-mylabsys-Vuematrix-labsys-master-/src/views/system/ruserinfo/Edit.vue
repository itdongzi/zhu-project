<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="人员姓名" prop="userCnname">
        <el-input v-model="form.userCnname" placeholder="请输入人员姓名" />
      </el-form-item>
      <el-form-item label="类别" prop="userType">
        <el-radio-group v-model="form.userType">
          <el-radio label="1">教师</el-radio>
          <el-radio label="2">学生</el-radio>
          <el-radio label="3">其他</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="所属部门" prop="deptNo">
        <el-select v-model="form.deptNo" placeholder="请选择">
          <el-option
            v-for="item in deptList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="form.sex">
          <el-radio label="1">男</el-radio>
          <el-radio label="0">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="form.email" placeholder="请输入人员姓名" />
      </el-form-item>
      <el-form-item label="电话" prop="telephone">
        <el-input v-model="form.telephone" placeholder="请输入人员姓名" />
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
  import { getRuserinfo, addRuserinfo, uptRuserinfo } from '@/api/system/ruserinfo'
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
      deptList: [
        { label: "部门1", value: "1" },
        { label: "部门2", value: "0" },
      ],
      form: {
        userNo: "0",
        loginName: "",
        userCnname: "0",
        userEnname: "0",
        userType: "1",
        deptNo: "0",
        orgzNo: "0",
        postNo: 1,
        sex: "1",
        email: "",
        avatar:"",
        telephone:"",
        birthdate:"",        
        checkState: "1",
        comments: "",
      },
      rules: {
        userCnname: [{ required: true, message: "请输入名称", trigger: "blur" }],
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs['form'].validate((valid) => {
          if (valid) {
            if (that.form.userNo === "0") {
            addRuserinfo(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptRuserinfo(that.form).then((response) => {
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
      getRuserinfo(this.id).then(response => {
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
