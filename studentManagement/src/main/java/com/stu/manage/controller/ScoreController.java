package com.stu.manage.controller;

import com.stu.manage.entiry.CommonResult;
import com.stu.manage.entiry.Score;
import com.stu.manage.service.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/18 0:03
 * @Description
 */
@RestController
@RequestMapping("score")
public class ScoreController {

    private IScoreService scoreService;


    @GetMapping("list")
    public CommonResult<Score> listScore(){
        CommonResult.CommonResultBuilder<Score> builder = new CommonResult.CommonResultBuilder<>();
        List<Score> scores = scoreService.listScores();
        return builder.counts(scores.size()).results(scores).build();
    }



    @Autowired
    public void setScoreService(IScoreService scoreService) {
        this.scoreService = scoreService;
    }
}
