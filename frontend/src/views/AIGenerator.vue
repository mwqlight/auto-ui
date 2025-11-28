<template>
  <div class="ai-generator">
    <el-page-header title="返回" content="AI生成器" @back="goBack" />
    
    <el-row :gutter="20">
      <el-col :span="12">
        <div class="input-section">
          <h3>描述您的界面需求</h3>
          <el-input
            v-model="userDescription"
            type="textarea"
            :rows="10"
            placeholder="请详细描述您想要的界面，例如：我需要一个登录表单，包含用户名和密码输入框，以及登录和取消按钮..."
          />
          <div class="actions">
            <el-button type="primary" @click="generateUI" :loading="generating">
              {{ generating ? '生成中...' : '生成界面' }}
            </el-button>
            <el-button @click="clearInput">清空</el-button>
          </div>
        </div>
      </el-col>
      
      <el-col :span="12">
        <div class="preview-section">
          <h3>预览</h3>
          <div class="preview-container">
            <div v-if="generatedCode" v-html="generatedCode"></div>
            <div v-else class="placeholder">
              <p>生成的界面将在此处显示</p>
            </div>
          </div>
          <div class="export-actions" v-if="generatedCode">
            <el-button type="success" @click="exportCode">导出代码</el-button>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'AIGenerator',
  data() {
    return {
      userDescription: '',
      generatedCode: '',
      generating: false
    }
  },
  methods: {
    goBack() {
      this.$router.push('/')
    },
    async generateUI() {
      if (!this.userDescription.trim()) {
        this.$message.warning('请输入界面描述')
        return
      }
      
      this.generating = true
      
      try {
        // 模拟API调用
        const response = await fetch('/api/v1/generate-ui', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ description: this.userDescription })
        })
        
        const data = await response.json()
        this.generatedCode = data.generatedCode
        
        this.$message.success('界面生成成功')
      } catch (error) {
        this.$message.error('生成失败，请稍后重试')
      } finally {
        this.generating = false
      }
    },
    clearInput() {
      this.userDescription = ''
      this.generatedCode = ''
    },
    exportCode() {
      this.$message.success('代码导出成功')
    }
  }
}
</script>

<style scoped>
.ai-generator {
  padding: 20px;
}

.input-section,
.preview-section {
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  padding: 20px;
  height: 100%;
}

.actions,
.export-actions {
  margin-top: 20px;
  text-align: right;
}

.actions .el-button,
.export-actions .el-button {
  margin-left: 10px;
}

.preview-container {
  min-height: 400px;
  border: 1px dashed #ccc;
  padding: 20px;
  background-color: #f9f9f9;
}

.placeholder {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  color: #909399;
}
</style>