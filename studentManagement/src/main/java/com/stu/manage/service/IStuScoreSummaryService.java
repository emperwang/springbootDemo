package com.stu.manage.service;

import com.stu.manage.entiry.StuScoreSummary;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/2/7 21:27
 * @Description
 */
public interface IStuScoreSummaryService {

    List<StuScoreSummary> listAllSummary();

    StuScoreSummary getColumnByYear(String year);
}
