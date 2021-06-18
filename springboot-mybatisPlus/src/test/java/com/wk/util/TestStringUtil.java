package com.wk.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author: Sparks
 * @Date: 2021/6/18 21:15
 * @Description
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {StringUtils.class})
public class TestStringUtil {

    @Test
    public void testStaticMethod(){
        // 针对静态方法的mock
        PowerMockito.mockStatic(StringUtils.class);
        Mockito.when(StringUtils.getString()).thenReturn("mock string");
        Assert.assertEquals("mock exception","mock string", StringUtils.getString());
    }
}
