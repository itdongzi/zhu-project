<template>
  <div class="app-container">

    <vxe-button type="button" status="primary" @click="handleSync">同步教务系统</vxe-button>
    <vxe-button type="button" @click="handleImport">导入课表</vxe-button>

    <el-divider></el-divider>

    <el-form :model="queryParams" size="small" :inline="true">
      <el-form-item label="所属区域" prop="areaNo">
        <el-select
          v-model="queryParams.areaNo"
          placeholder="请选择区域"
          clearable
          style="width: 240px"
          @change="changeArea"
        >
          <el-option
            v-for="areaData in areaListData"
            :key="areaData.value"
            :label="areaData.label"
            :value="areaData.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="实训室名称" prop="roomNo">
        <el-select
          v-model="queryParams.roomNo"
          placeholder="请选择实训室名称"
          clearable
          style="width: 240px"
          @change="changeRoom"
        >
          <el-option
            v-for="areaData in roomListData"
            :key="areaData.roomNo"
            :label="areaData.roomName"
            :value="areaData.roomNo"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="学年学期" prop="semeNo">
        <el-select
          v-model="queryParams.semeNo"
          placeholder="请选择学年学期"
          style="width: 240px"
          @change="changeXnxqbh"
        >
          <el-option
            v-for="item in xnxqbhListData"
            :key="item.semeNo"
            :label="item.xnxqbh"
            :value="item.semeNo"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="周次" prop="kkzc">
        <el-select
          v-model="queryParams.kkzc"
          placeholder="请选择周次"
          style="width: 240px"
          @change="changeKkzc"
        >
          <el-option
            v-for="item in kkzcListData"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>
      </el-form-item>
    </el-form>

    <vxe-table
      ref="myTable"
      border
      stripe
      resizable
      :loading="loading"
      class="mytable-scrollbar"
      height="650"
      :data="dataSource"
      :header-cell-class-name="headerCellClassName"
      :cell-class-name="cellClassName"
    >
      <vxe-table-column field="jxjcbh"  title="课表" align="center" >
        <template v-slot="{ row }">
          {{ getValue(row.jxjcbh) }}
        </template>
      </vxe-table-column>
      <vxe-table-column title="周一" align="center">
        <template v-slot="{ row }">
          {{ getValue(row.Monday) }}
        </template>
      </vxe-table-column>
      <vxe-table-column title="周二" align="center">
        <template v-slot="{ row }">
          {{ getValue(row.Tuesday) }}
        </template>
      </vxe-table-column>
      <vxe-table-column title="周三" align="center">
        <template v-slot="{ row }">
          {{ getValue(row.Wednesday) }}
        </template>
      </vxe-table-column>
      <vxe-table-column title="周四" align="center">
        <template v-slot="{ row }">
          {{ getValue(row.Thursday) }}
        </template>
      </vxe-table-column>
      <vxe-table-column title="周五" align="center">
        <template v-slot="{ row }">
          {{ getValue(row.Friday) }}
        </template>
      </vxe-table-column>
      <vxe-table-column title="周六" align="center">
        <template v-slot="{ row }">
          {{ getValue(row.Saturday) }}
        </template>
      </vxe-table-column>
      <vxe-table-column title="周日" align="center">
        <template v-slot="{ row }">
          {{ getValue(row.Sunday) }}
        </template>
      </vxe-table-column>
    </vxe-table>
  </div>
</template>

<script>
import {
  listRoomschedule,
  delRoomschedule,
  getXnxqbhList,
  getKkzcList
} from '@/api/labsys/roomschedule'
  import syncData from './SyncData'
  import imptData from './ImptData.vue'
  import { treeAreainfo } from '@/api/labsys/areainfo'
  import { queryRoominfo } from '@/api/labsys/roominfo'
export default {
  name: "List",
  data() {
    return {
      loading: false,
      dataSource: [],
      // 查询参数
      queryParams: {
        roomNo: "",
        areaNo: "",
        semeNo: "",
        kkzc: "",
      },
      // 查询参数
      pageParam: {
        pageIndex: 1, // 第几页
        pageSize: 10, // 每页中显示数据的条数
        pageTotal: 0,
        condition: "",
        dataParams: "",
      },
      areaListData: [],// 区域列表数据
      roomListData: [],// 实训室列表数据
      xnxqbhListData: [],// 学期学年列表数据
      kkzcListData: [],// 周次列表数据
    };
  },
  mounted() {
    this.getAreaListData();
  },
  methods: {
    getValue(str){
      if ( str && str.desc) {
        return str.desc
      }
      return ""
    },
    headerCellClassName ({ column, columnIndex }) {
      return 'col-blue'
    },
    cellClassName ({ row, rowIndex, column, columnIndex }) {
      if (column.title === '课表') {
        return 'col-red'
      }
    },
    changeArea(val) {
      this.getRoomListData(val)
      this.handleQuery();
    },
    changeRoom() {
      this.handleQuery();
    },
    changeXnxqbh() {
      this.handleQuery();
    },
    changeKkzc() {
      this.handleQuery();
    },
    handleQuery() {
      this.getDataSource();
    },
    handleSync() {
      this.$layer.iframe({
        content: {
          content: syncData,
          parent: this,
        },
        area: ["500px", "400px"],
        title: "同步教务数据",
        maxmin: false,
        shade: true,
        shadeClose: false});
    },
    handleImport() {
      this.$layer.iframe({
        content: {
          content: imptData,
          parent: this,
        },
        area: ["500px", "350px"],
        title: "导入数据",
        maxmin: false,
        shade: true,
        shadeClose: false});
    },
    resetQuery() {
      this.queryParams.roomName = "";
    },
    handleAdd() {
      this.$layer.iframe({
        content: {
          content: edit,
          parent: this,
          data: { id: "" }},
        area: ["890px", "600px"],
        title: "新增",
        maxmin: false,
        shade: true,
        shadeClose: false});
    },
    handleDel() {
      const that = this;
      let selectedRecords = this.$refs.myTable.getCheckboxRecords();
      if (selectedRecords.length > 0) {
        this.$layer.confirm("您确定要删除所选的记录吗？",(layerid)=> {
          let selectedRowKeys = [];
          selectedRecords.map(function (item) {
            selectedRowKeys.push(item.roomNo);
          });
          delRoomschedule(selectedRowKeys).then((response) => {
            that.getDataSource();
            that.$layer.close(layerid);
          }); 
        });
      }
    },
    // 调用查询接口为dataSource 赋值
    getDataSource() {
      console.log(this.queryParams)
      if ( this.queryParams.roomNo && this.queryParams.roomNo !== '' && this.queryParams.kkzc && this.queryParams.kkzc !== '' && this.queryParams.semeNo && this.queryParams.semeNo !== '') {
        const that = this;
        this.loading = true;
        this.pageParam.dataParams = this.queryParams;
        listRoomschedule(this.pageParam).then((response) => {
          that.dataSource = response.data;
          that.loading = false; 
        });
      }

    },
    // 调用查询接口为areaListData 赋值
    getAreaListData() {
      treeAreainfo().then((res) => {
        this.areaListData = res.data;
        this.queryParams.areaNo = this.areaListData[0].value
        this.getRoomListData(this.areaListData[0].value)
      });
    },
      getRoomListData(areaId) {
      var params = {};
      params.dataParams = { areaNo: areaId };
      queryRoominfo(params).then((response) => {
        this.roomListData = response.data;
        this.queryParams.roomNo = this.roomListData[0].roomNo
        this.getXnxqbhListData()
      });
    },
    getXnxqbhListData() {
      getXnxqbhList().then((res) => {
        this.xnxqbhListData = res.data;
        this.queryParams.semeNo = this.xnxqbhListData[0].semeNo
        this.getKkzcListData(this.xnxqbhListData[0].semeNo)
      });
    },
    getKkzcListData(semeNo) {
      var params = {};
      params.dataParams = { semeNo: semeNo };
      getKkzcList(params).then((res) => {
        this.kkzcListData = res.data;
        this.queryParams.kkzc = this.kkzcListData[0]
        this.getDataSource()
      });
    },
  },
};
</script>

<style scoped>
.mytable-scrollbar /deep/ .vxe-body--row.row-green {
  background-color: #187;
  color: #fff;
}
.mytable-scrollbar /deep/ .vxe-header--column.col-blue {
  background-color: rgba(232,245,255);
  color: #000000;
}
.mytable-scrollbar /deep/ .vxe-body--column.col-red {
  background-color: rgba(232,245,255);
  color: #000000;
}

/*vxe-table 自定义行高 */
.vxe-table /deep/ .vxe-body--column.col--ellipsis {
  height: 100px;
}
.vxe-table /deep/ .vxe-body--column:not(.col--ellipsis), .vxe-table .vxe-footer--column:not(.col--ellipsis), .vxe-table .vxe-header--column:not(.col--ellipsis) {
  padding: 5px 0;
  height: 100px;
}

</style>
<style scoped lang="scss">

/*滚动条整体部分*/
.mytable-scrollbar div::-webkit-scrollbar {
  width: 10px;
  height: 10px;
}
/*滚动条的轨道*/
.mytable-scrollbar div::-webkit-scrollbar-track {
  background-color: #ffffff;
}
/*滚动条里面的小方块，能向上向下移动*/
.mytable-scrollbar div::-webkit-scrollbar-thumb {
  background-color: #bfbfbf;
  border-radius: 5px;
  border: 1px solid #f1f1f1;
  box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
}
.mytable-scrollbar div::-webkit-scrollbar-thumb:hover {
  background-color: #a8a8a8;
}
.mytable-scrollbar div::-webkit-scrollbar-thumb:active {
  background-color: #787878;
}
/*边角，即两个滚动条的交汇处*/
.mytable-scrollbar div::-webkit-scrollbar-corner {
  background-color: #ffffff;
}


</style>
