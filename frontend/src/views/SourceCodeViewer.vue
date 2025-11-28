<template>
  <div class="source-code-viewer">
    <el-page-header title="返回" content="源码查看" @back="goBack" />
    
    <el-row :gutter="20">
      <el-col :span="16">
        <el-card class="code-card">
          <div class="code-header">
            <el-select v-model="selectedFramework" placeholder="选择框架" @change="loadSourceCode">
              <el-option
                v-for="framework in frameworks"
                :key="framework.value"
                :label="framework.label"
                :value="framework.value"
              />
            </el-select>
            
            <el-select v-model="selectedComponent" placeholder="选择组件" @change="loadSourceCode">
              <el-option
                v-for="component in components"
                :key="component.value"
                :label="component.label"
                :value="component.value"
              />
            </el-select>
          </div>
          
          <div class="code-content">
            <pre><code :class="'language-' + getCodeLanguage()">{{ sourceCode }}</code></pre>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="8">
        <el-card class="info-card">
          <h3>组件信息</h3>
          <div class="component-info" v-if="selectedComponentInfo">
            <h4>{{ selectedComponentInfo.name }}</h4>
            <p>{{ selectedComponentInfo.description }}</p>
            
            <h4>特性</h4>
            <ul>
              <li v-for="feature in selectedComponentInfo.features" :key="feature">
                {{ feature }}
              </li>
            </ul>
            
            <h4>使用场景</h4>
            <p>{{ selectedComponentInfo.useCase }}</p>
          </div>
          
          <div v-else>
            <el-empty description="请选择组件查看详情" />
          </div>
        </el-card>
        
        <el-card class="actions-card">
          <h3>操作</h3>
          <el-button type="primary" @click="copyCode" :loading="copying">复制代码</el-button>
          <el-button @click="downloadCode">下载代码</el-button>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'SourceCodeViewer',
  data() {
    return {
      selectedFramework: 'vue',
      selectedComponent: 'button',
      copying: false,
      frameworks: [
        { label: 'Vue.js', value: 'vue' },
        { label: 'React', value: 'react' },
        { label: 'Angular', value: 'angular' },
        { label: 'Flutter', value: 'flutter' }
      ],
      components: [
        { label: '按钮 Button', value: 'button' },
        { label: '输入框 Input', value: 'input' },
        { label: '表格 Table', value: 'table' },
        { label: '表单 Form', value: 'form' },
        { label: '卡片 Card', value: 'card' },
        { label: '模态框 Modal', value: 'modal' },
        { label: '导航菜单 Nav', value: 'nav' },
        { label: '轮播图 Carousel', value: 'carousel' },
        { label: '选择器 Select', value: 'select' },
        { label: '开关 Switch', value: 'switch' },
        { label: '进度条 Progress', value: 'progress' },
        { label: '警告 Alert', value: 'alert' }
      ],
      sourceCode: '// 请选择框架和组件查看源码',
      selectedComponentInfo: null
    }
  },
  mounted() {
    this.loadSourceCode()
  },
  methods: {
    goBack() {
      this.$router.push('/')
    },
    getCodeLanguage() {
      const languageMap = {
        'vue': 'html',
        'react': 'jsx',
        'angular': 'typescript',
        'flutter': 'dart'
      }
      return languageMap[this.selectedFramework] || 'html'
    },
    loadSourceCode() {
      // 更新选中组件的信息
      const componentDetails = {
        'button': {
          name: '按钮 Button',
          description: '按钮是一种常用的交互控件，用于触发各种操作。',
          features: ['多种样式', '不同尺寸', '禁用状态', '加载状态'],
          useCase: '适用于表单提交、页面跳转、弹窗确认等各种需要用户点击操作的场景。'
        },
        'input': {
          name: '输入框 Input',
          description: '输入框用于接收用户的文本输入，支持多种输入类型。',
          features: ['多种类型', '前后缀', '清空按钮', '密码框'],
          useCase: '适用于登录表单、搜索框、数据录入等需要用户输入文本的场景。'
        },
        'table': {
          name: '表格 Table',
          description: '表格用于展示行列数据，支持排序、筛选等功能。',
          features: ['排序', '分页', '筛选', '固定列'],
          useCase: '适用于数据列表展示、报表统计、数据管理等需要结构化展示数据的场景。'
        },
        'form': {
          name: '表单 Form',
          description: '表单用于收集用户输入的数据，支持验证和提交。',
          features: ['字段验证', '布局管理', '数据绑定', '提交处理'],
          useCase: '适用于注册登录、信息填写、数据编辑等需要收集用户信息的场景。'
        },
        'card': {
          name: '卡片 Card',
          description: '卡片是一种通用的内容容器，用于组织和展示相关信息。',
          features: ['标题和副标题', '边框样式', '阴影效果', '可折叠'],
          useCase: '适用于仪表盘、内容摘要、产品展示等需要将相关内容组合在一起的场景。'
        },
        'modal': {
          name: '模态框 Modal',
          description: '模态框用于在当前页面上叠加显示重要内容，阻止用户与底层页面交互。',
          features: ['自定义宽度', '可拖拽', '遮罩层', '键盘关闭'],
          useCase: '适用于重要信息提示、表单填写、详情查看等需要用户专注处理的场景。'
        },
        'nav': {
          name: '导航菜单 Navigation',
          description: '导航菜单用于在应用程序的不同部分之间进行导航。',
          features: ['水平垂直布局', '多级菜单', '图标支持', '主题定制'],
          useCase: '适用于网站导航、后台管理系统菜单、功能模块切换等场景。'
        },
        'carousel': {
          name: '轮播图 Carousel',
          description: '轮播图用于循环播放一系列图片或内容，常用于广告展示。',
          features: ['自动播放', '指示器', '左右箭头', '手势滑动'],
          useCase: '适用于首页banner、产品展示、图片浏览等需要循环展示内容的场景。'
        }
      }
      
      this.selectedComponentInfo = componentDetails[this.selectedComponent]
      
      // 根据框架和组件生成相应的源码
      const sourceCodes = {
        'vue': {
          'button': `<div>
  <el-button>默认按钮</el-button>
  <el-button type="primary">主要按钮</el-button>
  <el-button type="success">成功按钮</el-button>
  <el-button type="warning">警告按钮</el-button>
  <el-button type="danger">危险按钮</el-button>
  <el-button type="info">信息按钮</el-button>
</div>`,
          'input': `<div>
  <el-input v-model="inputValue" placeholder="请输入内容"></el-input>
  <el-input v-model="password" type="password" placeholder="请输入密码"></el-input>
</div>`,
          'table': `<el-table :data="tableData" style="width: 100%">
  <el-table-column prop="date" label="日期" width="180"></el-table-column>
  <el-table-column prop="name" label="姓名" width="180"></el-table-column>
  <el-table-column prop="address" label="地址"></el-table-column>
</el-table>`,
          'form': `<el-form :model="form" label-width="80px">
  <el-form-item label="活动名称">
    <el-input v-model="form.name"></el-input>
  </el-form-item>
  <el-form-item label="活动区域">
    <el-select v-model="form.region" placeholder="请选择活动区域">
      <el-option label="北京" value="beijing"></el-option>
      <el-option label="上海" value="shanghai"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="onSubmit">立即创建</el-button>
    <el-button>取消</el-button>
  </el-form-item>
</el-form>`,
          'card': `<el-card class="box-card">
  <template #header>
    <div class="card-header">
      <span>卡片名称</span>
      <el-button class="button" type="text">操作按钮</el-button>
    </div>
  </template>
  <div v-for="o in 4" :key="o" class="text item">
    {{ '列表内容 ' + o }}
  </div>
</el-card>`,
          'modal': `<div>
  <el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button>
  
  <el-dialog title="提示" v-model="dialogVisible" width="30%">
    <span>这是一段信息</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </template>
  </el-dialog>
</div>`,
          'nav': `<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
  <el-menu-item index="1">处理中心</el-menu-item>
  <el-sub-menu index="2">
    <template #title>我的工作台</template>
    <el-menu-item index="2-1">选项1</el-menu-item>
    <el-menu-item index="2-2">选项2</el-menu-item>
    <el-menu-item index="2-3">选项3</el-menu-item>
    <el-sub-menu index="2-4">
      <template #title>选项4</template>
      <el-menu-item index="2-4-1">选项1</el-menu-item>
      <el-menu-item index="2-4-2">选项2</el-menu-item>
      <el-menu-item index="2-4-3">选项3</el-menu-item>
    </el-sub-menu>
  </el-sub-menu>
  <el-menu-item index="3" disabled>消息中心</el-menu-item>
  <el-menu-item index="4">订单管理</el-menu-item>
</el-menu>`,
          'carousel': `<div>
  <el-carousel height="150px">
    <el-carousel-item v-for="item in 4" :key="item">
      <h3>{{ item }}</h3>
    </el-carousel-item>
  </el-carousel>
</div>`,
        'select': `<div>
  <el-select v-model="value" placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
</div>`,
        'switch': `<div>
  <el-switch
    v-model="value"
    active-color="#13ce66"
    inactive-color="#ff4949">
  </el-switch>
</div>`,
        'progress': `<div>
  <el-progress :percentage="50"></el-progress>
  <el-progress :percentage="100" status="success"></el-progress>
  <el-progress :percentage="70" status="warning"></el-progress>
  <el-progress :percentage="50" status="exception"></el-progress>
</div>`,
'alert': `<div>
  <el-alert
    title="成功提示的文案"
    type="success">
  </el-alert>
  <el-alert
    title="消息提示的文案"
    type="info">
  </el-alert>
  <el-alert
    title="警告提示的文案"
    type="warning">
  </el-alert>
  <el-alert
    title="错误提示的文案"
    type="error">
  </el-alert>
</div>`
        },
        'react': {
          'button': `import React from 'react';
import { Button } from 'antd';

const ButtonDemo = () => {
  return (
    <div>
      <Button type="primary">Primary Button</Button>
      <Button>Default Button</Button>
      <Button type="dashed">Dashed Button</Button>
      <Button type="link">Link Button</Button>
      <Button type="text">Text Button</Button>
    </div>
  );
};

export default ButtonDemo;`,
          'input': `import React, { useState } from 'react';
import { Input } from 'antd';

const InputDemo = () => {
  const [inputValue, setInputValue] = useState('');
  const [password, setPassword] = useState('');

  return (
    <div>
      <Input 
        placeholder="Basic usage" 
        value={inputValue} 
        onChange={(e) => setInputValue(e.target.value)} 
      />
      <br />
      <br />
      <Input.Password 
        placeholder="input password" 
        value={password} 
        onChange={(e) => setPassword(e.target.value)} 
      />
    </div>
  );
};

export default InputDemo;`,
          'table': `import React from 'react';
import { Table } from 'antd';

const columns = [
  {
    title: 'Name',
    dataIndex: 'name',
    filters: [
      {
        text: 'Joe',
        value: 'Joe',
      },
      {
        text: 'Jim',
        value: 'Jim',
      },
    ],
    onFilter: (value, record) => record.name.indexOf(value) === 0,
    sorter: (a, b) => a.name.length - b.name.length,
    sortDirections: ['descend'],
  },
  {
    title: 'Age',
    dataIndex: 'age',
    defaultSortOrder: 'descend',
    sorter: (a, b) => a.age - b.age,
  },
  {
    title: 'Address',
    dataIndex: 'address',
    filters: [
      {
        text: 'London',
        value: 'London',
      },
      {
        text: 'New York',
        value: 'New York',
      },
    ],
    onFilter: (value, record) => record.address.indexOf(value) === 0,
  },
];

const data = [
  {
    key: '1',
    name: 'John Brown',
    age: 32,
    address: 'New York No. 1 Lake Park',
  },
  {
    key: '2',
    name: 'Jim Green',
    age: 42,
    address: 'London No. 1 Lake Park',
  },
  {
    key: '3',
    name: 'Joe Black',
    age: 32,
    address: 'Sidney No. 1 Lake Park',
  },
  {
    key: '4',
    name: 'Jim Red',
    age: 32,
    address: 'London No. 2 Lake Park',
  },
];

const TableDemo = () => {
  return (
    <Table columns={columns} dataSource={data} />
  );
};

export default TableDemo;`,
          'form': `import React, { useState } from 'react';
import { Form, Input, Button, Select } from 'antd';

const { Option } = Select;

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
};

const tailLayout = {
  wrapperCol: { offset: 8, span: 16 },
};

const FormDemo = () => {
  const [form] = Form.useForm();
  const [formData, setFormData] = useState({});

  const onFinish = (values) => {
    console.log(values);
    setFormData(values);
  };

  const onReset = () => {
    form.resetFields();
  };

  return (
    <Form {...layout} form={form} name="control-hooks" onFinish={onFinish}>
      <Form.Item name="name" label="Name" rules={[{ required: true }]}>
        <Input />
      </Form.Item>
      <Form.Item name="gender" label="Gender" rules={[{ required: true }]}>
        <Select placeholder="Select a option and change input text above" allowClear>
          <Option value="male">male</Option>
          <Option value="female">female</Option>
          <Option value="other">other</Option>
        </Select>
      </Form.Item>
      <Form.Item {...tailLayout}>
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
        <Button htmlType="button" onClick={onReset}>
          Reset
        </Button>
      </Form.Item>
    </Form>
  );
};

export default FormDemo;`,
          'card': `import React from 'react';
import { Card } from 'antd';

const { Meta } = Card;

const CardDemo = () => {
  return (
    <Card
      hoverable
      style={{ width: 240 }}
      cover={<img alt="example" src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png" />}
    >
      <Meta title="Europe Street beat" description="www.instagram.com" />
    </Card>
  );
};

export default CardDemo;`,
          'modal': `import React, { useState } from 'react';
import { Modal, Button } from 'antd';

const ModalDemo = () => {
  const [visible, setVisible] = useState(false);

  const showModal = () => {
    setVisible(true);
  };

  const handleOk = () => {
    setVisible(false);
  };

  const handleCancel = () => {
    setVisible(false);
  };

  return (
    <>
      <Button type="primary" onClick={showModal}>
        Open Modal
      </Button>
      <Modal
        title="Basic Modal"
        visible={visible}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <p>Some contents...</p>
        <p>Some contents...</p>
        <p>Some contents...</p>
      </Modal>
    </>
  );
};

export default ModalDemo;`,
          'nav': `import React, { useState } from 'react';
import { Menu } from 'antd';
import { MailOutlined, AppstoreOutlined, SettingOutlined } from '@ant-design/icons';

const { SubMenu } = Menu;

const NavDemo = () => {
  const [current, setCurrent] = useState('mail');

  const handleClick = (e) => {
    console.log('click ', e);
    setCurrent(e.key);
  };

  return (
    <Menu onClick={handleClick} selectedKeys={[current]} mode="horizontal">
      <Menu.Item key="mail" icon={<MailOutlined />}>
        Navigation One
      </Menu.Item>
      <Menu.Item key="app" disabled icon={<AppstoreOutlined />}>
        Navigation Two
      </Menu.Item>
      <SubMenu key="SubMenu" icon={<SettingOutlined />} title="Navigation Three - Submenu">
        <Menu.ItemGroup title="Item 1">
          <Menu.Item key="setting:1">Option 1</Menu.Item>
          <Menu.Item key="setting:2">Option 2</Menu.Item>
        </Menu.ItemGroup>
        <Menu.ItemGroup title="Item 2">
          <Menu.Item key="setting:3">Option 3</Menu.Item>
          <Menu.Item key="setting:4">Option 4</Menu.Item>
        </Menu.ItemGroup>
      </SubMenu>
      <Menu.Item key="alipay">
        <a href="https://ant.design" target="_blank" rel="noopener noreferrer">
          Navigation Four - Link
        </a>
      </Menu.Item>
    </Menu>
  );
};

export default NavDemo;`,
          'carousel': `import React from 'react';
import { Carousel } from 'antd';

const contentStyle = {
  height: '160px',
  color: '#fff',
  lineHeight: '160px',
  textAlign: 'center',
  background: '#364d79',
};

const CarouselDemo = () => {
  const onChange = (currentSlide) => {
    console.log(currentSlide);
  };

  return (
    <Carousel afterChange={onChange}>
      <div>
        <h1 style={contentStyle}>1</h1>
      </div>
      <div>
        <h1 style={contentStyle}>2</h1>
      </div>
      <div>
        <h1 style={contentStyle}>3</h1>
      </div>
      <div>
        <h1 style={contentStyle}>4</h1>
      </div>
    </Carousel>
  );
};

export default CarouselDemo;`,
          'select': `import React, { useState } from 'react';
import { Select } from 'antd';

const { Option } = Select;

const SelectDemo = () => {
  const [value, setValue] = useState('');

  const handleChange = (value) => {
    setValue(value);
  };

  return (
    <Select
      defaultValue="选项1"
      style={{ width: 120 }}
      onChange={handleChange}
      value={value}
    >
      <Option value="选项1">黄金糕</Option>
      <Option value="选项2">双皮奶</Option>
      <Option value="选项3">蚵仔煎</Option>
      <Option value="选项4">龙须面</Option>
      <Option value="选项5">北京烤鸭</Option>
    </Select>
  );
};

export default SelectDemo;`,
          'switch': `import React, { useState } from 'react';
import { Switch } from 'antd';

const SwitchDemo = () => {
  const [checked, setChecked] = useState(true);

  const onChange = (checked) => {
    setChecked(checked);
  };

  return (
    <Switch checked={checked} onChange={onChange} />
  );
};

export default SwitchDemo;`,
          'progress': `import React from 'react';
import { Progress } from 'antd';

const ProgressDemo = () => {
  return (
    <div>
      <Progress percent={50} />
      <Progress percent={100} status="success" />
      <Progress percent={70} status="warning" />
      <Progress percent={50} status="exception" />
    </div>
  );
};

export default ProgressDemo;`,
          'alert': `import React from 'react';
import { Alert } from 'antd';

const AlertDemo = () => {
  return (
    <div>
      <Alert message="Success Text" type="success" />
      <Alert message="Info Text" type="info" />
      <Alert message="Warning Text" type="warning" />
      <Alert message="Error Text" type="error" />
    </div>
  );
};

export default AlertDemo;`
        },
        'angular': {
          'button': `import { Component } from '@angular/core';

@Component({
  selector: 'app-button-demo',
  template: \`
    <button mat-button>Basic</button>
    <button mat-raised-button color="primary">Primary</button>
    <button mat-raised-button color="accent">Accent</button>
    <button mat-raised-button color="warn">Warn</button>
    <button mat-raised-button disabled>Disabled</button>
  \`
})
export class ButtonDemoComponent {}`,
          'input': `import { Component } from '@angular/core';

@Component({
  selector: 'app-input-demo',
  template: \`
    <mat-form-field appearance="fill">
      <mat-label>Basic input</mat-label>
      <input matInput placeholder="Ex. Pizza">
    </mat-form-field>
    
    <mat-form-field appearance="fill">
      <mat-label>Password</mat-label>
      <input matInput [type]="'password'">
    </mat-form-field>
  \`
})
export class InputDemoComponent {}`,
          'table': `import { Component } from '@angular/core';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'}
];

@Component({
  selector: 'app-table-demo',
  template: \`
    <table mat-table [dataSource]="dataSource" class="mat-elevation-z8">
      <ng-container matColumnDef="position">
        <th mat-header-cell *matHeaderCellDef> No. </th>
        <td mat-cell *matCellDef="let element"> {{element.position}} </td>
      </ng-container>

      <ng-container matColumnDef="name">
        <th mat-header-cell *matHeaderCellDef> Name </th>
        <td mat-cell *matCellDef="let element"> {{element.name}} </td>
      </ng-container>

      <ng-container matColumnDef="weight">
        <th mat-header-cell *matHeaderCellDef> Weight </th>
        <td mat-cell *matCellDef="let element"> {{element.weight}} </td>
      </ng-container>

      <ng-container matColumnDef="symbol">
        <th mat-header-cell *matHeaderCellDef> Symbol </th>
        <td mat-cell *matCellDef="let element"> {{element.symbol}} </td>
      </ng-container>

      <tr mat-header-row *matHeaderRowDef="displayedColumns"></tr>
      <tr mat-row *matRowDef="let row; columns: displayedColumns;"></tr>
    </table>
  \`
})
export class TableDemoComponent {
  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSource = ELEMENT_DATA;
}`,
          'form': `import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-form-demo',
  template: \`
    <form [formGroup]="addressForm" novalidate (ngSubmit)="onSubmit()">
      <mat-form-field class="full-width">
        <input matInput placeholder="Company (disabled)" formControlName="company" disabled>
      </mat-form-field>

      <mat-form-field class="full-width">
        <input matInput placeholder="First name" formControlName="firstName" required>
        <mat-error *ngIf="addressForm.get('firstName')?.invalid">{{getErrorMessage()}}</mat-error>
      </mat-form-field>

      <mat-form-field class="full-width">
        <input matInput placeholder="Last name" formControlName="lastName" required>
      </mat-form-field>

      <div class="button-row">
        <button type="submit" mat-button color="primary">Submit</button>
      </div>
    </form>
  \`,
  styles: [\`
    .full-width {
      width: 100%;
    }
    
    .button-row {
      margin-top: 10px;
    }
  \`]
})
export class FormDemoComponent {
  addressForm = this.fb.group({
    company: null,
    firstName: [null, Validators.required],
    lastName: [null, Validators.required]
  });

  constructor(private fb: FormBuilder) {}

  onSubmit(): void {
    console.log(this.addressForm.value);
  }
  
  getErrorMessage() {
    if (this.addressForm.get('firstName')?.hasError('required')) {
      return 'You must enter a value';
    }
    return '';
  }
}`,
          'card': `import { Component } from '@angular/core';

@Component({
  selector: 'app-card-demo',
  template: \`
    <mat-card class="example-card">
      <mat-card-header>
        <div mat-card-avatar class="example-header-image"></div>
        <mat-card-title>Shiba Inu</mat-card-title>
        <mat-card-subtitle>Dog Breed</mat-card-subtitle>
      </mat-card-header>
      <img mat-card-image src="https://material.angular.io/assets/img/examples/shiba2.jpg" alt="Photo of a Shiba Inu">
      <mat-card-content>
        <p>
          The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan.
          A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally
          bred for hunting.
        </p>
      </mat-card-content>
      <mat-card-actions>
        <button mat-button>LIKE</button>
        <button mat-button>SHARE</button>
      </mat-card-actions>
    </mat-card>
  \`,
  styles: [\`
    .example-card {
      max-width: 400px;
    }

    .example-header-image {
      background-image: url('https://material.angular.io/assets/img/examples/shiba1.jpg');
      background-size: cover;
    }
  \`]
})
export class CardDemoComponent {}`,
          'modal': `import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-modal-demo',
  template: \`
    <button mat-raised-button (click)="openDialog()">Open dialog</button>
  \`
})
export class ModalDemoComponent {
  constructor(public dialog: MatDialog) {}

  openDialog() {
    const dialogRef = this.dialog.open(DialogContentExampleDialog);

    dialogRef.afterClosed().subscribe(result => {
      console.log(\`Dialog result: \${result}\`);
    });
  }
}

@Component({
  selector: 'dialog-content-example-dialog',
  template: \`
    <h1 mat-dialog-title>Dialog</h1>
    <div mat-dialog-content>
      <p>What would you like to do?</p>
    </div>
    <div mat-dialog-actions>
      <button mat-button mat-dialog-close>Cancel</button>
      <button mat-button [mat-dialog-close]="true" cdkFocusInitial>Ok</button>
    </div>
  \`,
})
export class DialogContentExampleDialog {}`,
          'nav': `import { Component } from '@angular/core';

@Component({
  selector: 'app-nav-demo',
  template: \`
    <mat-tab-group>
      <mat-tab label="First"> Content 1 </mat-tab>
      <mat-tab label="Second"> Content 2 </mat-tab>
      <mat-tab label="Third"> Content 3 </mat-tab>
    </mat-tab-group>
  \`
})
export class NavDemoComponent {}`,
          'carousel': `import { Component } from '@angular/core';

@Component({
  selector: 'app-carousel-demo',
  template: \`
    <div class="carousel-container">
      <div class="carousel-slide" *ngFor="let slide of slides; let i = index" [class.active]="i === currentIndex">
        <h2>{{ slide.title }}</h2>
        <p>{{ slide.content }}</p>
      </div>
    </div>
    
    <div class="carousel-controls">
      <button (click)="prev()" mat-button>Prev</button>
      <button (click)="next()" mat-button>Next</button>
    </div>
  \`,
  styles: [\`
    .carousel-container {
      position: relative;
      height: 200px;
      overflow: hidden;
    }
    
    .carousel-slide {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      background-color: #f0f0f0;
      transition: transform 0.3s ease;
      transform: translateX(100%);
    }
    
    .carousel-slide.active {
      transform: translateX(0);
    }
  \`]
})
export class CarouselDemoComponent {
  currentIndex = 0;
  slides = [
    { title: 'Slide 1', content: 'This is the first slide' },
    { title: 'Slide 2', content: 'This is the second slide' },
    { title: 'Slide 3', content: 'This is the third slide' }
  ];
  
  next() {
    this.currentIndex = (this.currentIndex + 1) % this.slides.length;
  }
  
  prev() {
    this.currentIndex = this.currentIndex === 0 ? this.slides.length - 1 : this.currentIndex - 1;
  }
}`,
          'select': `import { Component } from '@angular/core';

@Component({
  selector: 'app-select-demo',
  template: \`
    <mat-form-field appearance="fill">
      <mat-label>Select an option</mat-label>
      <mat-select [(value)]="selectedValue">
        <mat-option value="option1">黄金糕</mat-option>
        <mat-option value="option2">双皮奶</mat-option>
        <mat-option value="option3">蚵仔煎</mat-option>
        <mat-option value="option4">龙须面</mat-option>
        <mat-option value="option5">北京烤鸭</mat-option>
      </mat-select>
    </mat-form-field>
  \`
})
export class SelectDemoComponent {
  selectedValue: string = '';
}`,
          'switch': `import { Component } from '@angular/core';

@Component({
  selector: 'app-switch-demo',
  template: \`
    <mat-slide-toggle [(ngModel)]="isToggleOn">Toggle me!</mat-slide-toggle>
  \`
})
export class SwitchDemoComponent {
  isToggleOn: boolean = true;
}`,
          'progress': `import { Component } from '@angular/core';

@Component({
  selector: 'app-progress-demo',
  template: \`
    <mat-progress-bar mode="determinate" [value]="50"></mat-progress-bar>
    <mat-progress-bar mode="buffer" [value]="50" [bufferValue]="70"></mat-progress-bar>
    <mat-progress-spinner mode="determinate" [value]="50"></mat-progress-spinner>
  \`
})
export class ProgressDemoComponent {}`,
          'alert': `import { Component } from '@angular/core';

@Component({
  selector: 'app-alert-demo',
  template: \`
    <div class="alerts-container">
      <mat-card class="success-alert">
        <mat-card-content>
          <mat-icon>check_circle</mat-icon>
          <span>Success message</span>
        </mat-card-content>
      </mat-card>
      
      <mat-card class="info-alert">
        <mat-card-content>
          <mat-icon>info</mat-icon>
          <span>Info message</span>
        </mat-card-content>
      </mat-card>
      
      <mat-card class="warning-alert">
        <mat-card-content>
          <mat-icon>warning</mat-icon>
          <span>Warning message</span>
        </mat-card-content>
      </mat-card>
      
      <mat-card class="error-alert">
        <mat-card-content>
          <mat-icon>error</mat-icon>
          <span>Error message</span>
        </mat-card-content>
      </mat-card>
    </div>
  \`,
  styles: [\`
    .alerts-container {
      display: flex;
      flex-direction: column;
      gap: 10px;
    }
    
    .mat-card {
      display: flex;
      align-items: center;
      padding: 10px;
    }
    
    .mat-card-content {
      display: flex;
      align-items: center;
      gap: 10px;
      margin: 0;
    }
    
    .success-alert {
      background-color: #e8f5e9;
      color: #2e7d32;
    }
    
    .info-alert {
      background-color: #e3f2fd;
      color: #1565c0;
    }
    
    .warning-alert {
      background-color: #fff8e1;
      color: #f57f17;
    }
    
    .error-alert {
      background-color: #ffebee;
      color: #c62828;
    }
  \`]
})
export class AlertDemoComponent {}`
        },
        'flutter': {
          'button': `import 'package:flutter/material.dart';

class ButtonDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Button Demo')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
              onPressed: () {
                // Respond to button press
              },
              child: Text('ElevatedButton'),
            ),
            TextButton(
              onPressed: () {
                // Respond to button press
              },
              child: Text('TextButton'),
            ),
            OutlinedButton(
              onPressed: () {
                // Respond to button press
              },
              child: Text('OutlinedButton'),
            ),
          ],
        ),
      ),
    );
  }
}`,
          'input': `import 'package:flutter/material.dart';

class InputDemo extends StatefulWidget {
  @override
  _InputDemoState createState() => _InputDemoState();
}

class _InputDemoState extends State<InputDemo> {
  final TextEditingController _controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Input Demo')),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          children: <Widget>[
            TextField(
              controller: _controller,
              decoration: InputDecoration(
                labelText: 'Enter text',
                hintText: 'Type something...',
              ),
            ),
            SizedBox(height: 20),
            TextField(
              obscureText: true,
              decoration: InputDecoration(
                labelText: 'Password',
                hintText: 'Enter password',
              ),
            ),
          ],
        ),
      ),
    );
  }
}`,
          'table': `import 'package:flutter/material.dart';

class TableDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Table Demo')),
      body: SingleChildScrollView(
        child: DataTable(
          columns: const <DataColumn>[
            DataColumn(label: Text('Name')),
            DataColumn(label: Text('Age')),
            DataColumn(label: Text('Role')),
          ],
          rows: const <DataRow>[
            DataRow(
              cells: <DataCell>[
                DataCell(Text('John')),
                DataCell(Text('30')),
                DataCell(Text('Developer')),
              ],
            ),
            DataRow(
              cells: <DataCell>[
                DataCell(Text('Jane')),
                DataCell(Text('25')),
                DataCell(Text('Designer')),
              ],
            ),
          ],
        ),
      ),
    );
  }
}`,
          'form': `import 'package:flutter/material.dart';

class FormDemo extends StatefulWidget {
  @override
  _FormDemoState createState() => _FormDemoState();
}

class _FormDemoState extends State<FormDemo> {
  final _formKey = GlobalKey<FormState>();
  String _name = '';
  String _email = '';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Form Demo')),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: Column(
            children: <Widget>[
              TextFormField(
                decoration: InputDecoration(labelText: 'Name'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter your name';
                  }
                  return null;
                },
                onSaved: (value) {
                  _name = value!;
                },
              ),
              TextFormField(
                decoration: InputDecoration(labelText: 'Email'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter your email';
                  }
                  return null;
                },
                onSaved: (value) {
                  _email = value!;
                },
              ),
              SizedBox(height: 20),
              ElevatedButton(
                onPressed: () {
                  if (_formKey.currentState!.validate()) {
                    _formKey.currentState!.save();
                    ScaffoldMessenger.of(context).showSnackBar(
                      SnackBar(content: Text('Processing Data')),
                    );
                  }
                },
                child: Text('Submit'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}`,
          'card': `import 'package:flutter/material.dart';

class CardDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Card Demo')),
      body: Center(
        child: Card(
          child: Padding(
            padding: EdgeInsets.all(16.0),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: <Widget>[
                const ListTile(
                  leading: Icon(Icons.album),
                  title: Text('The Enchanted Nightingale'),
                  subtitle: Text('Music by Julie Gable. Lyrics by Sidney Stein.'),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: <Widget>[
                    TextButton(
                      child: const Text('BUY TICKETS'),
                      onPressed: () {/* ... */},
                    ),
                    SizedBox(width: 8),
                    TextButton(
                      child: const Text('LISTEN'),
                      onPressed: () {/* ... */},
                    ),
                    SizedBox(width: 8),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}`,
          'modal': `import 'package:flutter/material.dart';

class ModalDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Modal Demo')),
      body: Center(
        child: ElevatedButton(
          child: Text('Show Modal'),
          onPressed: () {
            showDialog(
              context: context,
              builder: (BuildContext context) {
                return AlertDialog(
                  title: Text('Alert'),
                  content: Text('This is an alert dialog.'),
                  actions: <Widget>[
                    TextButton(
                      child: Text('Close'),
                      onPressed: () {
                        Navigator.of(context).pop();
                      },
                    ),
                  ],
                );
              },
            );
          },
        ),
      ),
    );
  }
}`,
          'nav': `import 'package:flutter/material.dart';

class NavDemo extends StatefulWidget {
  @override
  _NavDemoState createState() => _NavDemoState();
}

class _NavDemoState extends State<NavDemo> {
  int _selectedIndex = 0;

  static const List<Widget> _widgetOptions = <Widget>[
    Text('Home'),
    Text('Search'),
    Text('Profile'),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Navigation Demo')),
      body: Center(
        child: _widgetOptions.elementAt(_selectedIndex),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Home',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.search),
            label: 'Search',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person),
            label: 'Profile',
          ),
        ],
        currentIndex: _selectedIndex,
        selectedItemColor: Colors.blue,
        onTap: _onItemTapped,
      ),
    );
  }
}`,
          'carousel': `import 'package:flutter/material.dart';

class CarouselDemo extends StatefulWidget {
  @override
  _CarouselDemoState createState() => _CarouselDemoState();
}

class _CarouselDemoState extends State<CarouselDemo> {
  PageController _pageController = PageController();
  int _currentPage = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Carousel Demo')),
      body: Column(
        children: <Widget>[
          Container(
            height: 200,
            child: PageView(
              controller: _pageController,
              children: <Widget>[
                Container(color: Colors.red, child: Center(child: Text('Page 1'))),
                Container(color: Colors.green, child: Center(child: Text('Page 2'))),
                Container(color: Colors.blue, child: Center(child: Text('Page 3'))),
              ],
              onPageChanged: (int page) {
                setState(() {
                  _currentPage = page;
                });
              },
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: List.generate(3, (index) {
              return Container(
                width: 8,
                height: 8,
                margin: EdgeInsets.symmetric(horizontal: 2),
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  color: _currentPage == index ? Colors.blue : Colors.grey,
                ),
              );
            }),
          ),
        ],
      ),
    );
  }
}`,
          'select': `import 'package:flutter/material.dart';

class SelectDemo extends StatefulWidget {
  @override
  _SelectDemoState createState() => _SelectDemoState();
}

class _SelectDemoState extends State<SelectDemo> {
  String? _selectedValue;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Select Demo')),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: DropdownButton<String>(
          value: _selectedValue,
          hint: Text('请选择'),
          onChanged: (String? newValue) {
            setState(() {
              _selectedValue = newValue;
            });
          },
          items: <String>['黄金糕', '双皮奶', '蚵仔煎', '龙须面', '北京烤鸭']
              .map<DropdownMenuItem<String>>((String value) {
            return DropdownMenuItem<String>(
              value: value,
              child: Text(value),
            );
          }).toList(),
        ),
      ),
    );
  }
}`,
          'switch': `import 'package:flutter/material.dart';

class SwitchDemo extends StatefulWidget {
  @override
  _SwitchDemoState createState() => _SwitchDemoState();
}

class _SwitchDemoState extends State<SwitchDemo> {
  bool _switchValue = true;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Switch Demo')),
      body: Center(
        child: Switch(
          value: _switchValue,
          onChanged: (bool value) {
            setState(() {
              _switchValue = value;
            });
          },
          activeTrackColor: Colors.lightGreenAccent,
          activeColor: Colors.green,
        ),
      ),
    );
  }
}`,
          'progress': `import 'package:flutter/material.dart';

class ProgressDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Progress Demo')),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          children: <Widget>[
            LinearProgressIndicator(),
            SizedBox(height: 20),
            LinearProgressIndicator(value: 0.5),
            SizedBox(height: 20),
            CircularProgressIndicator(),
            SizedBox(height: 20),
            CircularProgressIndicator(value: 0.7),
          ],
        ),
      ),
    );
  }
}`,
          'alert': `import 'package:flutter/material.dart';

class AlertDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Alert Demo')),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          children: <Widget>[
            Card(
              color: Colors.green[100],
              child: Padding(
                padding: EdgeInsets.all(16.0),
                child: Row(
                  children: [
                    Icon(Icons.check_circle, color: Colors.green),
                    SizedBox(width: 10),
                    Text('Success message'),
                  ],
                ),
              ),
            ),
            SizedBox(height: 10),
            Card(
              color: Colors.blue[100],
              child: Padding(
                padding: EdgeInsets.all(16.0),
                child: Row(
                  children: [
                    Icon(Icons.info, color: Colors.blue),
                    SizedBox(width: 10),
                    Text('Info message'),
                  ],
                ),
              ),
            ),
            SizedBox(height: 10),
            Card(
              color: Colors.orange[100],
              child: Padding(
                padding: EdgeInsets.all(16.0),
                child: Row(
                  children: [
                    Icon(Icons.warning, color: Colors.orange),
                    SizedBox(width: 10),
                    Text('Warning message'),
                  ],
                ),
              ),
            ),
            SizedBox(height: 10),
            Card(
              color: Colors.red[100],
              child: Padding(
                padding: EdgeInsets.all(16.0),
                child: Row(
                  children: [
                    Icon(Icons.error, color: Colors.red),
                    SizedBox(width: 10),
                    Text('Error message'),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}`
        }
      }
      
      // 获取对应的源码
      this.sourceCode = sourceCodes[this.selectedFramework]?.[this.selectedComponent] || '// 未找到对应的源码';
    },
    copyCode() {
      this.copying = true
      // 模拟复制操作
      setTimeout(() => {
        this.copying = false
        this.$message.success('代码已复制到剪贴板')
      }, 1000)
    },
    downloadCode() {
      this.$message.success('代码下载开始')
    }
  }
}
</script>

<style scoped>
.source-code-viewer {
  padding: 20px;
}

.code-card,
.info-card,
.actions-card {
  margin-bottom: 20px;
}

.code-header {
  display: flex;
  gap: 20px;
  margin-bottom: 20px;
}

.code-content {
  background-color: #f5f5f5;
  padding: 15px;
  border-radius: 4px;
  max-height: 500px;
  overflow: auto;
}

pre {
  margin: 0;
  background: transparent;
}

.component-info h4 {
  margin: 15px 0 5px 0;
  font-size: 14px;
}

.component-info ul {
  padding-left: 20px;
  margin: 5px 0;
}

.component-info p {
  margin: 5px 0;
}
</style>