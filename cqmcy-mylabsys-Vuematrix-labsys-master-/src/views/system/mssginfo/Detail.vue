<template>
  <div class="wrapbox">
    <el-descriptions title="消息信息">
      <el-descriptions-item label="消息标题">{{model.mtitle}}</el-descriptions-item>
      <el-descriptions-item label="消息类别">{{model.mssgType}}</el-descriptions-item>
      <el-descriptions-item label="发件人">{{model.msender}}</el-descriptions-item>
      <el-descriptions-item label="发件时间">{{model.sendTime}}</el-descriptions-item>
      <el-descriptions-item label="阅读状态">{{model.readState}}</el-descriptions-item>
      <el-descriptions-item label="消息内容">{{model.mcontent}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getMessageinfo } from '@/api/system/messageinfo'

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
        mssNo: "0",
        mssgType: "",
        mreceiver: "",
        msender: "",
        mtitle: "",
        mcontent: "0",
        attachfile: "",
        readState: "",
        receiveTime: "",
        sendTime: "1",
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
      getMessageinfo(this.id).then(response => {
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