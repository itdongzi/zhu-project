<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="实训室名称" prop="roomName">
        <el-input v-model="form.roomName" placeholder="请输入实训室名称" disabled/>
      </el-form-item>
      <el-row>
        <el-col :span="12">
          <el-form-item label="学年学期" prop="xnxqbh">
            <el-input v-model="xnxqbh" placeholder="请输入学年学期" disabled/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="周次" prop="kkzc">
            <el-input v-model="form.kkzc" placeholder="请输入学年学期" disabled/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="星期" prop="week">
            <el-input v-model="week" placeholder="请输入学年学期" disabled/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="节次" prop="jc">
            <el-input v-model="form.jc" placeholder="请输入学年学期" disabled/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="预约教师" prop="jsxm">
            <el-input v-model="form.jsxm" placeholder="请输入预约教师姓名"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="预约时间" prop="orderDtime">
            <el-date-picker
              v-model="form.orderDtime"
              type="datetime"
              format="yyyy-MM-dd HH:mm:ss"
              value-format="yyyy-MM-dd HH:mm:ss"
              clearable
              placeholder="开始时间"
              style="width:100%"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="课程信息" prop="orderKcmc">
            <el-input v-model="form.orderKcmc" placeholder="请输入课程信息"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="班级信息" prop="orderBjmc">
            <el-input v-model="form.orderBjmc" placeholder="请输入班级信息"/>
          </el-form-item>
        </el-col>
      </el-row>
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
import { getRoomorders, addRoomorders, uptRoomorders } from '@/api/labsys/roomorders'
import { getUserProfile } from '@/api/system/ruserinfo'

export default {
  name: 'Edit',
  props: {
    layerid: {
      // 自动注入的layerid
      type: String,
      default: ''
    },
    week: {
      // 传递的数据
      type: String,
      default: ''
    },
    jc: {
      // 传递的数据
      type: String,
      default: ''
    },
    roomNo: {
      // 传递的数据
      type: String,
      default: ''
    },
    roomName: {
      // 传递的数据
      type: String,
      default: ''
    },
    semeNo: {
      // 传递的数据
      type: String,
      default: ''
    },
    xnxqbh: {
      // 传递的数据
      type: String,
      default: ''
    },
    kkzc: {
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
      user: {},
      form: {
        roomNo: this.roomNo,
        roomName: this.roomName,
        semeNo: this.semeNo,
        xnxqbh: this.xnxqbh,
        kkzc: this.kkzc,
        week: this.week,
        jc: this.jc,
        jsxm: '',
        orderDtime: '',
        orderKcmc: '',
        orderBjmc: '',
        comments: ''
      },
      rules: {
        jsxm: [{ required: true, message: '请输入预约教师', trigger: 'blur' }],
        orderDtime: [{ required: true, message: '请输入预约时间', trigger: 'blur' }],
        orderKcmc: [{ required: true, message: '请输入课程信息', trigger: 'blur' }],
        orderBjmc: [{ required: true, message: '请输入班级信息', trigger: 'blur' }],
      }
    }
  },
  created() {

  },
  methods: {
    doOk() {
      const that = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          addRoomorders(that.form).then((response) => {
            that.$layer.msg(response.msg)
            that.$parent.getDataSource()
            that.$layer.close(that.layerid)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    doCancel() {

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
