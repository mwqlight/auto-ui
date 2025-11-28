# 自助UI平台

## 项目简介
这是一个自助UI平台项目，旨在为用户提供一种简单便捷的方式来创建和管理用户界面。通过可视化操作和AI生成两种方式，用户可以快速构建现代化的UI界面，并导出为多种前端框架代码。

## 核心功能
1. **可视化界面**：通过拖拽、点击等操作创建和管理UI
2. **AI生成**：通过描述性文本自动生成对应UI
3. **组件库**：提供丰富的常用UI组件，包括按钮、输入框、表格、表单、卡片、模态框、导航菜单、轮播图、选择器、开关和进度条等
4. **自定义组件**：支持用户自定义组件以满足特殊需求
5. **多框架导出**：支持导出为Flutter、React、Vue、Angular等前端框架代码
6. **源码查看**：支持在Vue.js、React、Angular和Flutter四种主流前端框架间切换查看组件源码
7. **主题切换**：提供多种UI风格和色彩方案
8. **无需注册**：用户可直接使用平台功能

## 技术栈
- 后端：SpringBoot + AI大模型集成
- 前端：Vue3 + Element Plus

## 快速开始

### 环境要求
- Java 11+
- Node.js 14+
- Maven 3.8+

### 后端启动
```bash
# 进入后端目录
cd backend

# 安装依赖并启动
./mvnw spring-boot:run
```

### 前端启动
```bash
# 进入前端目录
cd frontend

# 安装依赖
npm install

# 启动开发服务器
npm run serve
```

## 项目结构
```
auto-ui-platform/
├── backend/           # 后端服务
│   ├── src/           # Java源代码
│   └── pom.xml        # Maven配置
├── frontend/          # 前端应用
│   ├── src/           # Vue源代码
│   └── package.json   # npm配置
├── docs/              # 文档
└── README.md          # 项目说明
```

## 文档
- [用户手册](docs/user-guide.md)
- [开发指南](docs/developer-guide.md)

## 许可证
本项目采用MIT许可证，详情请参见LICENSE文件。

## 联系我们
- 邮箱：support@autoui.com
- 官方网站：https://www.autoui.com