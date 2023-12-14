<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="实训室名称" prop="roomName">
        <el-input v-model="form.roomName" placeholder="请输入实训室名称" />
      </el-form-item>
      <el-row>
        <el-col :span="12">
          <el-form-item label="实训室类型" prop="classNo">
            <el-select v-model="form.classNo" placeholder="请选择" style="width:100%">
              <el-option
                v-for="item in classList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属区域" prop="areaNo">
            <el-select v-model="form.areaNo" placeholder="请选择" style="width:100%">
              <el-option
                v-for="item in areaList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="管理员" prop="roomAdmin">
            <el-input v-model="form.roomAdmin" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="规模/大小" prop="roomScale">
            <el-input v-model="form.roomScale" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="实训室简介" prop="roomDesc">
        <el-input
          v-model="form.roomDesc"
          type="textarea"
        ></el-input>
      </el-form-item>
      <el-form-item label="预约状态" prop="orderState">
        <el-radio-group v-model="form.orderState">
          <el-radio
            v-for="dict in orderList"
            :key="dict.value"
            :label="dict.value"
          >{{dict.label}}
          </el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="可用状态" prop="checkState">
        <el-radio-group v-model="form.checkState">
          <el-radio
            v-for="dict in checkList"
            :key="dict.value"
            :label="dict.value"
          >{{dict.label}}
          </el-radio>
        </el-radio-group>
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
import { treeRoomclass } from '@/api/labsys/roomclass'

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
      classList: [],
      areaList: [],
      orderList:[{label:'可预约',value:'1'},{label:'不可预约',value:'0'}],
      checkList:[{label:'正常',value:'1'},{label:'停用',value:'0'}],
      form: {
        roomNo: "0",
        roomName: "",
        roomCode: "",
        roomType: "1",
        areaNo: "",
        classNo: "",
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
    getClassinfo() {
      treeRoomclass().then((res) => {
        this.classList = res.data;
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
    this.getClassinfo();
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
