<template>
  <div class="wrapbox">
    <el-descriptions title="预约信息">
      <el-descriptions-item label="实训室名称">{{model.roomNo}}</el-descriptions-item>
      <el-descriptions-item label="学期">{{model.semeNo}}</el-descriptions-item>
      <el-descriptions-item label="预约人">{{model.userNo}}</el-descriptions-item>
      <el-descriptions-item label="预约信息">{{model.orderValue}}</el-descriptions-item>
      <el-descriptions-item label="预约时间">{{model.orderDtime}}</el-descriptions-item>
      <el-descriptions-item label="预约状态">{{model.checkState}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getRoomorders } from '@/api/labsys/roomorders'

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
      getRoomorders(this.id).then(response => {
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