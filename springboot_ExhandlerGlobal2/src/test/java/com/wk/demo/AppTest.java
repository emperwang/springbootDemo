package com.wk.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void getProperty(){
        String NEW_LINE = System.getProperty("line.separator");
        System.out.println(NEW_LINE);
    }
}
