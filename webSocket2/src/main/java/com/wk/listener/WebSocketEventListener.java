package com.wk.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.*;
@Slf4j
@Component
public class WebSocketEventListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
            if (event instanceof BrokerAvailabilityEvent){
                log.info("onApplicationEvent  BrokerAvailabilityEvent.........");
            }

            if (event instanceof SessionConnectEvent){
                log.info("onApplicationEvent  SessionConnectEvent.........");
            }

            if (event instanceof SessionConnectedEvent){
                log.info("onApplicationEvent  SessionConnectedEvent.........");
            }

            if (event instanceof SessionSubscribeEvent){
                log.info("onApplicationEvent  SessionSubscribeEvent.........");
            }

            if (event instanceof SessionUnsubscribeEvent){
                log.info("onApplicationEvent  SessionUnsubscribeEvent.........");
            }

            if (event instanceof SessionDisconnectEvent){
                log.info("onApplicationEvent  SessionDisconnectEvent.........");
            }
    }
}
