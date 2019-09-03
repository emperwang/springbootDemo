package com.wk.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  实现自动http自动跳转到https
 */
// @Profile("dev")
@Configuration
public class TomcatHttpConfig {

    /**
     *  内置的servlet容器工厂为tomcat
     * @return
     */
    @Bean
    public ServletWebServerFactory servletContainer(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){

            @Override // confidential
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        // 添加配置信息，主要是http的配置信息
        tomcat.addAdditionalTomcatConnectors(redirectConnector());
        return tomcat;
    }

    /**
     *  创建一个https连接
     * @return
     */
    public Connector createSSLConnector(){
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocolHandler = (Http11NioProtocol) connector.getProtocolHandler();
        connector.setScheme("https");
        connector.setSecure(true);
        connector.setPort(9999);
        // 设置TLS/SSL密钥信息
        protocolHandler.setSSLEnabled(true);
        protocolHandler.setKeystoreFile("");
        protocolHandler.setKeystorePass("");

        protocolHandler.setKeyAlias("");
        protocolHandler.setKeyPass("");

        protocolHandler.setTruststoreFile("");
        protocolHandler.setTruststorePass("");

        return connector;
    }

    /**
     *  配置一个http连接信息,
     *  此是一个重定向连接
     * @return
     */
    public Connector redirectConnector(){
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8088);
        connector.setSecure(false);
        connector.setRedirectPort(9999);
        return connector;
    }
}
