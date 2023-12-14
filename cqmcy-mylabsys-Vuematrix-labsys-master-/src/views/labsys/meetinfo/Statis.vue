<template>
  <div class="app-container">
    <el-form :model="queryParams" size="small" :inline="true">
      <el-form-item label="实训室名称" prop="roomName">
        <el-input
          v-model="queryParams.roomName"
          placeholder="请输入实训室名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属区域" prop="areaNo">
        <el-select
          v-model="queryParams.areaNo"
          placeholder="请选择区域"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="areaData in areaListData"
            :key="areaData.value"
            :label="areaData.label"
            :value="areaData.value"
          />
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
    <el-divider></el-divider>
    <p>图表</p>
  </div>
</template>

<script>

export default {
  name: "List",
  data() {
    return {
      // 查询参数
      queryParams: {
        roomName: ""
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
  methods: {
    handleQuery() {
      this.queryParams.pageIndex = 1;
      this.getDataSource();
    },
    resetQuery() {
      this.queryParams.roomName = "";
      this.queryParams.areaNo = "";
    }, 
  },
};
</script>

<style scoped lang="scss">
 
</style>
