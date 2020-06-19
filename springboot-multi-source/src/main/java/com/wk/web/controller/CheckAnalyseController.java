package com.wk.web.controller;

import com.wk.entity.slave.AlarmSequenceAnalyze;
import com.wk.mapper.slave.AlarmSequenceAnalyzeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    @GetMapping(value = "checkDate.do")
    public List<AlarmSequenceAnalyze> checkDateCompare(){
        // 2019-11-26 09:53:21
        LocalDateTime start1 = LocalDateTime.of(2019, 11, 26, 9, 53, 21, 00);
        LocalDateTime end1 = start1.plusSeconds(20);
        Date start = Date.from(start1.atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(end1.atZone(ZoneId.systemDefault()).toInstant());
        List<AlarmSequenceAnalyze> lists = alarmSequenceAnalyzeMapper.getUnAnalyzeBySourceIdWithLimits("vim001",
                5, start, end);
        return lists;
    }

}
