<template>
  <div class="theme-switcher">
    <el-dropdown @command="changeTheme">
      <el-button type="primary">
        {{ currentThemeName }}
        <el-icon class="el-icon--right">
          <arrow-down />
        </el-icon>
      </el-button>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item 
            v-for="(theme, name) in themes" 
            :key="name" 
            :command="name"
            :disabled="currentTheme === name"
          >
            {{ theme.name }}
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script>
import { themes, applyTheme, getCurrentTheme } from '@/utils/theme.js'
import { ArrowDown } from '@element-plus/icons-vue'

export default {
  name: 'ThemeSwitcher',
  components: {
    ArrowDown
  },
  data() {
    return {
      themes,
      currentTheme: getCurrentTheme()
    }
  },
  computed: {
    currentThemeName() {
      return themes[this.currentTheme]?.name || '默认主题'
    }
  },
  mounted() {
    // 应用保存的主题
    applyTheme(this.currentTheme)
  },
  methods: {
    changeTheme(themeName) {
      this.currentTheme = themeName
      applyTheme(themeName)
      this.$message.success(`已切换到${themes[themeName].name}`)
    }
  }
}
</script>

<style scoped>
.theme-switcher {
  display: inline-block;
}
</style>