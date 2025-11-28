// 主题配置
export const themes = {
  default: {
    name: '默认主题',
    colors: {
      primary: '#409eff',
      success: '#67c23a',
      warning: '#e6a23c',
      danger: '#f56c6c',
      info: '#909399'
    }
  },
  dark: {
    name: '暗黑主题',
    colors: {
      primary: '#409eff',
      success: '#67c23a',
      warning: '#e6a23c',
      danger: '#f56c6c',
      info: '#909399'
    }
  },
  tech: {
    name: '科技蓝',
    colors: {
      primary: '#007acc',
      success: '#009966',
      warning: '#ff9900',
      danger: '#cc0000',
      info: '#666699'
    }
  },
  elegant: {
    name: '优雅紫',
    colors: {
      primary: '#663399',
      success: '#339966',
      warning: '#cc9900',
      danger: '#cc3300',
      info: '#6699cc'
    }
  }
}

// 应用主题
export function applyTheme(themeName) {
  const theme = themes[themeName] || themes.default
  const root = document.documentElement
  
  // 设置CSS变量
  Object.keys(theme.colors).forEach(colorKey => {
    root.style.setProperty(`--el-color-${colorKey}`, theme.colors[colorKey])
  })
  
  // 保存到localStorage
  localStorage.setItem('ui-theme', themeName)
}

// 获取当前主题
export function getCurrentTheme() {
  return localStorage.getItem('ui-theme') || 'default'
}