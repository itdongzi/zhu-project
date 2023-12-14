<template>
  <div class="app-container">
    <el-form :model="queryParams" size="small" :inline="true">
      <el-form-item label="竞赛名称" prop="contestName">
        <el-input
          v-model="queryParams.contestName"
          placeholder="请输入竞赛名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类">
        <el-select v-model="queryParams.classNo" placeholder="请选择分类">
           <el-option
            v-for="item in deptList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          @click="handleDel"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          >导出</el-button
        >
      </el-col>
    </el-row>

    <vxe-table
      ref="myTable"
      border
      stripe
      resizable
      highlight-current-row
      highlight-hover-row
      :loading="loading"
      class="mytable-scrollbar"
      height="400"
      :data="dataSource"
    >
      <vxe-table-column type="checkbox" width="60"></vxe-table-column>
      <vxe-table-column type="seq" title="ID" width="60"></vxe-table-column>
      <vxe-table-column field="contestName" title="竞赛名称"></vxe-table-column>
      <vxe-table-column field="classNo" title="类型"></vxe-table-column>

      <vxe-table-column
        field="contestGuider"
        title="指导老师"
        show-overflow="tooltip"
      ></vxe-table-column>

      <vxe-table-column
        field="contestDate"
        title="比赛时间"
        show-overflow="tooltip"
      ></vxe-table-column>

      <vxe-table-column title="操作">
        <template v-slot="{ row }">
          <vxe-button status="primary"  @click="handleEdt(row.contestNo)"
            >编辑</vxe-button
          >
          <vxe-button  @click="handleDet(row.contestNo)"
            >详细</vxe-button
          >
        </template>
      </vxe-table-column>
    </vxe-table>
    <vxe-pager
      border
      size="medium"
      :loading="loading"
      :current-page="pageParam.pageIndex"
      :page-size="pageParam.pageSize"
      :total="pageParam.pageTotal"
      :layouts="[
        'PrevPage',
        'JumpNumber',
        'NextPage',
        'FullJump',
        'Sizes',
        'Total',
      ]"
      @page-change="onPageChange"
    >
    </vxe-pager>
  </div>
</template>

<script>
import {
  listContestinfo,
  delContestinfo,
  exptContestinfo,
} from "@/api/labsys/contestinfo";
import edit from "./Edit";
import detail from "./Detail";

export default {
  name: "List",
  data() {
    return {
      advanced: false,
      loading: false,
      dataSource: [],
      deptList: [
         { label: "H5开发", value: "1" },
        { label: "移动应用开发", value: "2" },
        { label: "软件测试", value: "3" },
      ],
      // 查询参数
      queryParams: {
        contestName: "",
        classNo: "",
      },
      // 查询参数
      pageParam: {
        pageIndex: 1, // 第几页
        pageSize: 10, // 每页中显示数据的条数
        pageTotal: 0,
        condition: "",
        dataParams: "",
      },
    };
  },
  mounted() {
    this.getDataSource();
  },
  methods: {
    handleQuery() {
      this.queryParams.pageIndex = 1;
      this.getDataSource();
    },
    resetQuery() {
      this.queryParams.contestName = "";
      this.queryParams.classNo = "";
      this.getDataSource();
    },
    handleAdd() {
      this.$layer.iframe({
        content: {
          content: edit,
          parent: this,
          data: { id: "" },
        },
        area: ["890px", "600px"],
        title: "新增",
        maxmin: false,
        shade: true,
        shadeClose: false,
      });
    },
    handleDel() {
      const that = this;
      let selectedRecords = this.$refs.myTable.getCheckboxRecords();
      if (selectedRecords.length > 0) {
        this.$layer.confirm("您确定要删除所选的记录吗？", (layerid) => {
          let selectedRowKeys = [];
          selectedRecords.map(function (item) {
            selectedRowKeys.push(item.contestNo);
          });
          delContestinfo(selectedRowKeys).then((response) => {
            that.getDataSource();
            that.$layer.close(layerid);
          });
        });
      } else {
        this.$message.warning("请至少选择一条记录!");
      }
    },
    handleEdt(val) {
      this.$layer.iframe({
        content: {
          content: edit,
          parent: this,
          data: { id: val },
        },
        area: ["890px", "600px"],
        title: "编辑",
        maxmin: false,
        shade: true,
        shadeClose: false,
      });
    },
    handleDet(val) {
      this.$layer.iframe({
        content: {
          content: detail,
          parent: this,
          data: { id: val },
        },
        area: ["890px", "600px"],
        title: "详细",
        maxmin: false,
        shade: true,
        shadeClose: false,
      });
    },
    handleExport() {
      const that = this;
      this.pageParam.dataParams = this.queryParams;
      exptContestinfo(this.pageParam).then((response) => {
        that.download(response.msg, true);
        that.$message.success("导出成功!");
      });
    },
    onPageChange({ currentPage, pageSize }) {
      this.pageParam.pageIndex = currentPage;
      this.pageParam.pageSize = pageSize;
      this.getDataSource();
    },
    // 调用查询接口为dataSource 赋值
    getDataSource() {
      const that = this;
      this.loading = true;
      this.pageParam.dataParams = this.queryParams;
      listContestinfo(this.pageParam).then((response) => {
        that.dataSource = response.rows;
        that.pageParam.pageTotal = response.total;
        that.loading = false;
      });
    },
  },
};
</script> 

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