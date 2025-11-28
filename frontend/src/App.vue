<template>
  <div id="app">
    <el-container>
      <el-header class="header">
        <div class="logo">自助UI平台</div>
        <div class="header-right">
          <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
          >
            <el-menu-item index="1">可视化设计</el-menu-item>
            <el-menu-item index="2">AI生成</el-menu-item>
            <el-menu-item index="3">组件库</el-menu-item>
            <el-menu-item index="4">源码查看</el-menu-item>
          </el-menu>
          <ThemeSwitcher class="theme-switcher" />
        </div>
      </el-header>
      
      <el-main>
        <router-view />
      </el-main>
      
      <el-footer class="footer">
        © 2023 自助UI平台 - 快速创建您的用户界面
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import ThemeSwitcher from '@/components/ThemeSwitcher.vue'

export default {
  name: 'App',
  components: {
    ThemeSwitcher
  },
  data() {
    return {
      activeIndex: '1'
    }
  },
  setup() {
    const router = useRouter()
    return { router }
  },
  mounted() {
    // 根据当前路由设置激活的菜单项
    this.updateActiveIndex()
    
    // 监听路由变化
    this.router.afterEach(() => {
      this.updateActiveIndex()
    })
  },
  methods: {
    handleSelect(key) {
      this.activeIndex = key
      // 根据菜单项导航到相应页面
      switch (key) {
        case '1':
          this.router.push('/visual-design')
          break
        case '2':
          this.router.push('/ai-generator')
          break
        case '3':
          this.router.push('/component-library')
          break
        case '4':
          this.router.push('/source-code')
          break
      }
    },
    updateActiveIndex() {
      const path = this.router.currentRoute.value.path
      if (path === '/visual-design') {
        this.activeIndex = '1'
      } else if (path === '/ai-generator') {
        this.activeIndex = '2'
      } else if (path === '/component-library') {
        this.activeIndex = '3'
      } else if (path === '/source-code') {
        this.activeIndex = '4'
      } else {
        this.activeIndex = '1'
      }
    }
  }
}
</script>

<style>
#app {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #f1f5f9;
  background-color: #0f172a;
  min-height: 100vh;
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: linear-gradient(90deg, #0f172a, #1e293b);
  color: white;
  padding: 0 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
  border-bottom: 1px solid #334155;
}

.header-right {
  display: flex;
  align-items: center;
}

.logo {
  font-size: 24px;
  font-weight: bold;
  background: linear-gradient(90deg, #007acc, #00c8ff);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.theme-switcher {
  margin-left: 20px;
}

.footer {
  text-align: center;
  background: linear-gradient(90deg, #0f172a, #1e293b);
  color: #94a3b8;
  padding: 20px;
  border-top: 1px solid #334155;
  margin-top: 20px;
}
</style>