package com.stu.manage.service;

import com.stu.manage.entiry.Score;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/17 23:35
 * @Description
 */
public interface IScoreService {

    List<Score> listScores();

    int deleteScoreById(long id);

    int deleteScoreByIds(List<Long> ids);

    int updateScoreById(Score r);

    Score saveScore(Score r);
}
