package com.wk.job;

import com.wk.constants.ThreadContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
@Slf4j
public class ReceiveConnect extends Thread {

    public static final Integer PORT=19000;

    private volatile boolean flag = true;

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (flag){
                Socket socket = serverSocket.accept();
                ServerSimulator simulator = new ServerSimulator(socket);
                ThreadContainer.threads.put("test",simulator);
                simulator.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stopRun(){
        log.info("stop receive thread.");
        this.flag = false;
    }
}
