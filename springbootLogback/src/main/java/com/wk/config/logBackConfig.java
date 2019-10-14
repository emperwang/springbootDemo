package com.wk.config;

import ch.qos.logback.core.PropertyDefinerBase;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *  可以使用此方法在logback中设置想获取的属性
 *  <define name="hostname" class="com.wk.config.logBackConfig" />
 */
@Slf4j
public class logBackConfig extends PropertyDefinerBase {
    @Override
    public String getPropertyValue() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String hostName = localHost.getHostName();
            log.info("get hostname is :{}",hostName);
            return hostName;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
