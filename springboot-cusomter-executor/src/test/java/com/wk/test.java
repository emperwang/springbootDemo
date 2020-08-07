package com.wk;

import org.junit.Test;

public class test {

    @Test
    public void test1(){
        String s = String.format("name=%s", "sbootFactory");
        System.out.println(s);

        String sboot = String.format("name=%s-%d", "sboot", 1);
        System.out.println(sboot);
    }
}
