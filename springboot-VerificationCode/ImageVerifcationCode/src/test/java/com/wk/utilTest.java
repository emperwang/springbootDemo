package com.wk;

import com.wk.util.CodeUtil;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author: wk
 * @Date: 2020/10/29 11:26
 * @Description
 */
public class utilTest {

    @Test
    public void test1() throws FileNotFoundException {
        String path = "C:\\code-workspace\\source\\springbootDemo\\springboot-VerificationCode\\ImageVerifcationCode\\src\\main\\resources\\bit.jpeg";
        final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path));
        final CodeUtil codeUtil = new CodeUtil();
        codeUtil.getRandCode(outputStream);
        System.out.println("complete");
    }
}
