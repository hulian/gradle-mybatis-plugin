# gradle-docker-plugin

#### Description
 This plugin use local machine shell to execute docker command to
 complete common tasks like build docker image, push to local or remore registry, deploy to local or remote host

#### Installation

1. this plugin depends on command in you local host, please install docker and ssh client first!
2. if you want to deploy image to remote host, please make sure that remote host can be login by ssh without password
3. add plugin to build.gradle
4. add jar manifest configuration
>jar {
>> manifest {
>>>  attributes 'Main-Class': 'YourMainClass'
>>>  attributes 'Class-Path': configurations.runtimeClasspath.files.collect { './libs/'+it.getName() }.join(' ')
>>}
>}

#### Instructions

1. build image:  ./gradlew module:buildImage
2. push to local registry: ./gradlew module:pushImage
3. push to remote registry: ./gradlew module:pushImage -PREGISTRY_HOST=registryhost:port 
4. deploy to local host: ./gradlew module:publishImage
5. deploy to remote host: ./gradlew module:publishImage -PDEPLOY_HOST=deployhost:port
6. deploy with args: ./gradlew module:publishImage -PARGS="--spring.profiles.active=dev"