<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="竞赛名称" prop="contestName">
        <el-input v-model="form.contestName" placeholder="请输入竞赛名称" />
      </el-form-item>

      <el-row>
        <el-col :span="12">
      <el-form-item label="竞赛类型" prop="classNo">
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
      </el-col>
      <el-col :span="12">
        <el-form-item label="竞赛时间" prop="contestDate">
        <el-date-picker
              v-model="form.contestDate"
              type="datetime"
              format="yyyy-MM-dd HH:mm:ss"
              value-format="yyyy-MM-dd HH:mm:ss"
              clearable
              placeholder="开始时间"
              style="width: 100%"
            >
            </el-date-picker>
      </el-form-item>
      </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
      <el-form-item label="竞赛负责人" prop="contestAdmin">
        <el-input v-model="form.contestAdmin" placeholder="请输入竞赛负责人" />
      </el-form-item>
      </el-col>
      <el-col :span="12">
      <el-form-item label="指导老师" prop="contestGuider">
        <el-input v-model="form.contestGuider" placeholder="请输入指导老师" />
      </el-form-item>
      </el-col>
      </el-row>

      <el-form-item label="竞赛附件" prop="contestUrl">
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="fileList"
        >
          <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>

      <el-form-item label="竞赛简介" prop="contestDesc">
        <el-input
          v-model="form.contestDesc"
          type="textarea"
          placeholder="请输入竞赛简介"
        ></el-input>
      </el-form-item>

<el-row>
  <el-col :span="12">
<el-form-item label="开始时间" prop="trainStart">
        <el-date-picker
              v-model="form.trainStart"
              type="datetime"
              format="yyyy-MM-dd HH:mm:ss"
              value-format="yyyy-MM-dd HH:mm:ss"
              clearable
              placeholder="开始时间"
              style="width: 100%"
            >
            </el-date-picker>
      </el-form-item>
</el-col>
<el-col :span="12">
      <el-form-item label="结束时间" prop="trainEndit">
       <el-date-picker
              v-model="form.trainEndit"
              type="datetime"
              format="yyyy-MM-dd HH:mm:ss"
              value-format="yyyy-MM-dd HH:mm:ss"
              clearable
              placeholder="开始时间"
              style="width: 100%"
            >
            </el-date-picker>
      </el-form-item>
</el-col>      
</el-row>
      

      <el-form-item label="培训计划" prop="trainPlan">
        <el-input
          v-model="form.trainPlan"
          type="textarea"
          placeholder="请输入训练计划"
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
  import { getContestinfo, addContestinfo, uptContestinfo } from '@/api/labsys/contestinfo'
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
      classList: [
         { label: "H5开发", value: "1" },
        { label: "移动应用开发", value: "2" },
        { label: "软件测试", value: "3" },
      ],
      form: {
        contestNo: "0",
        classNo: "",
        contestName: "",
        contestAdmin: "",
        contestGuider: "",
        contestDate: "",
        contestUrl: "",
        contestDesc: "",
        trainStart: "",
        trainEndit: "",
        trainPlan:'',
        contestState: "1",
        comments: "",
      },
      rules: {
        contestName: [{ required: true, message: "请输入名称", trigger: "blur" }],
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs['form'].validate((valid) => {
          if (valid) {
            if (that.form.contestNo === "0") {
            addContestinfo(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptContestinfo(that.form).then((response) => {
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
      getContestinfo(this.id).then(response => {
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
