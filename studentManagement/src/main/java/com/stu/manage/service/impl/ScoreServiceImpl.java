package com.stu.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.manage.entiry.Score;
import com.stu.manage.mapper.ScoreMapper;
import com.stu.manage.service.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/17 23:35
 * @Description
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

    private ScoreMapper scoreMapper;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss");
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


    @Autowired
    public void setScoreMapper(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }
}
