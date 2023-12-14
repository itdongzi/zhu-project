<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="会议室名称" prop="meetName">
        <el-input v-model="form.meetName" placeholder="请输入会议室名称" />
      </el-form-item>
      <el-form-item label="实训室类型" prop="classNo">
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
      <el-form-item label="所属区域" prop="areaNo">
        <el-select v-model="form.areaNo" placeholder="请选择">
          <el-option
            v-for="item in areaList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="管理员" prop="meetAdmin">
        <el-input v-model="form.meetAdmin" placeholder="请输入管理员" />
      </el-form-item>
      <el-form-item label="会议室规模" prop="meetScale">
        <el-input v-model="form.meetScale" placeholder="请输入会议室规模" />
      </el-form-item>
      <el-form-item label="会议室简介" prop="meetDesc">
        <el-input
          v-model="form.meetDesc"
          type="textarea"
          placeholder="请输入会议室简介"
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
import { getMeetinfo, addMeetinfo, uptMeetinfo } from "@/api/labsys/meetinfo";
import { treeAreainfo } from "@/api/labsys/areainfo";

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
      areaList: [],
      classList:[{label:'可预约',value:'1'},{label:'不可预约',value:'0'}],
      form: {
        meetNo: "0",
        meetName: "",
        meetCode: "0",
        areaNo: "0",
        classNo: "0",
        meetAdmin: "0",
        meetScale: 1,
        meetDesc: "",
        orderState: "",
        checkState: "1",
        comments: "",
      },
      rules: {
        meetName: [{ required: true, message: "请输入名称", trigger: "blur" }],
        //areaNo: [{ required: true, message: "请选择", trigger: "blur" }],
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs['form'].validate((valid) => {
          if (valid) {
            if (that.form.meetNo === "0") {
            addMeetinfo(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptMeetinfo(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          }
          } else {
            console.log('error submit!!');
            return false;
          }
        });
    },
    doCancel() {
      this.$layer.close(this.layerid);
    },
    getAreainfo() {
      treeAreainfo().then((res) => {
        this.areaList = res.data;
      });
    },
  },
  mounted() {
    if (this.id !== "") {
      const that = this;
      getMeetinfo(this.id).then((response) => {
        that.form = response.data;
      });
    }
    this.getAreainfo();
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
