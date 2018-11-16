package com.wk.webservice.config;

import com.wk.webservice.service.UserService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
//@ImportResource("classpath:META-INF/cxf/cxf.xml")
public class WebConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private Bus bus;

    /**
     * webservice 的默认访问路径时services
     * 这里修改为webservives
     * @return
     */
    @Bean
    public ServletRegistrationBean wsServleter() {
        return new ServletRegistrationBean(new CXFServlet(), "/webservices/*");
    }

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, userService);
        endpoint.publish("/UserService");   //这里的名字一定要和serviceName声明的名字一致s
        return endpoint;
    }
}
