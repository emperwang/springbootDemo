package com.swagger.demo.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestfulApi(){
                    //文档类型
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // api信息
                .select()           //构建api选择器
                //api选择器选择api的包
                // .apis(RequestHandlerSelectors.basePackage("com.swagger.demo.controller"))
                // 选择类上有 ApiOperation注解的方法
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())            //api选择器选择包路径下任务api显示在文档中
                .build();  //创建文档
    }

    private ApiInfo apiInfo(){  //接口相关信息
        return new ApiInfoBuilder()
                .title("springboot 使用swagger2构建RESTFUL接口")
                .description("接口描述")
                .termsOfServiceUrl("termsOfServiceUrl")
                .version("1.0")
                .build();
                //.license("http://springfox.github.io/springfox/docs/current/")
                //.licenseUrl("http://springfox.github.io/springfox/docs/current/")
                //.build();
    }
}
