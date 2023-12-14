<template>
  <div class="wrapbox">
    <el-descriptions title="预约信息">
      <el-descriptions-item label="会议室名称">{{model.meetNo}}</el-descriptions-item>
      <el-descriptions-item label="预约人">{{model.userName}}</el-descriptions-item>
      <el-descriptions-item label="联系电话">{{model.userTel}}</el-descriptions-item>
      <el-descriptions-item label="预约时间">{{model.startDate}}-{{model.enditDate}}</el-descriptions-item>
      <el-descriptions-item label="会议主题">{{model.orderTitle}}</el-descriptions-item>
      <el-descriptions-item label="参会人员">{{model.orderUsers}}</el-descriptions-item>
      <el-descriptions-item label="会议简介">{{model.orderDesc}}</el-descriptions-item>
      <el-descriptions-item label="通知方式">{{model.notifyType}}</el-descriptions-item>
      <el-descriptions-item label="预约状态">{{model.checkState}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getMeetorders } from '@/api/labsys/meetorders'

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
        roomNo: "0",
        roomName: "",
        roomCode: "0",
        roomType: "0",
        areaNo: "0",
        classNo: "0",
        roomAdmin: "0",
        roomScale: 1,
        roomDesc: "",
        orderState: "",
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
      getMeetorders(this.id).then(response => {
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