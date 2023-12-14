<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="消息标题" prop="mtitle">
        <el-input v-model="form.mtitle" placeholder="" />
      </el-form-item>
          <el-form-item label="收件人" prop="mreceiver">
        <el-input v-model="form.mreceiver" placeholder="" />
      </el-form-item>     
      <el-form-item label="消息内容" prop="mcontent">
        <el-input
          v-model="form.mcontent"
          type="textarea"
          placeholder="请输入消息内容"
        ></el-input>
      </el-form-item>

      <!-- <el-form-item label="消息附件" prop="attachfile">
        <el-input v-model="form.attachfile" placeholder="" />
      </el-form-item> -->

      <!-- dong修改 2023.11.2 -->
      <el-form-item label="消息附件" prop="attachfile">
        <el-input :value="form.attachfile" readonly placeholder="选择文件" />
          <el-upload
            class="upload-demo"
            ref="upload"
            action="http://localhost:8098/web/upload"
            :on-success="handleUploadSuccess"
            :on-remove="handleRemove"
            :before-upload="beforeUpload"
            :limit="3" >
           <el-button size="small" type="primary">上传附件</el-button>
        </el-upload>
      </el-form-item>

      <el-form-item label="备注" prop="comments">
        <el-input
          v-model="form.comments"
          type="textarea"
          placeholder="请输入备注"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="doOk">确 定</el-button>
        <el-button size="small" @click="doCancel">取 消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import { getMessageinfo, addMessageinfo, uptMessageinfo } from '@/api/system/messageinfo'
  //import { treeAreainfo } from "@/api/labsys/areainfo";

  

export default {
  name: "Edit",
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

    // var time = new date();
    // time = time.toLocalstring;

    return {
      form: {
        mssNo: "0",
        mssgType: "",
        mreceiver: "",
        msender: "",
        mtitle: "",
        mcontent: "0",
        attachfile: "",
        readState: "",
        receiveTime: "",

        sendTime: '',              
        // sendTime:time,
        
        checkState: "1",
        comments: "",
      },
      rules: {
        mtitle: [{ required: true, message: "请输入名称", trigger: "blur" }],
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs['form'].validate((valid) => {
          if (valid) {
            if (that.form.mssNo === "0") {
            addMessageinfo(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptMessageinfo(that.form).then((response) => {
                that.$layer.msg(response.msg)
                that.$parent.getDataSource()
                that.$layer.close(that.layerid)
            });
          }
          } else {
            console.log('error submit!!');
            return false;
          }
        });

    },

    doCancel() {
      this.$layer.close(this.layerid)
    },
    

    handleUploadSuccess(getMessageinfo, file, fileList) {
      this.uploadedFiles.push(file.name);
      this.form.attachfile = this.uploadedFiles.join(', '); // 更新文本框中的值
    },
    handleRemove(file, fileList) {
      const index = this.uploadedFiles.indexOf(file.name);
      if (index !== -1) {
        this.uploadedFiles.splice(index, 1);
        this.form.attachfile = this.uploadedFiles.join(', '); // 更新文本框中的值
      }
    },
    beforeUpload(file) {
      // 文件类型和大小的验证逻辑
      const allowedTypes = [
      'application/msword',
      'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
      'application/vnd.ms-excel',
      'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
      'application/pdf',
      'application/vnd.ms-works', // WPS文档
      'application/vnd.ms-excel', // ET表格
      'application/vnd.ms-powerpoint', // WPP演示
      ];
      const maxSize = 10 * 1024 * 1024; // 10MB

      if (!allowedTypes.includes(file.type)) {
        this.$message.error('只允许上传Word、Excel或PDF文件');
        return false; // 阻止上传
      }

      if (file.size > maxSize) {
        this.$message.error('文件大小不能超过10MB');
        return false; // 阻止上传
      }

      return true; // 允许上传
    },

    handleUploadSuccess(response, file) {
      console.log('上传成功:', response);
    },
    handleUploadError(err, file) {
      console.error('上传失败:', err);
    },
    
    formatSendTime() {
  const timer = new Date();
  const formattedTime = timer.toLocaleString(); // 获取格式化的时间
  const dateParts = formattedTime.split(' ')[0].split('/'); // 拆分日期部分
  const timePart = formattedTime.split(' ')[1]; // 获取时间部分
  const year = dateParts[0];
  const month = dateParts[1].padStart(2, '0');
  const day = dateParts[2].padStart(2, '0');
  const formattedDateTime = `${year}-${month}-${day} ${timePart}`; // 重新构造日期时间格式
  this.form.sendTime = formattedDateTime; // 将格式化的时间赋值给form对象中的sendTime属性
},



  },
  mounted() {
    if (this.id !== "") {
      const that = this;
      getMessageinfo(this.id).then(response => {
        that.form = response.data;
      });
    }


    this.formatSendTime(); // 在组件挂载时调用formatSendTime方法


    /*
    getAreainfo() {
      treeAreainfo().then((res) => {
        this.areaList = res.data;
      });
    },
     */




    //this.getAreainfo();
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
