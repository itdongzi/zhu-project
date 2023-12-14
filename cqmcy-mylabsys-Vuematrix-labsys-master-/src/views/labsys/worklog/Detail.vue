<template>
  <div class="wrapbox">
    <el-descriptions title="实训室信息">
      <el-descriptions-item label="实训室名称">{{model.roomNo}}</el-descriptions-item>
      <el-descriptions-item label="姓名">{{model.userNo}}</el-descriptions-item>
      <el-descriptions-item label="分类">{{model.classNo}}</el-descriptions-item>
      <el-descriptions-item label="标题">{{model.workTitle}}</el-descriptions-item>
      <el-descriptions-item label="检查方式">{{model.workMode}}</el-descriptions-item>
      <el-descriptions-item label="检查内容">{{model.workContent}}</el-descriptions-item>
      <el-descriptions-item label="图片">{{model.workPicture}}</el-descriptions-item>
      <el-descriptions-item label="声音">{{model.workSound}}</el-descriptions-item>
      <el-descriptions-item label="视频">{{model.workVideo}}</el-descriptions-item>
      <el-descriptions-item label="检查结果">{{model.workResult}}</el-descriptions-item>
      <el-descriptions-item label="检查时间">{{model.workDate}}</el-descriptions-item>
      <el-descriptions-item label="得分">{{model.workTscore}}</el-descriptions-item>
      <el-descriptions-item label="状态">{{model.workState}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getWorklog } from '@/api/labsys/worklog'

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
      getWorklog(this.id).then(response => {
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