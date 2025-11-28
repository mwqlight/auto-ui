<template>
  <div class="export-page">
    <el-page-header title="返回" content="导出代码" @back="goBack" />
    
    <el-row :gutter="20">
      <el-col :span="16">
        <el-card class="export-card">
          <h3>导出设置</h3>
          
          <el-form :model="exportForm" label-width="120px">
            <el-form-item label="目标框架">
              <el-select v-model="exportForm.framework" placeholder="请选择目标框架">
                <el-option
                  v-for="framework in frameworks"
                  :key="framework.value"
                  :label="framework.label"
                  :value="framework.value"
                />
              </el-select>
            </el-form-item>
            
            <el-form-item label="项目名称">
              <el-input v-model="exportForm.projectName" placeholder="请输入项目名称" />
            </el-form-item>
            
            <el-form-item label="导出格式">
              <el-radio-group v-model="exportForm.format">
                <el-radio label="single">单文件</el-radio>
                <el-radio label="project">完整项目</el-radio>
              </el-radio-group>
            </el-form-item>
            
            <el-form-item label="包含样式">
              <el-switch v-model="exportForm.includeStyles" />
            </el-form-item>
            
            <el-form-item label="包含路由">
              <el-switch v-model="exportForm.includeRouter" />
            </el-form-item>
          </el-form>
          
          <div class="export-actions">
            <el-button type="primary" @click="exportCode" :loading="exporting">
              {{ exporting ? '导出中...' : '导出代码' }}
            </el-button>
            <el-button @click="previewCode">预览代码</el-button>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="8">
        <el-card class="info-card">
          <h3>导出说明</h3>
          <ul>
            <li>支持导出到多种前端框架</li>
            <li>可选择导出完整项目或单个组件文件</li>
            <li>自动生成依赖配置文件</li>
            <li>保留组件的样式和交互功能</li>
          </ul>
          
          <h3>支持的框架</h3>
          <el-tag v-for="framework in frameworks" :key="framework.value" class="framework-tag">
            {{ framework.label }}
          </el-tag>
        </el-card>
      </el-col>
    </el-row>
    
    <!-- 代码预览对话框 -->
    <el-dialog title="代码预览" v-model="previewDialogVisible" width="80%">
      <el-tabs v-model="activePreviewTab">
        <el-tab-pane label="HTML" name="html">
          <pre><code class="language-html">{{ previewCodeContent.html }}</code></pre>
        </el-tab-pane>
        <el-tab-pane label="CSS" name="css">
          <pre><code class="language-css">{{ previewCodeContent.css }}</code></pre>
        </el-tab-pane>
        <el-tab-pane label="JavaScript" name="js">
          <pre><code class="language-javascript">{{ previewCodeContent.js }}</code></pre>
        </el-tab-pane>
      </el-tabs>
      <template #footer>
        <el-button @click="previewDialogVisible = false">关闭</el-button>
        <el-button type="primary" @click="downloadCode">下载代码</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Export',
  data() {
    return {
      exportForm: {
        framework: 'vue',
        projectName: 'my-ui-project',
        format: 'project',
        includeStyles: true,
        includeRouter: false
      },
      frameworks: [
        { label: 'Vue.js', value: 'vue' },
        { label: 'React', value: 'react' },
        { label: 'Angular', value: 'angular' },
        { label: 'Flutter', value: 'flutter' }
      ],
      exporting: false,
      previewDialogVisible: false,
      activePreviewTab: 'html',
      previewCodeContent: {
        html: '',
        css: '',
        js: ''
      }
    }
  },
  methods: {
    goBack() {
      this.$router.push('/')
    },
    async exportCode() {
      this.exporting = true
      
      try {
        // 模拟导出过程
        await new Promise(resolve => setTimeout(resolve, 2000))
        this.$message.success('代码导出成功')
      } catch (error) {
        this.$message.error('导出失败，请稍后重试')
      } finally {
        this.exporting = false
      }
    },
    previewCode() {
      // 模拟生成预览代码
      this.previewCodeContent = {
        html: '<div class="container">\n  <h1>示例组件</h1>\n  <button class="btn">点击按钮</button>\n</div>',
        css: '.container {\n  padding: 20px;\n}\n\n.btn {\n  background-color: #409eff;\n  color: white;\n  border: none;\n  padding: 10px 20px;\n  border-radius: 4px;\n}',
        js: 'document.querySelector(".btn").addEventListener("click", function() {\n  alert("按钮被点击了!");\n});'
      }
      
      this.previewDialogVisible = true
    },
    downloadCode() {
      this.$message.success('代码下载开始')
      this.previewDialogVisible = false
    }
  }
}
</script>

<style scoped>
.export-page {
  padding: 20px;
}

.export-card,
.info-card {
  height: 100%;
}

.export-actions {
  text-align: right;
  margin-top: 30px;
}

.framework-tag {
  margin-right: 10px;
  margin-bottom: 10px;
}

pre {
  background-color: #f5f5f5;
  padding: 15px;
  border-radius: 4px;
  max-height: 400px;
  overflow: auto;
}
</style>