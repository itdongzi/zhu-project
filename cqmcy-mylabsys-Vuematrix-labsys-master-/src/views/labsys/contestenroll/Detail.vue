<template>
  <div class="wrapbox">
    <el-descriptions title="实训室信息">
      <el-descriptions-item label="竞赛名称">{{model.contestNo}}</el-descriptions-item>
      <el-descriptions-item label="报名小组">{{model.groupNo}}</el-descriptions-item>
      <el-descriptions-item label="报名学生">{{model.userNo}}</el-descriptions-item>
      <el-descriptions-item label="个人简介">{{model.userProfile}}</el-descriptions-item>
      <el-descriptions-item label="报名时间">{{model.enrollDate}}</el-descriptions-item>
      <el-descriptions-item label="预约状态">{{model.orderState}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getContestenroll } from '@/api/labsys/contestenroll'

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
        enrollNo: "0",
        contestNo: "",
        groupNo: "0",
        userNo: "0",
        userProfile: "0",
        enrollDate: "0",
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
      getContestenroll(this.id).then(response => {
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