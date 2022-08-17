# gradle-mybatis-plugin

#### Description
Gradle plugin for mybatis to auto generate Entity Mppper classes

#### Installation

1. add plugin to build.gradle
```
//use easydao version
plugins {
    id "pub.techfun.mybatis.plugin.easydao" version "0.0.1"
}
```

#### Instructions

1. create default configuration file:  ./gradlew module:createConfigFile
2. modify configuration file
3. generate classes: ./gradlew module:generateFile