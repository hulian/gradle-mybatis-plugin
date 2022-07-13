# gradle-docker-plugin

#### 介绍
 Gradle Docker插件 直接通过Gradle插件进行Docker镜像打包，上传，发布

#### 软件架构
docker-plugin-cmd 直接通过本机上的docker命令行执行docker任务

###  软件安装
1. 本插件依赖本机命令行环境，先在本机上安装好docker和ssh客户端
2. 如果要通过插件部署项目到远程机，需在远程机配置ssh免密登录
3. 在build.gradle加入插件
4. 添加jar manifest配置
>jar { 
>> manifest {
>>>  attributes 'Main-Class': '你的MainClass'
>>>  attributes 'Class-Path': configurations.runtimeClasspath.files.collect { './libs/'+it.getName() }.join(' ')
>>}
>}

#### 使用说明

1. 构建镜像: ./gradlew 模块名:buildImage
2. 发布到本地仓库: ./gradlew 模块名:pushImage
3. 发布到远程仓库，在命令行上加入自定义属性: ./gradlew 模块名:pushImage -PREGISTRY_HOST=registryhost:port
4. 部署到本机: ./gradlew 模块名:publishImage
5. 部署到远程机器: ./gradlew 模块名:publishImage -PDEPLOY_HOST=deployhost:port
