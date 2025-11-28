<template>
  <div class="component-library">
    <el-page-header title="返回" content="组件库" @back="goBack" />
    
    <el-tabs v-model="activeTab" type="card">
      <el-tab-pane label="基础组件" name="basic">
        <el-row :gutter="20">
          <el-col :span="6" v-for="component in basicComponents" :key="component.id">
            <el-card class="component-card" shadow="hover">
              <div class="component-preview">
                <component :is="component.component" v-bind="component.props" />
              </div>
              <div class="component-info">
                <h4>{{ component.name }}</h4>
                <p>{{ component.description }}</p>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>
      
      <el-tab-pane label="布局组件" name="layout">
        <el-row :gutter="20">
          <el-col :span="6" v-for="component in layoutComponents" :key="component.id">
            <el-card class="component-card" shadow="hover">
              <div class="component-preview">
                <component :is="component.component" v-bind="component.props" />
              </div>
              <div class="component-info">
                <h4>{{ component.name }}</h4>
                <p>{{ component.description }}</p>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>
      
      <!-- 数据组件 -->
        <el-tab-pane label="数据组件" name="data">
          <div class="component-grid">
            <div
              v-for="component in dataComponents"
              :key="component.id"
              class="component-item"
              @click="addComponent(component)"
            >
              <div class="component-preview">
                <component :is="component.component" v-bind="component.props">
                  {{ component.name }}
                </component>
              </div>
              <div class="component-info">
                <h3>{{ component.name }}</h3>
                <p>{{ component.description }}</p>
              </div>
            </div>
          </div>
        </el-tab-pane>
        
        <!-- 反馈组件 -->
        <el-tab-pane label="反馈组件" name="feedback">
          <div class="component-grid">
            <div
              v-for="component in feedbackComponents"
              :key="component.id"
              class="component-item"
              @click="addComponent(component)"
            >
              <div class="component-preview">
                <component :is="component.component" v-bind="component.props">
                  {{ component.name }}
                </component>
              </div>
              <div class="component-info">
                <h3>{{ component.name }}</h3>
                <p>{{ component.description }}</p>
              </div>
            </div>
          </div>
        </el-tab-pane>
      
      <el-tab-pane label="我的组件" name="custom">
        <div class="custom-components-header">
          <el-button type="primary" @click="openCustomDialog">创建自定义组件</el-button>
        </div>
        
        <el-row :gutter="20" v-if="customComponents.length > 0">
          <el-col :span="6" v-for="component in customComponents" :key="component.id">
            <el-card class="component-card" shadow="hover">
              <div class="component-preview">
                <div v-html="component.preview"></div>
              </div>
              <div class="component-info">
                <h4>{{ component.name }}</h4>
                <p>{{ component.description }}</p>
              </div>
            </el-card>
          </el-col>
        </el-row>
        
        <div v-else class="empty-custom">
          <el-empty description="暂无自定义组件" />
        </div>
      </el-tab-pane>
    </el-tabs>
    
    <!-- 自定义组件对话框 -->
    <el-dialog title="创建自定义组件" v-model="customDialogVisible" width="600px">
      <el-form :model="customComponentForm" label-width="80px">
        <el-form-item label="组件名称">
          <el-input v-model="customComponentForm.name" placeholder="请输入组件名称" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input 
            v-model="customComponentForm.description" 
            type="textarea" 
            placeholder="请输入组件描述"
          />
        </el-form-item>
        <el-form-item label="HTML代码">
          <el-input 
            v-model="customComponentForm.html" 
            type="textarea" 
            :rows="6"
            placeholder="请输入组件的HTML代码"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="customDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveCustomComponent">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'ComponentLibrary',
  data() {
    return {
      activeTab: 'basic',
      customDialogVisible: false,
      customComponentForm: {
        name: '',
        description: '',
        html: ''
      },
      customComponents: [
        // 示例自定义组件
        {
          id: 1,
          name: '自定义卡片',
          description: '带有特殊样式的卡片组件',
          preview: '<div style="padding: 10px; border: 1px solid #409eff; border-radius: 4px; background-color: #ecf5ff;">自定义卡片</div>'
        }
      ],
      basicComponents: [
        {
          id: 1,
          name: '按钮',
          component: 'el-button',
          props: { type: 'primary', text: '按钮' },
          description: '常用的操作按钮'
        },
        {
          id: 2,
          name: '输入框',
          component: 'el-input',
          props: { placeholder: '请输入内容' },
          description: '用于输入文本'
        },
        {
          id: 3,
          name: '选择器',
          component: 'el-select',
          props: { placeholder: '请选择' },
          description: '用于从多个选项中选择'
        },
        {
          id: 4,
          name: '开关',
          component: 'el-switch',
          props: {},
          description: '表示两种相互对立的状态间的切换'
        },
        {
          id: 5,
          name: '单选框',
          component: 'el-radio',
          props: { label: '1' },
          description: '用于在多个备选项中选中单个状态'
        },
        {
          id: 6,
          name: '复选框',
          component: 'el-checkbox',
          props: {},
          description: '用于在多个选项中选择一个或多个'
        },
        {
          id: 7,
          name: '滑块',
          component: 'el-slider',
          props: {},
          description: '用于在某个数值区间内进行选择'
        },
        {
          id: 8,
          name: '日期选择器',
          component: 'el-date-picker',
          props: { placeholder: '请选择日期' },
          description: '用于选择日期'
        },
        {
          id: 9,
          name: '表格',
          component: 'el-table',
          props: { 
            data: [
              { date: '2023-05-01', name: '王小虎', address: '上海市普陀区金沙江路 1518 弄' },
              { date: '2023-05-02', name: '王小虎', address: '上海市普陀区金沙江路 1517 弄' }
            ],
            style: { width: '100%' }
          },
          description: '用于展示多条结构类似的数据'
        },
        {
          id: 10,
          name: '表单',
          component: 'el-form',
          props: { 
            model: { name: '', region: '' },
            labelWidth: '80px'
          },
          description: '用于收集和提交数据'
        },
        {
          id: 11,
          name: '卡片',
          component: 'el-card',
          props: { 
            header: '卡片标题',
            shadow: 'always'
          },
          description: '通用卡片容器'
        },
        {
          id: 12,
          name: '对话框',
          component: 'el-dialog',
          props: { 
            title: '提示',
            modelValue: true,
            width: '30%'
          },
          description: '用于重要的消息提醒'
        }
      ],
      layoutComponents: [
        {
          id: 1,
          name: '布局容器',
          component: 'el-container',
          props: {},
          description: '用于布局的容器组件'
        },
        {
          id: 2,
          name: '栅格行',
          component: 'el-row',
          props: {},
          description: '栅格布局行'
        },
        {
          id: 3,
          name: '栅格列',
          component: 'el-col',
          props: { span: 12 },
          description: '栅格布局列'
        },
        {
          id: 4,
          name: '卡片',
          component: 'el-card',
          props: { header: '卡片标题' },
          description: '通用卡片容器'
        },
        {
          id: 5,
          name: '分割线',
          component: 'el-divider',
          props: {},
          description: '用于分隔内容'
        },
        {
          id: 6,
          name: '页头',
          component: 'el-page-header',
          props: { content: '页面标题' },
          description: '用于页面标题和返回操作'
        },
        {
          id: 7,
          name: '步骤条',
          component: 'el-steps',
          props: { active: 1 },
          description: '用于引导用户按照流程完成任务'
        },
        {
          id: 8,
          name: '标签页',
          component: 'el-tabs',
          props: {},
          description: '用于组织相关内容'
        },
        {
          id: 9,
          name: '导航菜单',
          component: 'el-menu',
          props: { mode: 'horizontal' },
          description: '为网站提供导航功能'
        },
        {
          id: 10,
          name: '面包屑',
          component: 'el-breadcrumb',
          props: {},
          description: '显示当前页面的路径'
        },
        {
          id: 11,
          name: '分页',
          component: 'el-pagination',
          props: { 
            total: 100,
            pageSize: 10
          },
          description: '用于分隔长列表'
        },
        {
          id: 12,
          name: '轮播图',
          component: 'el-carousel',
          props: { height: '150px' },
          description: '用于循环播放图片或其他内容'
        }
      ],
      dataComponents: [
        {
          id: 1,
          name: '表格',
          component: 'el-table',
          props: { 
            data: [
              { date: '2023-05-01', name: '王小虎', address: '上海市普陀区金沙江路 1518 弄' },
              { date: '2023-05-02', name: '王小虎', address: '上海市普陀区金沙江路 1517 弄' }
            ],
            style: { width: '100%' }
          },
          description: '用于展示多条结构类似的数据'
        },
        {
          id: 2,
          name: '标签页',
          component: 'el-tabs',
          props: {},
          description: '用于组织相关内容'
        },
        {
          id: 3,
          name: '树形控件',
          component: 'el-tree',
          props: { 
            data: [
              {
                label: '一级 1',
                children: [{
                  label: '二级 1-1',
                  children: [{ label: '三级 1-1-1' }]
                }]
              }
            ]
          },
          description: '用于展示层级数据'
        },
        {
          id: 4,
          name: '分页',
          component: 'el-pagination',
          props: { 
            total: 100,
            pageSize: 10
          },
          description: '用于分隔长列表'
        },
        {
          id: 5,
          name: '进度条',
          component: 'el-progress',
          props: { percentage: 50 },
          description: '用于展示操作进度'
        },
        {
          id: 6,
          name: '统计数值',
          component: 'el-statistic',
          props: { 
            value: 268500,
            title: '总销售额'
          },
          description: '用于突出显示某个业务数据'
        },
        {
          id: 7,
          name: '时间线',
          component: 'el-timeline',
          props: {},
          description: '用于按时间顺序展示信息'
        },
        {
          id: 8,
          name: '结果',
          component: 'el-result',
          props: { 
            icon: 'success',
            title: '成功',
            subTitle: '请根据提示进行操作'
          },
          description: '用于反馈一系列操作任务的处理结果'
        }
      ],
      feedbackComponents: [
        {
          id: 1,
          name: '对话框',
          component: 'el-dialog',
          props: { 
            title: '提示',
            modelValue: true,
            width: '30%'
          },
          description: '用于重要的消息提醒'
        },
        {
          id: 2,
          name: '消息提示',
          component: 'el-message',
          props: { message: '这是一条消息提示' },
          description: '用于轻量级的消息反馈'
        },
        {
          id: 3,
          name: '通知',
          component: 'el-notification',
          props: { 
            title: '通知',
            message: '这是一条通知'
          },
          description: '用于主动推送消息'
        },
        {
          id: 4,
          name: '加载',
          component: 'el-loading',
          props: {},
          description: '用于页面或模块的加载状态'
        },
        {
          id: 5,
          name: '气泡确认框',
          component: 'el-popconfirm',
          props: { 
            title: '确定删除吗？'
          },
          description: '用于确认操作'
        },
        {
          id: 6,
          name: '文字提示',
          component: 'el-tooltip',
          props: { 
            content: '这是提示文字',
            placement: 'top'
          },
          description: '用于文字提示'
        },
        {
          id: 7,
          name: '抽屉',
          component: 'el-drawer',
          props: { 
            title: '抽屉标题',
            modelValue: true
          },
          description: '用于收纳大量内容'
        },
        {
          id: 8,
          name: '警告',
          component: 'el-alert',
          props: { 
            title: '成功提示的文案',
            type: 'success'
          },
          description: '用于页面中的重要提示信息'
        }
      ]
    }
  },
  methods: {
    goBack() {
      this.$router.push('/')
    },
    openCustomDialog() {
      this.customComponentForm = {
        name: '',
        description: '',
        html: ''
      }
      this.customDialogVisible = true
    },
    saveCustomComponent() {
      if (!this.customComponentForm.name || !this.customComponentForm.html) {
        this.$message.warning('请填写完整的组件信息')
        return
      }
      
      const newComponent = {
        id: Date.now(),
        name: this.customComponentForm.name,
        description: this.customComponentForm.description,
        preview: this.customComponentForm.html
      }
      
      this.customComponents.push(newComponent)
      this.customDialogVisible = false
      this.$message.success('自定义组件创建成功')
    }
  }
}
</script>

<style scoped>
.component-library {
  padding: 20px;
}

.component-card {
  margin-bottom: 20px;
  cursor: pointer;
}

.component-preview {
  height: 100px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid #eee;
  margin-bottom: 10px;
}

.component-info h4 {
  margin: 0 0 5px 0;
  font-size: 14px;
}

.component-info p {
  margin: 0;
  font-size: 12px;
  color: #909399;
}

.custom-components-header {
  margin-bottom: 20px;
  text-align: right;
}

.empty-custom {
  padding: 50px 0;
}
</style>