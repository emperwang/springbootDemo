package com.wk.service;

/**
 * @author: Sparks
 * @Date: 2021/6/18 21:18
 * @Description
 */
public interface MockService {

    void noReturnMethod();

    String getMsg();

    void exceptionMethod() throws Exception;
}
