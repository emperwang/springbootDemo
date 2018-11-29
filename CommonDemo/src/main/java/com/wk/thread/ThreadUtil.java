package com.wk.thread;

public class ThreadUtil {

    public static ThreadGroup currentGroup(){
        return Thread.currentThread().getThreadGroup();
    }

    public static ThreadGroup createGroup(String groupName){
        return new ThreadGroup(groupName);
    }

    public static void runThread(ThreadGroup group,Runnable runnable,String name){
        new Thread(group,runnable,name).start();
    }

    public static void runThread(Runnable runnable,String name,boolean daemon){
        Thread thread = new Thread(runnable, name);
        thread.setDaemon(daemon);
        thread.start();
    }
    public static void runThread(Runnable runnable,String name){
        Thread thread = new Thread(runnable, name);
        thread.start();
    }

    public static SimpleThreadFactory createFactory(ThreadGroup group,String prefix){
        return new SimpleThreadFactory(group,prefix);
    }
}
