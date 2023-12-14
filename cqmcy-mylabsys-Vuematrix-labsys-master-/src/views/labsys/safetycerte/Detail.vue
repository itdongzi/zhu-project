<template>
  <div class="wrapbox">
    <el-descriptions title="证书信息">
      <el-descriptions-item label="证书名称">{{model.certeName}}</el-descriptions-item>
      <el-descriptions-item label="人员姓名">{{model.cuserName}}</el-descriptions-item>
      <el-descriptions-item label="证书">{{model.certeUrl}}</el-descriptions-item>
      <el-descriptions-item label="证书简介">{{model.certeDesc}}</el-descriptions-item>
      <el-descriptions-item label="获证时间">{{model.certeDate}}</el-descriptions-item>
      <el-descriptions-item label="有效期">{{model.expireDate}}</el-descriptions-item>
      <el-descriptions-item label="状态">{{model.certeState}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getSafetycerte } from '@/api/labsys/safetycerte'

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
        certeNo: "0",
        certeName: "0",
        cuserName: "",
        certeUrl: "0",
        certeDesc: "0",
        certeDate: "0",
        expireDate: "0",
        certeState: "1",
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
      getSafetycerte(this.id).then(response => {
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