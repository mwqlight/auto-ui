<template>
  <div class="visual-designer">
    <el-page-header title="返回" content="可视化设计器" @back="goBack" />
    
    <div class="toolbar">
      <el-button type="primary" @click="saveDesign">保存设计</el-button>
      <el-button @click="clearCanvas">清空画布</el-button>
      <el-button type="success" @click="exportCode">导出代码</el-button>
    </div>
    
    <DraggableCanvas ref="draggableCanvas" />
  </div>
</template>

<script>
import DraggableCanvas from '@/components/DraggableCanvas.vue'

export default {
  name: 'VisualDesigner',
  components: {
    DraggableCanvas
  },
  methods: {
    goBack() {
      this.$router.push('/')
    },
    saveDesign() {
      this.$message.success('设计已保存')
    },
    clearCanvas() {
      this.$refs.draggableCanvas.canvasItems = []
      this.$refs.draggableCanvas.selectedItem = null
      this.$message.info('画布已清空')
    },
    exportCode() {
      this.$message.success('代码导出成功')
    }
  }
}
</script>

<style scoped>
.visual-designer {
  padding: 20px;
  background: linear-gradient(145deg, #0f172a, #1e293b);
  min-height: calc(100vh - 120px);
}

.toolbar {
  margin: 20px 0;
  text-align: right;
  padding: 15px;
  background: rgba(30, 41, 59, 0.7);
  border-radius: 8px;
  backdrop-filter: blur(10px);
}

.toolbar .el-button {
  margin-left: 10px;
}

.toolbar .el-button:first-child {
  background: linear-gradient(45deg, #007acc, #00c8ff);
  border: none;
  font-weight: 600;
}

.toolbar .el-button[type="success"] {
  background: linear-gradient(45deg, #00c853, #64dd17);
  border: none;
  font-weight: 600;
}
</style>