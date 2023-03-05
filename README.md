# 🎉Fly-Cloud

🎉🎉🔥 

### 🌈 介绍

微服务脚手架框架

### ⛱️ 核心依赖

| 依赖                   | 版本         |
|----------------------|------------|
| Nacos                | 2.1.1      |
| Spring Boot          | 2.6.6      |
| Spring Cloud         | 2021.0.5   |
| Spring Cloud Alibaba | 2021.0.4.0 |
| Mybatis              | 3.5.11     |
| pagehelper           | 5.3.2      |
| fastjson             | 2.0.21     |
| hutool               | 5.8.11     |


### 🚧 安装方式
* 基础依赖项目: 
```lua 
https://gitee.com/itdachen/fly-framework
```
* 克隆基础依赖项目
```lua 
git clone https://gitee.com/itdachen/fly-framework.git
```
* 进入依赖管理模块安装
```lua 
cd /fly-framework/fly-dependencies
mvn clean
mvn install
```
* 返回根目录安装基础依赖
```lua 
cd ..
mvn clean 
mvn install
```

### 🏭 模块说明

```lua
fly-cloud
├── fly-gateway -- 微服务网关
└── end
```