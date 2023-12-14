<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="实训室名称" prop="roomNo">
        <el-input v-model="form.roomNo" placeholder="请输入实训室名称" />
      </el-form-item>
      <el-form-item label="所属类型" prop="classNo">
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
      <el-form-item label="标题" prop="workTitle">
        <el-input v-model="form.workTitle" placeholder="请输入标题" />
      </el-form-item>
      <el-form-item label="检查方式" prop="workMode">
        <el-input v-model="form.workMode" placeholder="请输入实训室名称" />
      </el-form-item>
      <el-form-item label="检查内容" prop="workContent">
        <el-input
          v-model="form.workContent"
          type="textarea"
          placeholder="请输入检查内容"
        ></el-input>
      </el-form-item>
      <el-form-item label="图片" prop="workPicture">
        <el-input v-model="form.workPicture" placeholder="请输入图片" />
      </el-form-item>
      <el-form-item label="声音" prop="workSound">
        <el-input v-model="form.workSound" placeholder="请输入声音" />
      </el-form-item>
      <el-form-item label="视频" prop="workVideo">
        <el-input v-model="form.workVideo" placeholder="请输入视频" />
      </el-form-item>
      <el-form-item label="检查结果" prop="workResult">
        <el-input
          v-model="form.workResult"
          type="textarea"
          placeholder="请输入检查结果"
        ></el-input>
      </el-form-item>
      <el-form-item label="检查时间" prop="workDate">
        <el-date-picker
      v-model="form.workDate"
      type="date"
      placeholder="选择日期">
    </el-date-picker>
      </el-form-item>
      <el-form-item label="得分" prop="workTscore">
        <el-input-number v-model="form.workTscore" placeholder="请输入得分" />
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
  import { getWorklog, addWorklog, uptWorklog } from '@/api/labsys/worklog'
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
        workNo: "0",
        roomNo: "",
        userNo: "0",
        dataType: "0",
        classNo: "0",
        workTitle: "0",
        workMode: "0",
        workContent: "",
        workPicture: "",
        workSound: "",
        workVideo: "",
        workResult: "",
        workDate: "",
        workTscore: "",
        workState: "1",
        comments: "",
      },
      rules: {
        workTitle: [{ required: true, message: "请输入名称", trigger: "blur" }],
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs['form'].validate((valid) => {
          if (valid) {
            if (that.form.workNo === "0") {
            addWorklog(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptWorklog(that.form).then((response) => {
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
      getWorklog(this.id).then(response => {
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
