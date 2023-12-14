<template>
  <div class="app-container">
    <el-form :model="queryParams" size="small" :inline="true">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入内容"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类">
        <el-select v-model="queryParams.classNo" placeholder="请选择分类">
          <el-option label="通知公告" value="1"></el-option>
          <el-option label="规章制度" value="2"></el-option>
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
      <vxe-table-column field="id" title="ID" width="60"></vxe-table-column>
      <vxe-table-column field="title" title="标题"></vxe-table-column>
      <vxe-table-column field="author" title="发布作者"></vxe-table-column>
      <vxe-table-column field="classNo" title="分类" :formatter="formatclassNo"></vxe-table-column>
      <vxe-table-column field="pubdate" title="发布时间"></vxe-table-column>
      <vxe-table-column title="操作">
        <template v-slot="{ row }">
          <vxe-button status="text" @click="handleEdt(row.contzNo)"
            >编辑</vxe-button
          >
          <vxe-button status="text" @click="handleDet(row.contzNo)"
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
  listContentinfo,
  delContentinfo,
  exptContentinfo,
} from "@/api/system/contentinfo";
import edit from "./Edit";
import detail from "./Detail";

export default {
  name: "List",
  data() {
    return {
      advanced: false,
      loading: false,
      dataSource: [],
      // 查询参数
      queryParams: {
        title: "",
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
      this.queryParams.title = "";
      this.queryParams.classNo = "";
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
            selectedRowKeys.push(item.contzNo);
          });
          delContentinfo(selectedRowKeys).then((response) => {
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
    handleImport() {
      console.log("import");
    },
    handleExport() {
      const that = this;
      this.pageParam.dataParams = this.queryParams;
      exptContentinfo(this.pageParam).then((response) => {
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
      listContentinfo(this.pageParam).then((response) => {
        that.dataSource = response.data.list;
        that.pageParam.pageTotal = response.data.total;
        that.loading = false;
      });
    },

    formatclassNo({ row }) {
        if (row.classNo === '6000000161132102') {
          return '通知公告';
        } else if (row.classNo === '6000001657568767') {
          return '图片';
        } else if(row.classNo === '6000000885788162'){
          return '软件';
        } else if(row.classNo === '1'){
          return '通知公告';
        } else if(row.classNo === '2'){
          return '规章制度';
        }else {
          return '其他类型';
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