package com.wk.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebTotalInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.DefaultBlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcTotalConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: wk
 * @Date: 2020/10/27 16:01
 * @Description
 */
@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        addSentinelWebInterceptor(registry);
    }

    private void addSentinelWebInterceptor(InterceptorRegistry registry){
        // 创建 SentinelWebMvcConfig对象
        final SentinelWebMvcConfig config = new SentinelWebMvcConfig();
        // 是否包含请求方法. 即基于URL 创建的 资源,是否包含 method
        config.setHttpMethodSpecify(true);
        // 设置默认的 BlockException 处理器
        // config.setBlockExceptionHandler(new DefaultBlockExceptionHandler());
        // 添加 SentinelWebInterceptor 拦截器
        registry.addInterceptor(new SentinelWebInterceptor(config)).addPathPatterns("/**");
    }

    private void addSentinelTotalWebInterceptor(InterceptorRegistry registry){
        // 创建 SentinelWebMvcTotalConfig 对象
        final SentinelWebMvcTotalConfig totalConfig = new SentinelWebMvcTotalConfig();
        // 添加 SentinelWebTotalInterceptor拦截器
        registry.addInterceptor(new SentinelWebTotalInterceptor(totalConfig)).addPathPatterns("/**");
    }
}


















