package com.wk.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  配置tomcat支持http请求
 *  此配置之后，springboot支持http和https两种协议
 */
@Configuration
public class TomcatSupportHttpConfig {
    /**
     *  此端口也可以在配置文件中进行配置
     */
    private int httpPort = 8888;

    @Bean
    public ServletWebServerFactory servletContainer(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        // 添加配置信息，主要是http的配置信息
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setSecure(false);
        connector.setPort(httpPort);
        connector.setScheme("http");
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }
}
