<template>
  <div class="wrapbox">
    <el-descriptions title="资料信息">
      <el-descriptions-item label="资料名称">{{model.fileTitle}}</el-descriptions-item>
      <el-descriptions-item label="资料类别">{{model.safeClass}}</el-descriptions-item>
      <el-descriptions-item label="资料格式">{{model.fileFormat}}</el-descriptions-item>
      <el-descriptions-item label="资料地址">{{model.fileUrl}}</el-descriptions-item>
      <el-descriptions-item label="审核状态">{{model.checkState}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getSafetyfile } from '@/api/labsys/safetyfile'

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
        fileNo: "0",
        classNo: "",
        fileTitle: "",
        fileType: "",
        safeClass: "",
        fileFormat: "",
        fileUrl: "",
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
      getSafetyfile(this.id).then(response => {
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