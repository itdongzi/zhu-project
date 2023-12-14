<template>
  <div class="wrapbox">
    <el-descriptions title="考核信息">
      <el-descriptions-item label="考核人员">{{model.userNo}}</el-descriptions-item>
      <el-descriptions-item label="考核试卷">{{model.paperNo}}</el-descriptions-item>
      <el-descriptions-item label="地址">{{model.testUrl}}</el-descriptions-item>
      <el-descriptions-item label="考核时间">{{model.testDate}}</el-descriptions-item>
      <el-descriptions-item label="考核得分">{{model.testScore}}</el-descriptions-item>
      <el-descriptions-item label="考核情况">{{model.testDesc}}</el-descriptions-item>
      <el-descriptions-item label="考核状态">{{model.testState}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getSafetytest } from '@/api/labsys/safetytest'

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
        testNo: "0",
        userNo: "",
        paperNo: "0",
        testUrl: "0",
        testDate: "0",
        testScore: "0",
        testDesc: "0",
        testState: "1",
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
      getSafetytest(this.id).then(response => {
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