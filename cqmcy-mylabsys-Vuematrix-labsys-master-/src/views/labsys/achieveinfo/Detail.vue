<template>
  <div class="wrapbox">
    <el-descriptions title="成果信息">
      <el-descriptions-item label="成果名称">{{model.achieTitle}}</el-descriptions-item>
      <el-descriptions-item label="成果类别">{{model.classNo}}</el-descriptions-item>
      <el-descriptions-item label="成果地址">{{model.achieUrl}}</el-descriptions-item>
      <el-descriptions-item label="登记时间">{{model.achieDate}}</el-descriptions-item>
      <el-descriptions-item label="成果简介">{{model.achieDesc}}</el-descriptions-item>
      <el-descriptions-item label="审核状态">{{model.achieState}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getAchieveinfo } from '@/api/labsys/achieveinfo'

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
        achieNo: "0",
        achieTitle: "",
        userNo: "",
        classNo: "1",
        achieUrl: "",
        achieDesc: "0",
        achieDate: "",
        achieState: "1",
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
      getAchieveinfo(this.id).then(response => {
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