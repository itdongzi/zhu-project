<template>
  <div class="wrapbox">
    <el-descriptions title="通知公告"  >
      <el-descriptions-item label="标题">{{model.title}}</el-descriptions-item>
      <el-descriptions-item label="发布作者">{{model.author}}</el-descriptions-item>
      <el-descriptions-item label="分类">{{model.classNo}}</el-descriptions-item>
      <el-descriptions-item label="发布时间">{{model.pubdate}}</el-descriptions-item>
      <el-descriptions-item label="备注">{{model.comments}}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="text-align:center;"><el-button size="small" @click="doClose">关闭</el-button></div>
  </div>
</template>

<script>
  import { getContentinfo } from '@/api/system/contentinfo'

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
        contzNo: "0",
        classNo: "",
        title: "",
        author: "",
        pubdate: "",
        poster: "0",
        abstracts: "",
        ncontents: "",
        hitCount: "",
        checkState:"",
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
      getContentinfo(this.id).then(response => {
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