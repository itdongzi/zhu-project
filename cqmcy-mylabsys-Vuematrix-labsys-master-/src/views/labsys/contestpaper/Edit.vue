<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="试卷名称" prop="paperTitle">
        <el-input v-model="form.paperTitle" placeholder="请输入试卷名称" />
      </el-form-item>
      <el-form-item label="竞赛名称" prop="contestNo">
        <el-select v-model="form.contestNo" placeholder="请选择">
          <el-option
            v-for="item in contestList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="试卷类别" prop="paperType">
        <el-select v-model="form.paperType" placeholder="请选择">
          <el-option
            key="1"
            label="正式试卷"
            value="1"
          >
          </el-option>
          <el-option
            key="2"
            label="练习试卷"
            value="3"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="考试年度" prop="paperYear">
        <el-select v-model="form.paperYear" placeholder="请选择">
          <el-option
            v-for="item in yearList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="考试时长" prop="paperDuration">
        <el-input-number v-model="form.paperDuration"  />
      </el-form-item>
      <el-form-item label="试卷上传" prop="paperUrl">
        <el-input v-model="form.paperUrl" placeholder="请输入实训室名称" />
      </el-form-item>
      <el-form-item label="试卷简介" prop="paperDesc">
        <el-input
          v-model="form.paperDesc"
          type="textarea"
          placeholder="请输入试卷简介"
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
  import { getContestpaper, addContestpaper, uptContestpaper } from '@/api/labsys/contestpaper'
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
      contestList: [{label:'移动应用开发',value:'1'},{label:'软件测试',value:'2'}],
      yearList:[{label:'2021',value:'2021'},{label:'2022',value:'2022'}],
      form: {
        paperNo: "0",
        contestNo: "",
        classNo: "1",
        paperTitle: "1",
        paperType: "1",
        paperYear: "2022",
        paperDuration: 120,
        paperUrl: "",
        paperDesc: "",
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
            addContestpaper(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptContestpaper(that.form).then((response) => {
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
      getContestpaper(this.id).then(response => {
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
