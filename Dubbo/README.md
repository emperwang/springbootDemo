# 说明文档

首先springboot整合dubbo由三种方式：

1. 使用application.properties文件配置加部分注解
2. 使用原生的xml文件进行配置
3. 使用java bean进行配置

DubboAPI：是为dubbo提供bean和暴露的接口类的模块

DubboConsumer：是使用application.properties(.yml)配置的一个dubbo消费者

DubboConsumer-bean：使用java-bean进行配置的消费者

DubboConsumer-xml：使用xml进行配置的消费者

DubboProvider：是使用application.properties(.yml)配置的一个dubbo服务提供者

DubboProvider-bean：使用java-bean进行配置的提供者

DubboProvider-xml：使用xml进行配置的提供者

DubboConsumer-xml-hystrix：使用hystrix实现容错功能。因为hystrix和springboot2.04版本整合有问题，所以按照1.5.10.RELEASE 这个springcloud-Edgware.SR2版本，使用了hystrix1.4.3版本

DubboProvider-xml-hystrix：使用hystrix实现容错功能。因为hystrix和springboot2.04版本整合有问题，所以按照1.5.10.RELEASE 这个springcloud-Edgware.SR2版本，使用了hystrix1.4.3版本

因为使用application配置文件需要配合注解才能，而且有些功能注解没有实现，如对方法进行配置。



