package com.wk.web.controller;

import com.wk.bean.MonthSum;
import com.wk.bean.views.DataGradeView;
import com.wk.web.service.MonthSumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("groupdata")
public class GroupDataController {
    private static Logger log = LoggerFactory.getLogger(GroupDataController.class);
    @Autowired
    private MonthSumService monthSumService;

    @GetMapping(value = "getAllinfo.do")
    public List<MonthSum> getAllGroupInfo(){
        List<MonthSum> all = monthSumService.findAll();

        return all;
    }

    @GetMapping(value = "getDataGride.do")
    public DataGradeView<MonthSum> getDataGride(){
        DataGradeView<MonthSum> gradeView = monthSumService.dataGradeList();
        return gradeView;
    }

    @PostMapping(value = "searchDataGride.do")
    public DataGradeView<MonthSum> searchDataGrideData(Integer month,Integer personCount){
        log.info("searchDataGrideData receive param is: month="+month + ", personCount="+personCount);
        DataGradeView<MonthSum> dataGradeView = monthSumService.searchFirstMonthSatisifyCount(month,personCount);

        return dataGradeView;
    }
    @ResponseBody
    @PostMapping(value = "uploadexcel.do")
    public String uploadFile(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("excelName");
        String originalFilename = file.getOriginalFilename();
        log.info("get originfileName is :{}",originalFilename);
        return originalFilename;
    }

    @PostMapping(value = "downloadExcel.do")
    public void downLoadExcel(@RequestBody String ids, HttpServletResponse response){
        log.info("receive msg is:{}",ids);
        try {
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] bytes = ids.getBytes();
            outputStream.write(bytes,0,bytes.length);
            outputStream.flush();
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
