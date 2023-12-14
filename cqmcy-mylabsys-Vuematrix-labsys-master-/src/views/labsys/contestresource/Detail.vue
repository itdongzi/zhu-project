<template>
  <div class="wrapbox">
    <el-descriptions title="竞赛资源信息">
      <el-descriptions-item label="资源名称">{{model.rescTitle}}</el-descriptions-item>
      <el-descriptions-item label="资源类别">{{model.rescExt}}</el-descriptions-item>
      <el-descriptions-item label="竞赛名称">{{model.contestNo}}</el-descriptions-item>
      <el-descriptions-item label="上传作者">{{model.userNo}}</el-descriptions-item>
      <el-descriptions-item label="上传时间">{{model.rescDate}}</el-descriptions-item>
      <el-descriptions-item label="资源简介">{{model.rescDesc}}</el-descriptions-item>
      <el-descriptions-item label="资源地址">{{model.rescUrl}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getContestresource } from '@/api/labsys/contestresource'

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
      getContestresource(this.id).then(response => {
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