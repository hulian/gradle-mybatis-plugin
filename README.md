# gradle-docker-plugin

#### 介绍
 Gradle Docker插件 直接通过Gradle插件进行Docker镜像打包，上传，发布

#### 软件架构
docker-plugin-cmd 直接通过本机上的docker命令行执行docker任务

#### 使用说明

1.  在build.gradle中引入插件
2.  执行gradle打包命令，如 gradlew project:buildImage
