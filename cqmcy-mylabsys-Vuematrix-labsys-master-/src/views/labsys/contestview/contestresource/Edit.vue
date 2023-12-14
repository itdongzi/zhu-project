<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="资源名称" prop="rescTitle">
        <el-input v-model="form.rescTitle" placeholder="请输入资源名称" />
      </el-form-item>
      <el-form-item label="竞赛名称" prop="contestNo">
        <el-input v-model="form.contestNo" placeholder="请输入竞赛名称" />
      </el-form-item>
      <el-form-item label="资源类别" prop="rescExt">
        <el-select v-model="form.rescExt" placeholder="请选择资源类别">
          <el-option
            key="1"
            label="图片"
            value="jpg"
          >
          </el-option>
          <el-option
            key="2"
            label="视频"
            value="mp4"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="资源上传" prop="rescUrl">
        <el-input v-model="form.rescUrl" placeholder="请输入资源上传" />
      </el-form-item>
      <el-form-item label="资源简介" prop="rescDesc">
        <el-input
          v-model="form.rescDesc"
          type="textarea"
          placeholder="请输入资源简介"
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
  import { getContestresource, addContestresource, uptContestresource } from '@/api/labsys/contestresource'
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
      areaList: [],
      orderList:[{label:'可预约',value:'1'},{label:'不可预约',value:'0'}],
      form: {
        rescNo: "0",
        contestNo: "",
        userNo: "",
        classNo: "1",
        rescTitle: "",
        rescUrl: "0",
        rescExt: "",
        rescDesc: "",
        rescDate: "",
        rescScore: "",
        rescState: "1",
        comments: "",
      },
      rules: {
        rescTitle: [{ required: true, message: "请输入名称", trigger: "blur" }],
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs['form'].validate((valid) => {
          if (valid) {
            if (that.form.rescNo === "0") {
            addContestresource(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptContestresource(that.form).then((response) => {
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
      getContestresource(this.id).then(response => {
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
