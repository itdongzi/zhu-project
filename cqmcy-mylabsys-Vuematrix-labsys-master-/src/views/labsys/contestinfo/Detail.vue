<template>
  <div class="wrapbox">
    <el-descriptions title="竞赛信息">
      <el-descriptions-item label="竞赛名称">{{model.contestName}}</el-descriptions-item>
      <el-descriptions-item label="竞赛类型">{{model.classNo}}</el-descriptions-item>
      <el-descriptions-item label="竞赛负责人">{{model.contestAdmin}}</el-descriptions-item>
      <el-descriptions-item label="竞赛指导人员">{{model.contestGuider}}</el-descriptions-item>
      <el-descriptions-item label="比赛时间">{{model.contestDate}}</el-descriptions-item>
      <el-descriptions-item label="竞赛附件">{{model.contestUrl}}</el-descriptions-item>
      <el-descriptions-item label="竞赛简介">{{model.contestDesc}}</el-descriptions-item>
      <el-descriptions-item label="培训时间">{{model.trainStart}}-{{model.trainEndit}}</el-descriptions-item>
      <el-descriptions-item label="培训计划">{{model.trainPlan}}</el-descriptions-item>
      <el-descriptions-item label="状态">{{model.contestState}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getContestinfo } from '@/api/labsys/contestinfo'

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
        contestNo: "0",
        classNo: "",
        contestName: "",
        contestAdmin: "1",
        contestGuider: "",
        contestDate: "0",
        contestUrl: "",
        contestDesc: "",
        trainStart: "",
        trainEndit: "",
        trainPlan:'',
        contestState: "1",
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
      getContestinfo(this.id).then(response => {
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