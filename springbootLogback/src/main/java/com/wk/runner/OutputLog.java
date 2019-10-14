package com.wk.runner;

import com.wk.config.GetApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class OutputLog implements ApplicationRunner {

    @Autowired
    private GetApplicationContext applicationContext;
    boolean flag = true;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    Map<String, String> env = System.getenv();
                    Set<Map.Entry<String, String>> entries = env.entrySet();
                    for (Map.Entry<String, String> entry : entries) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        log.info("key = "+key +", value = "+value);
                    }
                    String hostname = applicationContext.getApplicationContext().getEnvironment().getProperty("hostname");
                    System.out.println("**********************************************");
                    log.info("hostname = "+hostname);
                    try {
                        Thread.sleep(500);
                        flag = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
