<script setup>
import { ref, onMounted } from 'vue';
import { queryAllApi, addApi, queryByIdApi, updateApi, deleteApi } from '@/api/dept';
import { ElMessage, ElMessageBox } from 'element-plus';

const search = async () => {
  const result = await queryAllApi();
  if (result.code) { // js中，0和''都为false，其他是true
    depList.value = result.data;
  }
}
const depList = ref([]);

onMounted(() => {
  search();
})

//dialog对话框组件
const dialogFormVisible = ref(false);
const formTitle = ref('');
const dept = ref({
  name: ''
});

const addDept = () => {
  dialogFormVisible.value = true;
  formTitle.value = '新增部门';
  dept.value = {
    name: ''
  }
  // 清空表单校验
  if (deptFormRef.value) {
    deptFormRef.value.resetFields();
  }
}

const save = async () => {
  // 表单校验
  if (!deptFormRef.value) return;
  deptFormRef.value.validate(async  (valid) => {
    if (valid) {
      let result;
      if (dept.value.id) {
        result = await updateApi(dept.value); 
      } else {
        result = await addApi(dept.value); 
      }
      if (result.code) {
        ElMessage.success('新增成功');
        dialogFormVisible.value = false;
        search();
      } else {
        ElMessage.error(result.msg);
      }
    } else {
      ElMessage.error('表单校验失败 ');
    }
  })
}

// 表单校验
const rules = ref({
  name: [
    { required: true, message: '部门名称是必填项', trigger: 'blur' },
    { min: 2, max: 10, message: '部门名称长度为2-10个字符', trigger: 'blur' },
  ]})

const deptFormRef = ref();

// 编辑部门
const edit = async (id) => {
  // 查询回显
  const result = await queryByIdApi(id);

  if (result.code) {
    dept.value = result.data;
    dialogFormVisible.value = true;
    formTitle.value = '编辑部门';
  } else {
    ElMessage.error(result.msg);
  }
  // 清空表单校验
  if (deptFormRef.value) {
    deptFormRef.value.resetFields();
  } 
}

// 删除部门
const deleteById = async (id) => {
  // 弹窗确认
  ElMessageBox.confirm('确定删除该部门吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(async () => {
    const result = await deleteApi(id);
    if (result.code) {
      ElMessage.success('删除成功');
      search();
    } else {
      ElMessage.error(result.msg);
    }
  }).catch(() => {
    ElMessage.info('取消删除');
  })
}
</script>

<template>
  <h1>部门管理</h1>
  <!-- 新增部门按钮 -->
  <div class="container">
    <el-button type="primary" @click="addDept">新增部门</el-button>
  </div>

  <!-- 部门列表 -->
  <div class="container">
    <el-table :data="depList" border style="width: 100%">
    <el-table-column type="index" label="序号" width="100" align="center"/>
    <el-table-column prop="name" label="部门名称" width="260" align="center" />
    <el-table-column prop="updateTime" label="最后操作时间" width="260" align="center" />
    <el-table-column label="操作" align="center">
      <template #default="scope">
        <el-button type="primary" size="small" @click="edit(scope.row.id)">编辑</el-button>
        <el-button type="danger" size="small" @click="deleteById(scope.row.id)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  </div>

  <!-- dialog对话框组件 -->
  <el-dialog v-model="dialogFormVisible" :title="formTitle" width="500">
    <el-form :model="dept" :rules="rules" ref="deptFormRef">
      <el-form-item label="部门名称" label-width="80px" prop="name">
        <el-input v-model="dept.name" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>
.container {
  margin: 20px 0;
}
</style>
