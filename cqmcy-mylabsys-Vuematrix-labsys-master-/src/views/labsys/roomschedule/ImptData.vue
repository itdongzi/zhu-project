<template>
  <div class="wrapbox">
    <el-form ref="form" label-width="120px">
      <el-form-item label="模板文件" prop="">
        <el-link :href="downloadFileUrl" type="primary" target="_blank">
          <el-button type="primary" size="small">下载模板</el-button>
        </el-link>
      </el-form-item>
      <el-form-item label="上传文件" prop="file">
        <el-upload
          ref="fileUpload"
          :limit="1"
          accept=".xlsx, .xls"
          :headers="headers"
          :action="uploadFileUrl"
          :before-upload="handleBeforeUpload"
          :file-list="fileList"
          :on-error="handleUploadError"
          :on-exceed="handleExceed"
          :on-success="handleUploadSuccess"
          :auto-upload="false"
          class="upload-file-uploader"
        >
          <!-- 上传按钮 -->
          <el-button size="mini" type="primary">选取文件</el-button>
          <!-- 上传提示 -->
          <div class="el-upload__tip" slot="tip" v-if="showTip">
            请上传
            <template v-if="fileSize"> 大小不超过 <b style="color: #f56c6c">{{ fileSize }}MB</b> </template>
            <template v-if="fileType"> 格式为 <b style="color: #f56c6c">{{ fileType.join("/") }}</b> </template>
            的文件
          </div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitUpload">确 定</el-button>
        <el-button @click="doCancel">取 消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getToken } from '@/utils/auth'

export default {
  name: 'ImportData',
  props: {
    layerid: {
      // 自动注入的layerid
      type: String,
      default: ''
    },
    id: {
      // 传递的数据
      type: String,
      default: ''
    },
    lydata: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  data() {
    return {
      downloadFileUrl: "https://zwdpic.oss-cn-beijing.aliyuncs.com/cqmcy/%E5%AF%BC%E5%85%A5%E8%AF%BE%E8%A1%A8%E6%A8%A1%E6%9D%BF.xlsx",
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/labsys/roomschedule/upload", // 上传文件服务器地址
      fileType: ['xls', 'xlsx'],
      fileList: [],
      limit: 1,
      isShowTip: true,
      fileSize: 5,
    }
  },
  computed: {
    // 是否显示提示
    showTip() {
      return this.isShowTip && (this.fileType || this.fileSize);
    },
  },
  methods: {
    // 上传前校检格式和大小
    handleBeforeUpload(file) {
      // 校检文件类型
      if (this.fileType) {
        const fileName = file.name.split('.');
        const fileExt = fileName[fileName.length - 1];
        const isTypeOk = this.fileType.indexOf(fileExt) >= 0;
        if (!isTypeOk) {
          this.$modal.msgError(`文件格式不正确, 请上传${this.fileType.join("/")}格式文件!`);
          return false;
        }
      }
      // 校检文件大小
      if (this.fileSize) {
        const isLt = file.size / 1024 / 1024 < this.fileSize;
        if (!isLt) {
          this.$modal.msgError(`上传文件大小不能超过 ${this.fileSize} MB!`);
          return false;
        }
      }
      this.$modal.loading("正在上传文件，请稍候...");
      this.number++;
      return true;
    },
    // 上传失败
    handleUploadError(err) {
      this.$modal.msgError("上传文件失败，请重试");
      this.$modal.closeLoading()
    },
    // 文件个数超出
    handleExceed() {
      this.$modal.msgError(`上传文件数量不能超过 ${this.limit} 个!`);
    },
    // 上传成功回调
    handleUploadSuccess(res, file) {
      if (res.code === 200) {
        this.$modal.msgSuccess(res.msg);
        this.$modal.closeLoading();
        this.$layer.close(this.layerid)
      } else {
        this.$modal.closeLoading();
        this.$modal.msgError(res.msg);
        this.$refs.fileUpload.handleRemove(file);
      }
    },



























    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      //this.getList();
    },
    submitUpload() {
      this.$refs.fileUpload.submit()
    },
    doCancel() {
      this.$layer.close(this.layerid)
    }
  },
  mounted() {
  }
}
</script>

<style scoped lang="scss">
.wrapbox {
  margin: 0;
  padding: 30px;
  width: 100%;
}
</style>
