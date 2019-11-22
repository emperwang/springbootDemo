package com.wk.util;

import org.junit.Test;

public class CoderTest {

    @Test
    public void BASE64Test(){
        String s = com.wk.common.util.Coder.encryptionBASE64("admin".getBytes());
        System.out.println(s);
    }
}
