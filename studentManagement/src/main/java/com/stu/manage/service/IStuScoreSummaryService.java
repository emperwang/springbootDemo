package com.stu.manage.service;

import com.stu.manage.entity.StuScoreSummary;

import java.util.List;
import java.util.Map;

/**
 * @author: Sparks
 * @Date: 2024/2/7 21:27
 * @Description
 */
public interface IStuScoreSummaryService {

    List<StuScoreSummary> listAllSummary();

    StuScoreSummary getColumnByYear(String year,int semester);

    List<Map> groupScoreByUserAndYear(String year, int semester);

    List<StuScoreSummary> saveSummary(List<StuScoreSummary> stuScoreSummary);
}
