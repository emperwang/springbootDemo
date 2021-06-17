package com.wk;
import com.wk.MpStarter;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Sparks
 * @Date: 2021/6/17 21:56
 * @Description
 */
@SpringBootTest(classes = {MpStarter.class})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TestParent {
}
