package com.wk.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @Test
    public void verifyBehaviour(){
        // mock createion
        List mockList = Mockito.mock(List.class);
        // using mock object
        mockList.add("one");
        mockList.clear();
        // 验证 add("one") 是否发生
        Mockito.verify(mockList).add("one");
        //Mockito.verify(mockList).add("1");
        Mockito.verify(mockList).clear();
    }
}
