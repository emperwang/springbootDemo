package com.wk.web.controller;

import com.wk.bean.MonthSum;
import com.wk.bean.views.DataGradeView;
import com.wk.bean.views.MonthSumVo;
import com.wk.constant.MonthConstant;
import com.wk.util.GroupExcelUtil;
import com.wk.util.JSONUtil;
import com.wk.web.service.MonthSumService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    // page=1&rows=10  分页参数
    @PostMapping(value = "getDataGride.do")
    public DataGradeView<MonthSumVo> getDataGride(Integer page,Integer rows){
        if (log.isDebugEnabled()){
            log.debug("group getDataGride.do,receive parameter page = {},rows = {}",page,rows);
        }

        DataGradeView<MonthSumVo> gradeView = monthSumService.dataGradeList(page,rows);
        return gradeView;
    }

    @PostMapping(value = "searchDataGride.do")
    public DataGradeView<MonthSumVo> searchDataGrideData(Integer month,Integer personCount,Integer page,Integer rows){
        log.info("searchDataGrideData receive param is: month="+month + ", personCount="+personCount);
        DataGradeView<MonthSumVo> dataGradeView = monthSumService.searchFirstMonthSatisifyCount(month,personCount,page,rows);

        return dataGradeView;
    }
    @ResponseBody
    @PostMapping(value = "uploadexcel.do")
    public String uploadFile(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("excelName");
        String originalFilename = file.getOriginalFilename();
        log.info("get originfileName is :{}",originalFilename);
        GroupExcelUtil instance = GroupExcelUtil.getInstance();
        instance.readDataFromExcel(file);
        instance.printTotalNum(MonthConstant.juneEnd);
        monthSumService.addGroupFromExcel(instance);
        return originalFilename;
    }

    @PostMapping(value = "downloadExcel.do")
    public void downLoadExcel(@RequestBody String ids, HttpServletResponse response){
        log.info("receive msg is:{}",ids);
        try {
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setHeader("Content-Disposition","demoFile.txt");
            Workbook workbook = monthSumService.writeDataToExcel(ids);
            workbook.write(outputStream);
            outputStream.flush();
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  测试下载excel 文件
     * @param outputStream
     */
    private void testDownLoad(BufferedOutputStream outputStream){
        try {
            FileInputStream inputStream = new FileInputStream(new File("F:\\FTPTest\\output.xls"));
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  dataGride 批量增加 和 批量更新
     * @return
     */
    @PostMapping(value = "batchOpera.do")
    public String batchInsertAndUpdate(@RequestBody String groups){
        log.info("batchOpera.do receive msg is : {}",groups);
        if (groups != null && groups.length() > 0) {
             int count = monthSumService.batchAddAndUpdate(groups);
            List<MonthSum> monthSums = JSONUtil.jsonToBeanList(groups, MonthSum.class);
            log.info(monthSums.toString());

            if (count > 0){
                return "success";
            }
        }
        return "invalid parameter";
    }
}
