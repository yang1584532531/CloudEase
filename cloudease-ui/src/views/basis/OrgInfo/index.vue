<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="父组织id" prop="communityOrgParentId">
        <el-input
          v-model="queryParams.communityOrgParentId"
          placeholder="请输入父组织id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="祖级列表" prop="communityAncestors">
        <el-input
          v-model="queryParams.communityAncestors"
          placeholder="请输入祖级列表"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="组织名称" prop="communityOrgName">
        <el-input
          v-model="queryParams.communityOrgName"
          placeholder="请输入组织名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="显示顺序" prop="orderNum">
        <el-input
          v-model="queryParams.orderNum"
          placeholder="请输入显示顺序"
          clearable
          @keyup.enter="handleQuery"
        />
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
          v-hasPermi="['basis:OrgInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
            <el-button
               type="info"
               plain
               icon="Sort"
               @click="toggleExpandAll"
            >展开/折叠</el-button>
         </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['basis:OrgInfo:edit']"
        >修改</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['basis:OrgInfo:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['basis:OrgInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable" 
      v-loading="loading" 
      :data="OrgInfoList" 
      row-key="communityOrgId"
      :default-expand-all="isExpandAll" 
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <!-- <el-table-column label="组织id" align="center" prop="communityOrgId" /> -->
      <!-- <el-table-column label="父组织id" align="center" prop="communityOrgParentId" /> -->
      <!-- <el-table-column label="祖级列表" align="center" prop="communityAncestors" /> -->
      <el-table-column label="组织名称" align="center" prop="communityOrgName" width="260"/>
      <el-table-column label="排序" align="center" prop="orderNum" width="200"/>
      <el-table-column prop="status" label="状态" width="100">
            <template #default="scope">
               <dict-tag :options="sys_normal_disable" :value="scope.row.status" />
            </template>
         </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['basis:OrgInfo:edit']">修改</el-button>
          <el-button link type="primary" icon="Plus" @click="handleAdd(scope.row)" v-hasPermi="['basis:OrgInfo:add']">新增</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['basis:OrgInfo:remove']">删除</el-button>
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

    <!-- 添加或修改小区组织结构对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="OrgInfoRef" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24" v-if="form.communityOrgParentId !== 0">
            <el-form-item label="上级部门" prop="communityOrgParentId">
                <el-tree-select
                  v-model="form.communityOrgParentId"
                  :data="oriOptions"
                  :props="{ value: 'communityOrgId', label: 'communityOrgName', children: 'children' }"
                  value-key="communityOrgId"
                  placeholder="选择上级部门"
                  check-strictly
                />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="组织名称" prop="communityOrgName">
              <el-input v-model="form.communityOrgName" placeholder="请输入组织名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="显示排序" prop="orderNum">
                <el-input-number v-model="form.orderNum" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="部门状态">
                <el-radio-group v-model="form.status">
                  <el-radio
                      v-for="dict in sys_normal_disable"
                      :key="dict.value"
                      :label="dict.value"
                  >{{ dict.label }}</el-radio>
                </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <!-- <el-form-item label="父组织id" prop="communityOrgParentId">
          <el-input v-model="form.communityOrgParentId" placeholder="请输入父组织id" />
        </el-form-item>
        <el-form-item label="祖级列表" prop="communityAncestors">
          <el-input v-model="form.communityAncestors" placeholder="请输入祖级列表" />
        </el-form-item> -->
        
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

<script setup name="OrgInfo">
import { listOrgInfo, getOrgInfo, delOrgInfo, addOrgInfo, updateOrgInfo } from "@/api/basis/OrgInfo";

const { proxy } = getCurrentInstance();
const { sys_normal_disable } = proxy.useDict("sys_normal_disable");

const OrgInfoList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const oriOptions = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const refreshTable = ref(true);
const isExpandAll = ref(true);

const data = reactive({
  form: {},
  queryParams: {
    communityOrgParentId: null,
    communityAncestors: null,
    communityOrgName: null,
    orderNum: null,
    status: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询小区组织结构列表 */
function getList() {
  loading.value = true;
  listOrgInfo(queryParams.value).then(response => {
    OrgInfoList.value = proxy.handleTree(response.data, "communityOrgId","communityOrgParentId");
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
    communityOrgId: null,
    communityOrgParentId: null,
    communityAncestors: null,
    communityOrgName: null,
    orderNum: null,
    status: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  };
  proxy.resetForm("OrgInfoRef");
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
  ids.value = selection.map(item => item.communityOrgId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd(row) {
  reset();
  listOrgInfo().then(response => {
    oriOptions.value = proxy.handleTree(response.data, "communityOrgId","communityOrgParentId");
  });
  if (row != undefined) {
    form.value.communityOrgParentId = row.communityOrgId;
  }
  open.value = true;
  title.value = "添加小区组织结构";
}

/** 展开/折叠操作 */
function toggleExpandAll() {
  refreshTable.value = false;
  isExpandAll.value = !isExpandAll.value;
  nextTick(() => {
    refreshTable.value = true;
  });
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  listOrgInfo().then(response => {
    oriOptions.value = proxy.handleTree(response.data, "communityOrgId","communityOrgParentId");
  });
  const _communityOrgId = row.communityOrgId || ids.value
  getOrgInfo(_communityOrgId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改小区组织结构";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["OrgInfoRef"].validate(valid => {
    if (valid) {
      if (form.value.communityOrgId != null) {
        updateOrgInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addOrgInfo(form.value).then(response => {
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
  const _communityOrgIds = row.communityOrgId || ids.value;
  proxy.$modal.confirm('是否确认删除小区组织结构编号为"' + _communityOrgIds + '"的数据项？').then(function() {
    return delOrgInfo(_communityOrgIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('basis/OrgInfo/export', {
    ...queryParams.value
  }, `OrgInfo_${new Date().getTime()}.xlsx`)
}

getList();
</script>
