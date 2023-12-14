<template>
  <div class="wrapbox">
    <el-descriptions title="项目信息">
      <el-descriptions-item label="项目名称">{{model.projectTitle}}</el-descriptions-item>
      <el-descriptions-item label="项目类别">{{model.classNo}}</el-descriptions-item>
      <el-descriptions-item label="项目地址">{{model.projectUrl}}</el-descriptions-item>
      <el-descriptions-item label="立项时间">{{model.publishDate}}</el-descriptions-item>
      <el-descriptions-item label="项目简介">{{model.projectDesc}}</el-descriptions-item>
      <el-descriptions-item label="项目状态">{{model.processState}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getProjectinfo } from '@/api/labsys/projectinfo'

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
        projectNo: "0",
        userNo: "",
        classNo: "0",
        projectTitle: "0",
        projectUrl: "0",
        projectDesc: "0",
        publishDate: "0",
        processState: "1",
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
      getProjectinfo(this.id).then(response => {
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