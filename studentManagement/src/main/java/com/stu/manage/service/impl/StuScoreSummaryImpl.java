package com.stu.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.manage.constant.ColumnConstant;
import com.stu.manage.entity.Score;
import com.stu.manage.entity.StuScoreSummary;
import com.stu.manage.mapper.StuScoreSummaryMapper;
import com.stu.manage.service.IScoreService;
import com.stu.manage.service.IStuScoreSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author: Sparks
 * @Date: 2024/2/7 21:28
 * @Description
 */
@Service
public class StuScoreSummaryImpl extends ServiceImpl<StuScoreSummaryMapper, StuScoreSummary> implements IStuScoreSummaryService {

    private StuScoreSummaryMapper stuScoreSummaryMapper;

    private IScoreService scoreService;


    @Override
    public List<StuScoreSummary> listAllSummary() {
        QueryWrapper<StuScoreSummary> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("ssid");
        return stuScoreSummaryMapper.selectList(wrapper);
    }

    @Override
    public StuScoreSummary getColumnByYear(String year,int semester) {
        QueryWrapper<StuScoreSummary> wrapper = new QueryWrapper<>();
        wrapper.eq("academic_year",year);
        wrapper.eq("semester", semester);
        List<StuScoreSummary> stuScoreSummaries = stuScoreSummaryMapper.selectList(wrapper);
        return stuScoreSummaries.size()>0?stuScoreSummaries.get(0):new StuScoreSummary();
    }

    @Override
    public List<Map> groupScoreByUserAndYear(String year, int semester) {
        List<Map> lists = new ArrayList<>();
        List<Score> scores = scoreService.listScoreByAcademicYearAndSemester(year, semester);
        Map<Long, List<Score>> listMap = scores.stream().collect(Collectors.groupingBy(Score::getUid));
        lists = listMap.entrySet().stream().map(entry -> {
            Map<String, String> map = new ConcurrentHashMap<>();
            Long key = entry.getKey();
            map.put(ColumnConstant.SSID, key.toString());
            entry.getValue().stream().forEach(score -> {
                map.put(score.getCourse(), String.valueOf(score.getScore()));
            });
            map.put(ColumnConstant.USERNAME, entry.getValue().get(0).getUsername());
            // 必修
            List<Score> required = entry.getValue().stream().filter(score -> score.getCourseNature() == 1).collect(Collectors.toList());
            // 体育
            List<Score> sports = entry.getValue().stream().filter(score -> score.getCourseNature() != 1 && score.getCourseNature() != 2 && !score.getCourse().equalsIgnoreCase(ColumnConstant.EDUCATION_SCORE)).collect(Collectors.toList());
            // 选修
            List<Score> electiveCourse = entry.getValue().stream().filter(score -> score.getCourseNature() == 2).collect(Collectors.toList());
            // 考试成绩
            Float reduce = required.stream().map(score -> score.getScore() * score.getCredit()).reduce((float) 0.0, (n1, n2) -> n1 + n2);
            Float creditSum = required.stream().map(Score::getCredit).reduce((float) 0, (n1, n2) -> n1 + n2);
            Float exam = reduce / creditSum;
            map.put(ColumnConstant.EXAMINATION, String.format("%.2f", exam));
            // 考查成绩
            float electiveScore = electiveCourse.stream().map(Score::getScore).reduce((n1, n2) -> n1 + n2).get();
            Float examine = (float)(electiveScore) / (electiveCourse.size());
            map.put(ColumnConstant.EXAMINE, String.format("%.2f", examine));
            // 德育成绩
            Optional<Score> edu = entry.getValue().stream().filter(score -> score.getCourseNature() != 1 && score.getCourseNature() != 2 && score.getCourse().equalsIgnoreCase(ColumnConstant.EDUCATION_SCORE)).findFirst();
            Float educationScore = 70f;
            if (edu.isPresent()) {
                educationScore = edu.get().getScore();
            }
            map.put(ColumnConstant.EDUCATION, educationScore.toString());
            // 智育成绩
            Double intellectual = examine * 0.3 + exam * 0.7;
            map.put(ColumnConstant.INTELLECTUAL, String.format("%.2f", intellectual));

            // 综合成绩
            float sportScore = sports.stream().map(Score::getScore).reduce((n1, n2) -> n1 + n2).get();
            Double scoreSummary = intellectual * 0.65 + educationScore * 0.1 + sportScore * 0.25;
            map.put(ColumnConstant.SCORE_SUMMARY, String.format("%.2f", scoreSummary));
            return map;
        }).collect(Collectors.toList());

        return lists;
    }

    @Override
    public List<StuScoreSummary> saveSummary(List<StuScoreSummary> stuScoreSummary) {
        stuScoreSummaryMapper.batchInsert(stuScoreSummary);
        return stuScoreSummary;
    }

    @Autowired
    public void setStuScoreSummaryMapper(StuScoreSummaryMapper stuScoreSummaryMapper) {
        this.stuScoreSummaryMapper = stuScoreSummaryMapper;
    }

    @Autowired
    public void setScoreService(IScoreService scoreService) {
        this.scoreService = scoreService;
    }
}
