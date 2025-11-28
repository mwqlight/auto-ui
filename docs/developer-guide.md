# 自助UI平台开发指南

## 项目结构

```
auto-ui-platform/
├── backend/           # 后端服务
│   ├── src/           # Java源代码
│   │   ├── main/
│   │   │   ├── java/  # Java源文件
│   │   │   └── resources/  # 配置文件
│   │   └── test/      # 测试代码
│   └── pom.xml        # Maven配置
├── frontend/          # 前端应用
│   ├── src/           # Vue源代码
│   │   ├── assets/    # 静态资源
│   │   ├── components/ # 组件
│   │   ├── views/     # 页面视图
│   │   ├── utils/     # 工具函数
│   │   ├── App.vue    # 根组件
│   │   └── main.js    # 入口文件
│   ├── package.json   # npm配置
│   └── vue.config.js  # Vue配置
├── docs/              # 文档
└── README.md          # 项目说明
```

## 后端开发

### 技术栈
- Spring Boot 2.7.0
- Java 11
- Maven 3.8+
- H2 Database (开发环境)

### 项目配置
主要配置文件位于 `backend/src/main/resources/application.yml`

### API接口
1. **健康检查**
   - URL: `/api/v1/health`
   - 方法: GET
   - 描述: 检查服务运行状态

2. **AI生成UI**
   - URL: `/api/v1/generate-ui`
   - 方法: POST
   - 描述: 根据文本描述生成UI代码
   - 参数: `{ "description": "界面描述" }`

3. **导出代码**
   - URL: `/api/v1/export/{framework}`
   - 方法: POST
   - 描述: 导出指定框架的代码
   - 参数: `{ "uiDefinition": "UI定义" }`

### 扩展AI模型
AI集成相关代码位于 `backend/src/main/java/com/autoui/platform/config/AIConfig.java`，可根据需要扩展其他AI模型。

## 前端开发

### 技术栈
- Vue 3.2+
- Element Plus 2.2+
- SCSS

### 开发环境搭建
```bash
# 安装依赖
cd frontend
npm install

# 启动开发服务器
npm run serve
```

### 组件开发
1. **可视化画布组件** (`DraggableCanvas.vue`)
   - 实现拖拽功能
   - 支持组件属性编辑
   - 提供实时预览

2. **主题切换组件** (`ThemeSwitcher.vue`)
   - 管理主题状态
   - 应用主题样式

3. **组件库页面** (`ComponentLibrary.vue`)
   - 展示所有可用的UI组件
   - 支持按类别筛选组件
   - 集成源码查看功能

4. **源码查看器组件** (`SourceCodeViewer.vue`)
   - 显示组件在不同框架下的源码实现
   - 支持Vue.js、React、Angular、Flutter四种框架切换
   - 提供语法高亮显示

5. **AI生成器页面** (`AIGenerator.vue`)
   - 提供文本到UI的AI生成功能
   - 集成大模型API调用
   - 支持生成结果的预览和导出

### 路由配置
页面路由配置位于前端应用中，通过Vue Router管理。

### 样式系统
采用SCSS预处理器，全局样式定义在 `src/assets/styles/global.scss` 中。

### 新增组件
项目现已扩展组件库，新增以下组件类型：
1. 数据组件：表格(Table)、表单(Form)、卡片(Card)
2. 反馈组件：模态框(Modal)、警告提示(Alert)
3. 导航组件：导航菜单(Nav)、轮播图(Carousel)
4. 基础组件：选择器(Select)、开关(Switch)、进度条(Progress)

所有组件均支持在Vue.js、React、Angular、Flutter四种主流框架下查看源码实现。

## 部署指南

### 后端部署
```bash
# 构建项目
cd backend
./mvnw clean package

# 运行应用
java -jar target/auto-ui-platform-0.0.1-SNAPSHOT.jar
```

### 前端部署
```bash
# 构建生产版本
cd frontend
npm run build

# 部署dist目录到Web服务器
```

## 贡献指南

1. Fork 项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 代码规范

### 后端
遵循Java编码规范和Spring Boot最佳实践。

### 前端
遵循Vue.js风格指南和Element Plus组件使用规范。

## 测试

### 后端测试
使用JUnit 5进行单元测试，测试代码位于 `backend/src/test` 目录。

### 前端测试
使用Vue Test Utils进行组件测试。

## 许可证
本项目采用MIT许可证，详情请参见LICENSE文件。