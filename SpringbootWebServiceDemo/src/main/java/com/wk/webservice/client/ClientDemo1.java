package com.wk.webservice.client;

import com.wk.webservice.entity.User;
import com.wk.webservice.service.UserService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * 调用webservice发布的服务
 */
public class ClientDemo1 {
    public static void main(String[] args) {
        //main1();
        main2();
    }

    /**
     * 调用方式2  动态调用接口
     */
    public static void main2(){
        JaxWsDynamicClientFactory jaxWsDynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = jaxWsDynamicClientFactory.createClient("http://10.4.2.21:8888/webservices/UserService?wsdl");
        try {
            Object[] getUsers = client.invoke("getUser");
            System.out.println("返回数据:"+getUsers[0].toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 代理类工厂方式，需要拿到对方的接口地址
     */
    public static void main1(){
        //接口地址
        String address = "http://10.4.2.21:8888/webservices/UserService?wsdl";
        //创建代理工厂
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        //设置代理地址
        jaxWsProxyFactoryBean.setAddress(address);
        //设置接口类型
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);
        //创建一个代理接口实现
        UserService userService = (UserService)jaxWsProxyFactoryBean.create();
        //调用接口  并打印数据
        User user = userService.getUser();
        System.out.println(user.toString());
    }
}
