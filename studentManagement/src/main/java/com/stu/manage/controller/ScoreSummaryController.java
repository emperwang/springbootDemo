package com.stu.manage.controller;

import com.stu.manage.entiry.StuScoreSummary;
import com.stu.manage.service.IStuScoreSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/2/7 21:33
 * @Description
 */
@RestController
@RequestMapping("scoresum")
public class ScoreSummaryController {

    private IStuScoreSummaryService stuScoreSummaryService;


    @GetMapping("list")
    public ResponseEntity<List<StuScoreSummary>> listAll(){
        List<StuScoreSummary> listAllSummary = stuScoreSummaryService.listAllSummary();
        return ResponseEntity.ok(listAllSummary);
    }

    @GetMapping("listbyyear/{year}")
    public ResponseEntity<StuScoreSummary> listByYear(@PathVariable("year") String year){
        return ResponseEntity.ok(stuScoreSummaryService.getColumnByYear(year));
    }

    @Autowired
    public void setStuScoreSummaryService(IStuScoreSummaryService stuScoreSummaryService) {
        this.stuScoreSummaryService = stuScoreSummaryService;
    }
}
