<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="项目名称" prop="projectTitle">
        <el-input v-model="form.projectTitle" placeholder="请输入项目名称" />
      </el-form-item>
      <el-form
        :inline="true"
        :model="formInline"
        class="demo-form-inline"
        label-width="120px"
      >
        <el-form-item label="项目类型" prop="classNo">
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
        <el-form-item style="floatleft" label="项目负责人" prop="projectTitle">
          <el-input
            v-model="form.projectTitle"
            placeholder="请输入项目负责人"
          />
        </el-form-item>
      </el-form>

      <el-form-item label="资料上传">
      <div style="border:1px solid #ccc;border-radius: 4px;">
        <input type="file" id="fileinput" style="display: none;" @change="checkFileSure"/> 
        <el-button icon="el-icon-document-copy" type="primary" size="small" style="margin-left: 15px;" @click="checkFile">选择文件</el-button>
        <span>{{fileName}}</span> 
      </div>
      </el-form-item>

      <el-form-item label="项目地址" prop="projectUrl">
        <el-input v-model="form.projectUrl" placeholder="请输入项目地址" />
      </el-form-item>
      <el-form-item label="立项时间" prop="publishDate">
        <el-date-picker
          v-model="form.publishDate"
          type="date"
          placeholder="选择日期"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="项目简介" prop="projectDesc">
        <el-input  rows="5"
          v-model="form.projectDesc"
          type="textarea"
          placeholder="请输入项目简介"
        ></el-input>
      </el-form-item>
      <el-form-item label="可用状态" prop="processState">
        <el-radio-group v-model="form.processState">
          <el-radio label="1">正常</el-radio>
          <el-radio label="2">停用</el-radio>
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
import {
  getProjectinfo,
  addProjectinfo,
  uptProjectinfo,
} from "@/api/labsys/projectinfo";
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
        { label: "请选择类型", value: "0" },
        { label: "Java教学", value: "1" },
        { label: "C#教学", value: "2" },
        { label: "其他教学", value: "3" },
      ],
      form: {
        projectNo: "0",
        userNo: "",
        classNo: "0",
        projectTitle: "",
        projectUrl: "",
        projectDesc: "",
        publishDate: "0",
        processState: "1",
        comments: "",
      },
      rules: {
        roomName: [{ required: true, message: "请输入名称", trigger: "blur" }],
        areaNo: [{ required: true, message: "请选择", trigger: "blur" }],
      },
      fileName: '',
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (that.form.projectNo === "0") {
            addProjectinfo(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptProjectinfo(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    checkFile () {
      document.querySelector('#fileinput').click()
    },
    checkFileSure (val) {
      console.log(document.querySelector('#fileinput').files[0])
      this.fileName = document.querySelector('#fileinput').files[0].name
    },
    doCancel() {
      this.$layer.close(this.layerid);
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
      getProjectinfo(this.id).then((response) => {
        that.form = response.data;
      });
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
