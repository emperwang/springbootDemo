package com.wk.web.controller;

import com.wk.entity.master.UserBean;
import com.wk.entity.slave.AmCollectorSource;
import com.wk.web.service.master.UserService;
import com.wk.web.service.slave.CollectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
public class UserDemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private CollectorService collectorService;

    @GetMapping(value = "user.do")
    public List<UserBean> getAll(){
        List<UserBean> all = userService.getAll();
        log.info("user all:{}",all.toString());
        return all;
    }

    @GetMapping(value = "collector.do")
    public List<AmCollectorSource> getAllAm(){
        List<AmCollectorSource> all = collectorService.getAll();
        log.info("all Collector:{}", all.toString());
        return all;
    }

    @GetMapping(value = "page.do")
    public String pagesDemo(){
        List<UserBean> pages = userService.getPages(2, 10);
        HashMap<String, Object> map = new HashMap<>();
        map.put("start",2);
        map.put("end", 5);
        List<UserBean> pageWithMap = userService.getPageWithMap(map);

        List<AmCollectorSource> pages1 = collectorService.getPages(2, 5);

        List<AmCollectorSource> pageWithMap1 = collectorService.getPageWithMap(map);

        log.info("user pages: {}", pages);
        log.info("user map:{}", pageWithMap);

        log.info("collcetor pages:{}", pages1);
        log.info("collector maps :{}", pageWithMap1);

        return "success";
    }

}
