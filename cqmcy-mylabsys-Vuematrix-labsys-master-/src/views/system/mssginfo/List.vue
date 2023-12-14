<template>
  <div class="app-container">
    <el-form :model="queryParams" size="small" :inline="true">
      <el-form-item label="消息标题" prop="mtitle">
        <el-input
          v-model="queryParams.mtitle"
          placeholder="请输入内容"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >查询</el-button
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
          >发消息</el-button
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
      <vxe-table-column field="mtitle" title="消息标题"></vxe-table-column>
      <vxe-table-column field="msender" title="发送人"></vxe-table-column>
      <vxe-table-column field="mssgType" title="类型" :formatter="formatmssgType"></vxe-table-column>
      <vxe-table-column field="readState" title="阅读状态" :formatter="formatreadState"></vxe-table-column>    
      <vxe-table-column title="操作">
        <template v-slot="{ row }">
          <!-- <vxe-button type="text" @click="handleDet(row.mssNo)"
            >阅读</vxe-button
          > -->
          <vxe-button status="text" @click="handleDet(row.mssNo)"
            >查看详情</vxe-button
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
  import { listMessageinfo, delMessageinfo, exptMessageinfo } from '@/api/system/messageinfo'
  import edit from './Edit'
  import detail from './Detail'

export default {
  name: "List",
  data() {
    return {
      advanced: false,
      loading: false,
      dataSource: [],
      // 查询参数
      queryParams: {
        mtitle: "",
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
      this.queryParams.mtitle = "";
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
        console.log(Date.now());
    },
    handleDel() {
      const that = this;
      let selectedRecords = this.$refs.myTable.getCheckboxRecords();
      if (selectedRecords.length > 0) {
        this.$layer.confirm("您确定要删除所选的记录吗？",(layerid)=> {
          let selectedRowKeys = [];
          selectedRecords.map(function (item) {
            selectedRowKeys.push(item.mssNo);
          });
          delMessageinfo(selectedRowKeys).then((response) => {
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
          data: { id: val }},
        area: ["890px", "600px"],
        title: "编辑",
        maxmin: false,
        shade: true,
        shadeClose: false});
    },
    handleDet(val) {
      this.$layer.iframe({
        content: {
          content: detail,
          parent: this,
          data: { id: val}},
        area: ["890px", "600px"],
        title: "详细",
        maxmin: false,
        shade: true,
        shadeClose: false});
    },
    handleImport() {
      console.log("import");
    },
    handleExport() {
      const that = this;
      this.pageParam.dataParams = this.queryParams;
      exptMessageinfo(this.pageParam).then((response) => {
        that.download(response.msg,true);
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
      listMessageinfo(this.pageParam).then((response) => {
        that.dataSource = response.data.list;
        that.pageParam.pageTotal = response.data.total;
        that.loading = false;
      });
    },
    formatreadState({ row }) {
        if (row.readState === '0') {
          return '未阅读';
        } else if (row.readState === '1') {
          return '已阅读';
        } else {
          return '获取阅读状态错误';
        }   
      },

      formatmssgType({ row }) {
        if (row.mssgType === '0') {
          return '未知类型';
        } else if (row.mssgType === '1') {
          return '个人私信';
        } else {
          return '获取类型状态错误';
        }   
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