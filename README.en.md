# gradle-docker-plugin

#### Description
 This plugin use local machine shell to execute docker command to
 complete common tasks like build docker image, publish to local or remore registry, deploy to local or remote registry

#### Installation

1. add plugin to build.gradle
2. add jar manifest configuration
---
jar { \
    manifest {\
    attributes 'Main-Class': 'com.carbon.auth.AuthApplication'\
    attributes 'Class-Path': configurations.runtimeClasspath.files.collect { './libs/'+it.getName() }.join(' ')\
    }\
}
---

#### Instructions

1.  run gradle cmd  ./gradlew module:buildImage
