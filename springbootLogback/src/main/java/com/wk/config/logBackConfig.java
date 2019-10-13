package com.wk.config;

import ch.qos.logback.core.PropertyDefinerBase;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
