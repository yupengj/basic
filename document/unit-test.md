[返回目录](../README.md)

###  单元测试
单元测试保证的软件的质量，对于一个web项目来的，你对外暴露的方法都应该由测试保证它的正确性，
对于测试粒度可以分为单元测试和集成测试，一般我们写的公用方法都会对应写一个单元测试，而系统
的web->service->data会写一个对应的集成测试。

### 单元测试注意点
1. 不要使用跨网络的资源，应该使用本地资源
1. 尽可能多的添加测试的场景
1. mock各种外部服务

### 一些成熟的mock框架
1. amqp: org.apache.qpid:qpid-broker:6.1.2
1. redis: com.github.kstyrc:embedded-redis:0.6
1. mongodb: de.flapdoodle.embed:de.flapdoodle.embed.mongo
1. database: com.h2database:h2
