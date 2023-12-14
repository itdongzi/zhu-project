<template>
  <div class="wrapbox">
    <el-descriptions title="竞赛试卷信息">
      <el-descriptions-item label="试卷名称">{{model.paperTitle}}</el-descriptions-item>
      <el-descriptions-item label="试卷类别">{{model.paperType}}</el-descriptions-item>
      <el-descriptions-item label="竞赛名称">{{model.contestNo}}</el-descriptions-item>
      <el-descriptions-item label="试卷年份">{{model.paperYear}}</el-descriptions-item>
      <el-descriptions-item label="时长(分钟)">{{model.paperDuration}}</el-descriptions-item>
      <el-descriptions-item label="下载地址">{{model.paperUrl}}</el-descriptions-item>
      <el-descriptions-item label="试卷简介">{{model.paperDesc}}</el-descriptions-item>
      <el-descriptions-item label="状态">{{model.checkState}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getContestpaper } from '@/api/labsys/contestpaper'

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
        paperNo: "0",
        contestNo: "",
        classNo: "",
        paperTitle: "1",
        paperType: "1",
        paperYear: "2022",
        paperDuration: 120,
        paperUrl: "",
        paperDesc: "",
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
      getContestpaper(this.id).then(response => {
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