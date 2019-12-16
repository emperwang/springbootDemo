package com.wk.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * descripiton:
 *
 * @author: wk
 * @time: 15:52 2019/12/16
 * @modifier:
 */
@Component
public class MessageSourceUtil {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String key){
        return this.getMessage(key,new Object[]{});
    }

    public String getMessage(String key, String defaultMessage){
        return this.getMessage(key,null, defaultMessage);
    }


    public String getMessage(String key, String defaultMessage, Locale locale){
        return this.getMessage(key,null, defaultMessage,locale);
    }

    public String getMessage(String key, Locale locale){
        return this.getMessage(key,null, "", locale);
    }

    public String getMessage(String key, Object[] args){
        return this.getMessage(key, args, "");
    }

    public String getMessage(String key, Object[] args, String defaultMessage){
        Locale locale = LocaleContextHolder.getLocale();
        return this.getMessage(key,args, defaultMessage, locale);
    }

    /**
     * descripiton:
     * @author: wk
     * @params: [key, args, defaultMessage, locale]
     *             key: 对应的key
     *             args: 占位符的值
     *             defaultMessage: 默认值
     *             local: 地区
     * @returns: java.lang.String   得到的value
     * @time: 15:57
     * @modifier:
     * @since:
     */
    public String getMessage(String key, Object[] args, String defaultMessage, Locale locale){
        return this.messageSource.getMessage(key,args,defaultMessage,locale);
    }
}
