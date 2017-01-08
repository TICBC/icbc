## 这是一个MarkDown格式的ReadMe
### 前端环境配置过程
* 安装git
* 安装git客户端工具sourcetree
* 安装前置NodeJS 是因为我们要用到它的一个工具叫npm, npm是一个包管理工具, 配置文件对应package.json
* 安装bower 运行命令：npm install bower -g , bower是一个依赖管理工具, 跟npm类似,配置文件对应bower.json
** 我们用npm来管理开发用的工具，比如gulp；用bower管理前端依赖，比如bootstrap
* 全局安装gulp, bower install gulp -g

### 前端启动流程
* 在前端根目录运行 npm install, 这样npm会读取package.json下载相应的依赖
* 在前端根目录运行 bower install, 这样bower会读取bower.json下载相应的依赖, angularjs选择1.5.8
* 在前端根目录运行 gulp serve, 这样前端就跑起来了
** 可以了解一下gulp

## API测试工具安装
* 安装postman

## 前端开发流程
* index.html不要动
* in.html对应导航栏
* 后面可以接自己的html 比如 in.home  in.result
** 1. 添加3个文件 .html .scss .js
** 2. 在index.js里面将自己的js添加进去
*** 2.1 import ** from **;
*** 2.2 类似.controller('xxxeCtrl', xxxCtrl)
** 3. 配置路由 修改app/config/route.js
** 4. 给自己的模块添加一个跳转进去的地方 两种方式：在js里面通过类似$state.go("in.home"); 在html里面通过类似ui-sref="in.home"。

## 后端开发流程
* 安装JDK
* 下载Intellij IDEA Ultimate版本 不要下Community版本
* 下载gradle2.4: http://downloads.gradle.org/distributions/gradle-2.4-bin.zip
* 打开Intellij IDEA选择import双击项目根目录的build.gradle弹出导入对话框，选中use aoto import 选择使用本地gradle确定
