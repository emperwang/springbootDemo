package com.stu.manage.service;

import com.stu.manage.entity.Score;
import org.springframework.web.multipart.MultipartFile;

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

    List<Score> listScoreByAcademicYearAndSemester(String year, int semester);

    List<Score> batchInsert(MultipartFile file);
}
