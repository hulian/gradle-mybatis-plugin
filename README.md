# gradle-mybatis-plugin

#### Description
Gradle plugin for mybatis to auto generate Entity Mppper classes

#### Installation

1. add plugin to build.gradle
```
//use default version
plugins {
    id "pub.techfun.mybatis.plugin.default" version "0.0.6"
}
```
```
//use easydao lib specific version
plugins {
    id "pub.techfun.mybatis.plugin.easydao" version "0.0.6"
}
```

#### Instructions

1. create default configuration file:  ./gradlew module:createDefaultConfig
2. modify configuration file in the folder 'generator' 
3. generate classes: ./gradlew module:generateFile