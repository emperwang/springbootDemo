package com.wk.mapper.slave;

import com.wk.entity.slave.AlarmSequenceAnalyze;
import com.wk.entity.slave.AlarmSequenceAnalyzeExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AlarmSequenceAnalyzeMapper {
    int countByExample(AlarmSequenceAnalyzeExample example);

    int deleteByExample(AlarmSequenceAnalyzeExample example);

    int deleteByPrimaryKey(Integer autoId);

    int insert(AlarmSequenceAnalyze record);

    int insertBatch(List<AlarmSequenceAnalyze> list);

    int insertSelective(AlarmSequenceAnalyze record);

    List<AlarmSequenceAnalyze> selectByExample(AlarmSequenceAnalyzeExample example);

    List<String> selectSourceIds();

    List<AlarmSequenceAnalyze> getUnAnalyzeBySourceIdWithLimits(@Param("sourceId") String sourceId,
                                                                @Param("limits") Integer limits,
                                                                @Param("start")Date start,
                                                                @Param("end") Date end);

    List<AlarmSequenceAnalyze> getUnAnalyzeBySourceIdNoLimits(String sourceId);

    List<Integer> checkLostSeq(@Param("sourceId") String sourceId, @Param("list") List<Integer> losts);

    AlarmSequenceAnalyze selectByPrimaryKey(Integer autoId);

    int updateByExampleSelective(@Param("record") AlarmSequenceAnalyze record, @Param("example") AlarmSequenceAnalyzeExample example);

    int updateByExample(@Param("record") AlarmSequenceAnalyze record, @Param("example") AlarmSequenceAnalyzeExample example);

    int updateByPrimaryKeySelective(AlarmSequenceAnalyze record);

    int updateByPrimaryKey(AlarmSequenceAnalyze record);

    int updateIfCompleteByPrimaryKey(AlarmSequenceAnalyze record);
}