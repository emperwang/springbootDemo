package com.wk.service;

import com.wk.service.impl.MockServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author: Sparks
 * @Date: 2021/6/18 21:21
 * @Description
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMockServiceImpl {

    @Mock
    private MockServiceImpl mockService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMsg(){
        Mockito.when(mockService.getMsg()).thenReturn("mockMsg");
        Assert.assertEquals("testGetMsg error","mockMsg",mockService.getMsg());
    }

    @Test
    public void testNoReturnMethod(){
        // 当mock一个method时，默认就是 doNothing
    }

}
