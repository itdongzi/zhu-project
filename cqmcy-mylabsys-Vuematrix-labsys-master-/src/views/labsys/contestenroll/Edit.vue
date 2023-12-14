<template>
  <div class="wrapbox">
    
    <el-descriptions title="实训室信息">
      <el-descriptions-item label="竞赛名称">{{form.contestNo}}</el-descriptions-item>
      <el-descriptions-item label="报名小组">{{form.groupNo}}</el-descriptions-item>
      <el-descriptions-item label="报名学生">{{form.userNo}}</el-descriptions-item>
      <el-descriptions-item label="个人简介">{{form.userProfile}}</el-descriptions-item>
      <el-descriptions-item label="报名时间">{{form.enrollDate}}</el-descriptions-item>
      <el-descriptions-item label="预约状态">{{form.orderState}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{form.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="报名审核" prop="checkState">
        <el-select v-model="form.checkState" placeholder="请选择">
          <el-option
            key="1"
            label="审核通过"
            value="1"
          >
          </el-option>
          <el-option
            key="0"
            label="审核不通过"
            value="0"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="doOk">确 定</el-button>
        <el-button size="small" @click="doCancel">取 消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import { getContestenroll, addContestenroll, uptContestenroll } from '@/api/labsys/contestenroll'
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
      contestList:[{label:'可预约',value:'1'},{label:'不可预约',value:'0'}],
      form: {
        enrollNo: "0",
        contestNo: "",
        groupNo: "0",
        userNo: "0",
        userProfile: "0",
        enrollDate: "0",
        checkState: "1",
        comments: "",
      },
      rules: {
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs['form'].validate((valid) => {
          if (valid) {
            if (that.form.enrollNo === "0") {
            addContestenroll(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptContestenroll(that.form).then((response) => {
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
      getContestenroll(this.id).then(response => {
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
