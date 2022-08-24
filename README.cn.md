# gradle-mybatis-plugin

#### Description
通过Gradle插件自动生成Mybatis实体类和Mapper

#### Installation

1. 在build.gradle中添加插件
```
//使用默认版本
plugins {
    id "pub.techfun.mybatis.plugin.default" version "0.0.6"
}
```
```
//使用easydao库定制版本
plugins {
    id "pub.techfun.mybatis.plugin.easydao" version "0.0.6"
}
```

#### Instructions

1. 创建默认配置文件:  ./gradlew module:createConfigFile
2. 修改配置文件，生成的默认配置文件会在项目根目录下的generator文件夹
3. 生成类: ./gradlew module:generateFile