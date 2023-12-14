<template>
  <div class="wrapbox">
    <el-descriptions title="资源信息">
      <el-descriptions-item label="资源名称">{{model.rescName}}</el-descriptions-item>
      <el-descriptions-item label="资源分类">{{model.classNo}}</el-descriptions-item>
      <el-descriptions-item label="资源简介">{{model.rescDesc}}</el-descriptions-item>
      <el-descriptions-item label="共享方式">{{model.shareType}}</el-descriptions-item>
      <el-descriptions-item label="资源列表">{{model.rescNo}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getResourceinfo } from '@/api/labsys/resourceinfo'

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
        rescName: "",
        classNo: "0",
        rescDesc: "0",
        areaNo: "0",
        orderNo: "0",
        shareType: "0",
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
      getResourceinfo(this.id).then(response => {
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