# gradle-docker-plugin

#### Description
This plugin use local machine shell to execute docker command to
complete common tasks like build docker image, push to local or remore registry, deploy to local or remote host

#### Installation

1. this plugin depends on command in you local host, please install docker and ssh client first!
2. add plugin to build.gradle
3. add jar manifest configuration
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