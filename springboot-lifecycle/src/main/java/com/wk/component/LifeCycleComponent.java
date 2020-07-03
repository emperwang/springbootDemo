package com.wk.component;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class LifeCycleComponent implements SmartLifecycle {
    boolean isRunning  = false;

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        System.out.println("----------------application LifeCycleComponent stop thread---------------");
        runnable.run();
        stop();
    }

    @Override
    public void start() {
        System.out.println("----------------application LifeCycleComponent running---------------");
    }

    @Override
    public void stop() {
        System.out.println("----------------application LifeCycleComponent stopping---------------");
        this.isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPhase() {
        return 0;
    }
}
