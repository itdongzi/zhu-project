<template>
  <div class="wrapbox">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="数据源" prop="dataSource">
        <el-select v-model="form.dataSource" placeholder="请选择">
          <el-option
            v-for="item in dataSourceList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学年学期" prop="xnxqbh">
        <el-select v-model="form.xnxqbh" placeholder="请选择">
          <el-option
            v-for="item in xnxqbhListData"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="实训室选择" prop="roomNo">
        <el-select v-model="form.roomNo" placeholder="请选择">
          <el-option
            v-for="item in roomDataList"
            :key="item.roomNo"
            :label="item.roomName"
            :value="item.roomNo"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="同步方式" prop="syncType">
        <el-radio-group v-model="form.syncType">
          <el-radio label="1">覆盖同步</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="doOk">立即同步数据</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getRoomschedule, addRoomschedule, uptRoomschedule, getXnxqbhList } from '@/api/labsys/roomschedule'
import { queryRoominfo } from '@/api/labsys/roominfo'

export default {
  name: 'Sync',
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
      dataSourceList: [],
      xnxqbhListData: [],
      roomDataList: [],
      form: {
        dataSource: '',
        xnxqbh: '',
        roomNo: '',
        syncType: '1'
      },
      rules: {
        dataSource: [{ required: true, message: '请选择数据源', trigger: 'blur' }],
        xnxqbh: [{ required: true, message: '请选择学年学期', trigger: 'blur' }],
        roomNo: [{ required: true, message: '请选择实训室', trigger: 'blur' }]
      }
    }
  },
  mounted() {
    // this.getDataSourceData();
    this.getXnxqbhDataList();
    this.getRoomDataList();
  },
  methods: {
    getXnxqbhDataList() {
      getXnxqbhList().then((res) => {
        this.xnxqbhListData = res.data;
        console.log(this.xnxqbhListData)
      });
    },
    getRoomDataList() {
      queryRoominfo({}).then((res) => {
        this.roomDataList = res.data;
      });
    },

    doOk() {

    },
    doCancel() {
      this.$layer.close(this.layerid)
    }
  },

}
</script>

<style scoped lang="scss">
.wrapbox {
  margin: 0;
  padding: 30px;
  width: 100%;
}
</style>
