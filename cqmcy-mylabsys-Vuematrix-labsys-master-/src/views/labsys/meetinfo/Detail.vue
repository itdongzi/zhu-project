<template>
  <div class="wrapbox">
    <el-descriptions title="会议室信息">
      <el-descriptions-item label="会议室名称">{{model.meetName}}</el-descriptions-item>
      <el-descriptions-item label="会议室类别">{{model.classNo}}</el-descriptions-item>
      <el-descriptions-item label="所属区域">{{model.areaNo}}</el-descriptions-item>
      <el-descriptions-item label="预约状态">{{model.orderState}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
import { getMeetinfo } from "@/api/labsys/meetinfo";

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
        meetNo: "0",
        neetName: "",
        neetCode: "0",
        areaNo: "0",
        classNo: "0",
        meetAdmin: "0",
        meetScale: 1,
        meetDesc: "",
        orderState: "",
        checkState: "1",
        comments: "",
      },
    };
  },
  methods: {
    doClose() {
      this.$layer.close(this.layerid);
    },
  },
  mounted() {
    if (this.id !== "") {
      const that = this;
      getMeetinfo(this.id).then((response) => {
        that.model = response.data;
      });
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
