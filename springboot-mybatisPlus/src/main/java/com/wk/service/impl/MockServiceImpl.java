package com.wk.service.impl;

import com.wk.service.MockService;

/**
 * @author: Sparks
 * @Date: 2021/6/18 21:19
 * @Description
 */
public class MockServiceImpl implements MockService {

    @Override
    public void noReturnMethod() {
        System.out.println("noReturnMethod running");
    }

    @Override
    public String getMsg() {
        System.out.println("getMsg running");
        return "msg";
    }

    @Override
    public void exceptionMethod() throws Exception {
        System.out.println("exceptionMethod running");
        throw new Exception("123");
    }
}
