package com.wk.demo.web.service;

import com.wk.demo.common.util.JSONUtil;
import com.wk.demo.entity.AmCollectorSource;
import com.wk.demo.entity.UserBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private SourceService sourceService;

    @Test
    public void test(){
        List<AmCollectorSource> all = sourceService.getAll();

        List<UserBean> allUser = sourceService.getAllUser();
        System.out.println(JSONUtil.beanToJson(all));
        System.out.println(JSONUtil.beanToJson(allUser));
    }
}
