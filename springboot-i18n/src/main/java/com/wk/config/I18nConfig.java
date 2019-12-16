package com.wk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class I18nConfig implements WebMvcConfigurer{

    /**
     *  session 方式
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver resolver = new SessionLocaleResolver();

        // 设置默认区域
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }

    /**
     *  cookie 方式
     * @return
     */
    /*public LocaleResolver localeResolver2(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieName("localeCookie");
        resolver.setDefaultLocale(Locale.US);
        resolver.setCookieMaxAge(3600);
        return resolver;
    }*/

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
        changeInterceptor.setParamName("lang");
        return changeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
