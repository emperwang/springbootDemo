package com.wk.web.controller;

import com.wk.entity.slave.AlarmSequenceAnalyze;
import com.wk.mapper.slave.AlarmSequenceAnalyzeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * descripiton:
 *
 * @author: wk
 * @time: 17:54 2019/12/19
 * @modifier:
 */
@RestController
public class CheckAnalyseController {

    @Autowired
    private AlarmSequenceAnalyzeMapper alarmSequenceAnalyzeMapper;

    @GetMapping(value = "lost.do")
    public List<Integer> checkLosts(){
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(6);
        integers.add(5);
        List<Integer> vim001 = alarmSequenceAnalyzeMapper.checkLostSeq("vim001", integers);
        return vim001;
    }

    @GetMapping(value = "updateCom.do")
    public String checkUpdateField(){
        AlarmSequenceAnalyze sequenceAnalyze = new AlarmSequenceAnalyze();
        sequenceAnalyze.setAutoId(2726);
        sequenceAnalyze.setIfCompleteAnalysis(true);
        alarmSequenceAnalyzeMapper.updateIfCompleteByPrimaryKey(sequenceAnalyze);
        return "success";
    }

}
