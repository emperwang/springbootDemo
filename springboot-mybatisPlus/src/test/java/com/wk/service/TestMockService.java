package com.wk.service;

import com.wk.service.impl.MockServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 * @author: Sparks
 * @Date: 2021/6/18 21:21
 * @Description
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMockService {

    @Mock
    private MockService mockService;

    @Mock
    private MockServiceImpl mockServiceimpl;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoAnswer(){
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return null;
            }
        }).when(mockService).getMsg();

        Assert.assertEquals(null,mockService.getMsg());
    }

    @Test(expected = Exception.class)
    public void testDoThrow() throws Exception {
        // 这里mock 抛出异常的method时, 此method签名必须也是抛出异常
        Mockito.doThrow(new Exception("mock exception")).when(mockService).exceptionMethod();
        mockService.exceptionMethod();
    }

    @Test
    public void testDoThrow2(){
        try{
            Mockito.doThrow(new Exception("mock exception")).when(mockService).exceptionMethod();
            mockService.exceptionMethod();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testSpy() throws Exception {
        // spy的对象只有部分才会进行mock的操作, 没有进行mock操作时,就会调用实际的方法
        MockService spy = Mockito.spy(mockService);
        Mockito.doNothing().when(spy).exceptionMethod();
        spy.exceptionMethod();
        spy.noReturnMethod();
    }

    @Test
    public void testDoNothing() throws Exception {
        // error: getMsg 方法签名没有异常抛出
        //Mockito.doNothing().doThrow(new Exception("mock doNothing1 exception")).when(mockServiceimpl).getMsg();

        Mockito.doNothing().doThrow(new Exception("mock doNothing1 exception")).when(mockServiceimpl)
                .exceptionMethod();
        mockServiceimpl.exceptionMethod();

        try{
        mockServiceimpl.exceptionMethod();
        }catch (Exception e){
            System.out.println("second method call throw exception");
        }

    }
}
