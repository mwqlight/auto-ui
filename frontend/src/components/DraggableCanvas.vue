<template>
  <div class="draggable-canvas">
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="components-panel">
          <h3>组件库</h3>
          <div 
            v-for="component in components" 
            :key="component.type"
            class="component-item"
            draggable="true"
            @dragstart="dragStart($event, component)"
          >
            <el-button type="primary" plain>
              {{ component.name }}
            </el-button>
          </div>
        </div>
      </el-col>
      
      <el-col :span="12">
        <div 
          class="canvas-area"
          @dragover="dragOver"
          @drop="drop"
        >
          <h3>设计区域</h3>
          <div class="canvas">
            <div 
              v-for="(item, index) in canvasItems" 
              :key="index"
              class="canvas-item"
              :class="{ selected: selectedItem === index }"
              @click="selectItem(index)"
            >
              <component :is="item.component" v-bind="item.props" />
              <el-button 
                class="delete-btn" 
                type="danger" 
                size="small" 
                @click.stop="removeItem(index)"
                circle
              >
                X
              </el-button>
            </div>
            
            <div v-if="canvasItems.length === 0" class="empty-canvas">
              请从左侧拖拽组件到此区域
            </div>
          </div>
        </div>
      </el-col>
      
      <el-col :span="6">
        <div class="properties-panel">
          <h3>属性面板</h3>
          <div v-if="selectedItem !== null">
            <el-form label-position="top">
              <el-form-item label="文本内容">
                <el-input 
                  v-model="canvasItems[selectedItem].props.text" 
                  placeholder="请输入文本内容"
                />
              </el-form-item>
              <el-form-item label="按钮类型">
                <el-select 
                  v-model="canvasItems[selectedItem].props.type" 
                  placeholder="请选择按钮类型"
                >
                  <el-option label="主要按钮" value="primary" />
                  <el-option label="成功按钮" value="success" />
                  <el-option label="警告按钮" value="warning" />
                  <el-option label="危险按钮" value="danger" />
                  <el-option label="信息按钮" value="info" />
                </el-select>
              </el-form-item>
            </el-form>
          </div>
          <div v-else>
            <p>请选择一个组件以编辑属性</p>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'DraggableCanvas',
  data() {
    return {
      components: [
        { type: 'el-button', name: '按钮' },
        { type: 'el-input', name: '输入框' },
        { type: 'el-select', name: '选择器' },
        { type: 'el-card', name: '卡片' }
      ],
      canvasItems: [],
      selectedItem: null,
      dragComponent: null
    }
  },
  methods: {
    dragStart(event, component) {
      event.dataTransfer.setData('component', JSON.stringify(component))
    },
    dragOver(event) {
      event.preventDefault()
    },
    drop(event) {
      event.preventDefault()
      const componentData = event.dataTransfer.getData('component')
      if (componentData) {
        const component = JSON.parse(componentData)
        this.canvasItems.push({
          component: component.type,
          props: {
            text: '默认文本',
            type: 'primary'
          }
        })
      }
    },
    selectItem(index) {
      this.selectedItem = index
    },
    removeItem(index) {
      this.canvasItems.splice(index, 1)
      if (this.selectedItem === index) {
        this.selectedItem = null
      } else if (this.selectedItem > index) {
        this.selectedItem--
      }
    }
  }
}
</script>

<style scoped>
.draggable-canvas {
  padding: 20px;
}

.components-panel {
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  padding: 15px;
  height: 600px;
}

.component-item {
  margin-bottom: 10px;
  cursor: grab;
}

.component-item:hover {
  transform: scale(1.05);
}

.canvas-area {
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  padding: 15px;
  min-height: 600px;
}

.canvas {
  min-height: 500px;
  position: relative;
  border: 1px dashed #ccc;
  padding: 20px;
}

.canvas-item {
  position: relative;
  display: inline-block;
  margin: 10px;
  padding: 10px;
  border: 1px solid transparent;
  cursor: pointer;
}

.canvas-item.selected {
  border-color: #409eff;
  background-color: rgba(64, 158, 255, 0.1);
}

.delete-btn {
  position: absolute;
  top: -10px;
  right: -10px;
}

.empty-canvas {
  text-align: center;
  color: #909399;
  font-size: 14px;
  padding: 50px;
}

.properties-panel {
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  padding: 15px;
  height: 600px;
}
</style>