<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="小区编码" prop="communityCode">
        <el-input
          v-model="queryParams.communityCode"
          placeholder="请输入小区编码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="小区名称" prop="communityName">
        <el-input
          v-model="queryParams.communityName"
          placeholder="请输入小区名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="小区经纬度" prop="communityCoordinate">
        <el-input
          v-model="queryParams.communityCoordinate"
          placeholder="请输入小区经纬度"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="小区地址" prop="communityAddress">
        <el-input
          v-model="queryParams.communityAddress"
          placeholder="请输入小区地址"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="小区联系人" prop="communityContacts">
        <el-input
          v-model="queryParams.communityContacts"
          placeholder="请输入小区联系人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="小区联系方式" prop="communityPhone">
        <el-input
          v-model="queryParams.communityPhone"
          placeholder="请输入小区联系方式"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="状态" prop="communityStatus">
        <el-select v-model="queryParams.communityStatus" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in cloudease_community_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['basis:communityInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['basis:communityInfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['basis:communityInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['basis:communityInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="communityInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <!-- <el-table-column label="小区编码" align="center" prop="communityCode" /> -->
      <el-table-column label="小区名称" align="center" prop="communityName" />
      <!-- <el-table-column label="小区经纬度" align="center" prop="communityCoordinate" /> -->
      <el-table-column label="小区地址" align="center" prop="communityAddress" />
      <el-table-column label="小区负责人" align="center" prop="communityContacts" />
      <el-table-column label="联系方式" align="center" prop="communityPhone" />
      <el-table-column label="状态" align="center" prop="communityStatus">
        <template #default="scope">
          <dict-tag :options="cloudease_community_type" :value="scope.row.communityStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['basis:communityInfo:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['basis:communityInfo:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改小区信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="communityInfoRef" :model="form" :rules="rules" label-width="100px">
        <!-- <el-form-item label="小区编码" prop="communityCode">
          <el-input v-model="form.communityCode" placeholder="请输入小区编码" />
        </el-form-item> -->
        <el-form-item label="小区名称" prop="communityName">
          <el-input v-model="form.communityName" placeholder="请输入小区名称" />
        </el-form-item>
        <!-- <el-form-item label="小区经纬度" prop="communityCoordinate">
          <el-input v-model="form.communityCoordinate" placeholder="请输入小区经纬度" />
        </el-form-item> -->
        <!-- <el-form-item label="小区地址" prop="communityAddress">
          <el-input v-model="form.communityAddress" placeholder="请输入小区地址" />
        </el-form-item> -->
        <el-form-item label="小区联系人" prop="communityContacts">
          <el-input v-model="form.communityContacts" placeholder="请输入小区联系人" />
        </el-form-item>
        <el-form-item label="小区联系方式" prop="communityPhone">
          <el-input v-model="form.communityPhone" placeholder="请输入小区联系方式" />
        </el-form-item>
        <el-form-item label="状态" prop="communityStatus">
          <el-radio-group v-model="form.communityStatus">
            <el-radio
              v-for="dict in cloudease_community_type"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="CommunityInfo">
import { listCommunityInfo, getCommunityInfo, delCommunityInfo, addCommunityInfo, updateCommunityInfo } from "@/api/basis/communityInfo";

const { proxy } = getCurrentInstance();
const { cloudease_community_type } = proxy.useDict('cloudease_community_type');

const communityInfoList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    communityCode: null,
    communityName: null,
    communityCoordinate: null,
    communityAddress: null,
    communityContacts: null,
    communityPhone: null,
    communityStatus: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询小区信息列表 */
function getList() {
  loading.value = true;
  listCommunityInfo(queryParams.value).then(response => {
    communityInfoList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    communityCode: null,
    communityName: null,
    communityCoordinate: null,
    communityAddress: null,
    communityContacts: null,
    communityPhone: null,
    communityStatus: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  };
  proxy.resetForm("communityInfoRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加小区信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getCommunityInfo(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改小区信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["communityInfoRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateCommunityInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCommunityInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除小区信息编号为"' + _ids + '"的数据项？').then(function() {
    return delCommunityInfo(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('basis/communityInfo/export', {
    ...queryParams.value
  }, `communityInfo_${new Date().getTime()}.xlsx`)
}

getList();
</script>
