# gradle-docker-plugin

#### 介绍
 Gradle Docker插件 直接通过Gradle插件进行Docker镜像打包，上传，发布

#### 软件架构
docker-plugin-cmd 直接通过本机上的docker命令行执行docker任务

###  软件安装
1. 本插件依赖本机命令行环境，先在本机上安装好docker和ssh客户端
2. 如果要通过插件部署项目到远程机，需在远程机配置ssh免密登录
3. 在build.gradle加入插件
```
plugins {
    id "pub.techfun.docker.plugin.cmd" version "0.0.1"
}
```
4. 添加jar manifest配置
```
jar { 
 manifest {
  attributes 'Main-Class': '你的MainClass'
  attributes 'Class-Path': configurations.runtimeClasspath.files.collect { './libs/'+it.getName() }.join(' ')
 }
}
```

#### 使用说明

1. 构建镜像: ./gradlew 模块名:buildImage
2. 发布到本地仓库: ./gradlew 模块名:pushImage
3. 发布到远程仓库，在命令行上加入自定义属性: ./gradlew 模块名:pushImage -PREGISTRY_HOST=registryhost:port
4. 部署到本机: ./gradlew 模块名:publishImage
5. 部署到远程机器: ./gradlew 模块名:publishImage -PDEPLOY_HOST=deployhost:port
6. 部署时添加java命令行参数: ./gradlew 模块名:publishImage -PARGS="--spring.profiles.active=dev"
7. 默认docker配置文件在 gradle-docker-plugin-cmd/src/main/resources/docker,\
如果想要覆盖此配置文件，在项目的根目录创建名为docker的文件夹，在文件夹内创建Dockerfile和entrypoint.sh文件