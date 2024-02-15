package com.stu.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.manage.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/17 23:33
 * @Description
 */
@Mapper
public interface ScoreMapper extends BaseMapper<Score> {

    void batchInsert(List<Score> scores);
}
