<template>
  <div class="wrapbox">
    <el-descriptions title="人员信息">
      <el-descriptions-item label="姓名">{{model.userCnname}}</el-descriptions-item>
      <el-descriptions-item label="类别">{{model.userType}}</el-descriptions-item>
      <el-descriptions-item label="所属部门">{{model.deptNo}}</el-descriptions-item>
      <el-descriptions-item label="性别">{{model.sex}}</el-descriptions-item>
      <el-descriptions-item label="邮箱">{{model.email}}</el-descriptions-item>
      <el-descriptions-item label="电话">{{model.telephone}}</el-descriptions-item>
      <el-descriptions-item label="出生日期">{{model.birthdate}}</el-descriptions-item>
      <el-descriptions-item label="当前状态">{{model.checkState}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getRuserinfo } from '@/api/system/ruserinfo'

export default {
  name: "Detail",
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
      model: {
        userNo: "0",
        loginName: "",
        userCnname: "0",
        userEnname: "0",
        userType: "0",
        deptNo: "0",
        orgzNo: "0",
        postNo: 1,
        sex: "",
        email: "",
        avatar:"",
        telephone:"",
        birthdate:"",        
        checkState: "1",
        comments: "",
      },
    };
  },
  methods: {
    doClose() {
      this.$layer.close(this.layerid)
    },
  },
  mounted() {
    if (this.id !== "") {
      const that = this;
      getRuserinfo(this.id).then(response => {
        that.model = response.data
      })
    }
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