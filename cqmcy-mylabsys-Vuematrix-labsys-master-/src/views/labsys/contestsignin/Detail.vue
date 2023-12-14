<template>
  <div class="wrapbox">
    <el-descriptions title="签到打卡信息">
      <el-descriptions-item label="竞赛名称名称">{{model.contestNo}}</el-descriptions-item>
      <el-descriptions-item label="打卡姓名">{{model.userNo}}</el-descriptions-item>
      <el-descriptions-item label="打卡类别">{{model.signType}}</el-descriptions-item>
      <el-descriptions-item label="打卡时间">{{model.signTime}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getContestsignin } from '@/api/labsys/contestsignin'

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
        signNo: "0",
        contestNo: "",
        userNo: "0",
        signType: "0",
        signTime: "0",
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
      getContestsignin(this.id).then(response => {
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