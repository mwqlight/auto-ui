import { createRouter, createWebHistory } from 'vue-router'
import VisualDesigner from './views/VisualDesigner.vue'
import AIGenerator from './views/AIGenerator.vue'
import ComponentLibrary from './views/ComponentLibrary.vue'
import Export from './views/Export.vue'
import SourceCodeViewer from './views/SourceCodeViewer.vue'

const routes = [
  {
    path: '/',
    redirect: '/visual-design'
  },
  {
    path: '/visual-design',
    name: 'VisualDesigner',
    component: VisualDesigner
  },
  {
    path: '/ai-generator',
    name: 'AIGenerator',
    component: AIGenerator
  },
  {
    path: '/component-library',
    name: 'ComponentLibrary',
    component: ComponentLibrary
  },
  {
    path: '/export',
    name: 'Export',
    component: Export
  },
  {
    path: '/source-code',
    name: 'SourceCodeViewer',
    component: SourceCodeViewer
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router