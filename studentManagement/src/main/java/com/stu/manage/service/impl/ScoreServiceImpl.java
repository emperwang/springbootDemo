package com.stu.manage.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.manage.constant.ColumnConstant;
import com.stu.manage.entity.Score;
import com.stu.manage.entity.StuScoreSummary;
import com.stu.manage.entity.User;
import com.stu.manage.listener.ExcelEventListener;
import com.stu.manage.mapper.ScoreMapper;
import com.stu.manage.service.IScoreService;
import com.stu.manage.service.IStuScoreSummaryService;
import com.stu.manage.service.IUserService;
import com.stu.manage.utils.TimeFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author: Sparks
 * @Date: 2024/1/17 23:35
 * @Description
 */
@Service
@Slf4j
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

    private ScoreMapper scoreMapper;
    private DateTimeFormatter formatter = TimeFormatUtil.getFormat(TimeFormatUtil.format1);
    private IStuScoreSummaryService stuScoreSummaryService;
    private IUserService userService;

    @Override
    public List<Score> listScores() {
        QueryWrapper<Score> scoreQueryWrapper = new QueryWrapper<>();
        scoreQueryWrapper.orderByAsc("sid");
        List<Score> scores = scoreMapper.selectList(scoreQueryWrapper);
        return scores;
    }

    @Override
    public int deleteScoreById(long id) {
        return scoreMapper.deleteById(id);
    }

    @Override
    public int deleteScoreByIds(List<Long> ids) {
        return scoreMapper.deleteBatchIds(ids);
    }

    @Override
    public int updateScoreById(Score r) {
        String format = formatter.format(LocalDateTime.now());
        r.setUpdateTime(format);
        return scoreMapper.updateById(r);
    }

    @Override
    public Score saveScore(Score r) {
        String timer = formatter.format(LocalDateTime.now());
        r.setCreateTime(timer);
        r.setUpdateTime(timer);
        return scoreMapper.insert(r)>0?r:null;
    }

    @Override
    public List<Score> listScoreByAcademicYearAndSemester(String year, int semester) {
        QueryWrapper<Score> scoreQueryWrapper = new QueryWrapper<>();
        scoreQueryWrapper.eq("academic_year", year);
        scoreQueryWrapper.eq("semester", semester);
        return scoreMapper.selectList(scoreQueryWrapper);
    }

    @Override
    public List<Score> batchInsert(MultipartFile file) {
        // 解析excel, 获取成绩,以及课程表
        ExcelEventListener eventListener = new ExcelEventListener();
        try {
            EasyExcel.read(file.getInputStream(),eventListener).sheet(0).doRead();
        } catch (IOException e) {
            log.error("batch insert error: ", e);
        }
        List<Score> scores = eventListener.getScores();
        // 保存成绩
        scoreMapper.batchInsert(scores);
        // 保存课程
        Map<String, String> courses = eventListener.getAcademicToCourses();

        List<StuScoreSummary> summaryList = courses.entrySet().stream().map(entry -> {
            String[] s = entry.getKey().split("_");
            String columns = entry.getValue();
            StuScoreSummary stuScoreSummary = new StuScoreSummary();
            stuScoreSummary.setAcademicYear(s[0]);
            stuScoreSummary.setSemester(Integer.parseInt(s[1]));
            stuScoreSummary.setColumns(columns);
            return stuScoreSummary;
        }).collect(Collectors.toList());
        stuScoreSummaryService.saveSummary(summaryList);

        // 保存学生信息
        List<User> users = eventListener.getUsers();
        userService.batchInsert(users);
        return scores;
    }

    @Override
    public Map<String, String> getScoreByUidYearAndSemester(String uid, String year, String semester) {
        QueryWrapper<Score> scoreQueryWrapper = new QueryWrapper<>();
        scoreQueryWrapper.eq("academic_year", year);
        scoreQueryWrapper.eq("semester", semester);
        scoreQueryWrapper.eq("uid", uid);
        List<Score> scores = scoreMapper.selectList(scoreQueryWrapper);
        Map<String, String> res = new ConcurrentHashMap<>(20);
        res.put(ColumnConstant.USERNAME, scores.get(0).getUsername());
        scores.stream().forEach(sc -> {
            res.put(sc.getCourse(), String.format("%2f", sc.getScore()));
        });

        return res;
    }


    @Autowired
    public void setScoreMapper(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }

    @Autowired
    public void setStuScoreSummaryService(IStuScoreSummaryService stuScoreSummaryService) {
        this.stuScoreSummaryService = stuScoreSummaryService;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
