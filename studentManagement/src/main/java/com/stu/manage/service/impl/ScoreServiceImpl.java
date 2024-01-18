package com.stu.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.manage.entiry.Score;
import com.stu.manage.mapper.ScoreMapper;
import com.stu.manage.service.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/17 23:35
 * @Description
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

    private ScoreMapper scoreMapper;

    @Override
    public List<Score> listScores() {
        QueryWrapper<Score> scoreQueryWrapper = new QueryWrapper<>();
        scoreQueryWrapper.orderByAsc("sid");
        List<Score> scores = scoreMapper.selectList(scoreQueryWrapper);
        return scores;
    }


    @Autowired
    public void setScoreMapper(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }
}
