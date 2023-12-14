<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="实训室名称" prop="roomName">
        <el-input v-model="form.roomName" placeholder="请输入实训室名称" />
      </el-form-item>
      <el-form-item label="实训室类型" prop="roomType">
        <el-radio-group v-model="form.roomType">
          <el-radio label="1">公共实训室</el-radio>
          <el-radio label="2">专业实训室</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="所属区域" prop="areaNo">
        <el-select v-model="form.areaNo" placeholder="请选择">
          <el-option
            v-for="item in areaList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="预约状态" prop="orderState">
        <el-select v-model="form.orderState" placeholder="请选择">
          <el-option
            v-for="item in orderList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
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
import { getRoominfo, addRoominfo, uptRoominfo } from "@/api/labsys/roominfo";
import { treeAreainfo } from "@/api/labsys/areainfo";

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
    return {
      areaList: [],
      orderList:[{label:'可预约',value:'1'},{label:'不可预约',value:'0'}],
      form: {
        roomNo: "0",
        roomName: "",
        roomCode: "",
        roomType: "1",
        areaNo: "",
        classNo: "0",
        roomAdmin: "",
        roomScale: "",
        roomDesc: "",
        orderState: "1",
        checkState: "1",
        comments: "",
      },
      rules: {
        roomName: [{ required: true, message: "请输入名称", trigger: "blur" }],
        //areaNo: [{ required: true, message: "请选择", trigger: "blur" }],
      },
    };
  },
  methods: {
    doOk() {
      const that = this;
      this.$refs['form'].validate((valid) => {
          if (valid) {
            if (that.form.roomNo === "0") {
            addRoominfo(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          } else {
            uptRoominfo(that.form).then((response) => {
              that.$layer.msg(response.msg);
              that.$parent.getDataSource();
              that.$layer.close(that.layerid);
            });
          }
          } else {
            console.log('error submit!!');
            return false;
          }
        });
    },
    doCancel() {
      this.$layer.close(this.layerid);
    },
    getAreainfo() {
      treeAreainfo().then((res) => {
        this.areaList = res.data;
      });
    },
  },
  mounted() {
    if (this.id !== "") {
      const that = this;
      getRoominfo(this.id).then((response) => {
        that.form = response.data;
      });
    }
    this.getAreainfo();
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
