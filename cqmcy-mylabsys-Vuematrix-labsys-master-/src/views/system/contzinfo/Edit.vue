<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="标题" prop="title">
        <el-input v-model="form.title" placeholder="" />
      </el-form-item>
      <el-form-item label="类型" prop="classNo">
        <el-select v-model="form.classNo" placeholder="请选择类型">
          <el-option label="通知公告" value="1"></el-option>
          <el-option label="规章制度" value="2"></el-option>
        </el-select>
      </el-form-item>

      <el-row>
        <el-col :span="12">
          <el-form-item label="发布作者" prop="author">
            <el-input v-model="form.author" placeholder="" :span="12" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预约时间" prop="pubdate">
            <el-date-picker
              v-model="form.pubdate"
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

      <el-form-item label="正文" prop="ncontents">
        <el-input type="textarea" :rows="2" v-model="form.ncontents">
        </el-input>
      </el-form-item>

      <el-form-item label="附件上传" prop="poster">
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
          <div slot="tip" class="el-upload__tip">
            只能上传jpg/png文件,且不超过500kb
          </div>
        </el-upload>
      </el-form-item>

      <el-form-item label="审核状态" prop="checkState">
        <el-radio-group v-model="form.checkState">
          <el-radio
            v-for="dict in checkList"
            :key="dict.value"
            :label="dict.value"
            >{{ dict.label }}
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
import {
  getContentinfo,
  addContentinfo,
  uptContentinfo,
} from "@/api/system/contentinfo";
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
      checkList: [
        { label: "正常", value: "1" },
        { label: "停用", value: "0" },
      ],
      fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}],
      form: {
        contzNo: "0",
        classNo: "",
        title: "",
        author: "",
        pubdate: "",
        poster: "0",
        abstracts: "",
        ncontents: "",
        hitCount: "",
        checkState: "",
        comments: "",
      },
      rules: {
        title: [{ required: true, message: "请输入名称", trigger: "blur" }],
        contzNo: [{ required: true, message: "请选择", trigger: "blur" }],
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (that.form.contzNo === "0") {
            addContentinfo(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptContentinfo(that.form).then((response) => {
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
    doCancel() {
      this.$layer.close(this.layerid);
    },
     handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      }
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
      getContentinfo(this.id).then((response) => {
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
