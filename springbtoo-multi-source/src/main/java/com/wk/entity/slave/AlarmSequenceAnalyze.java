package com.wk.entity.slave;

import lombok.ToString;

import java.util.Date;

@ToString
public class AlarmSequenceAnalyze {
    private Integer autoId;

    private String sourceId;

    private Integer alarmSeq;

    private Date eventTime;

    private Boolean ifCompleteAnalysis;

    private Date updateTime;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    public Integer getAlarmSeq() {
        return alarmSeq;
    }

    public void setAlarmSeq(Integer alarmSeq) {
        this.alarmSeq = alarmSeq;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Boolean getIfCompleteAnalysis() {
        return ifCompleteAnalysis;
    }

    public void setIfCompleteAnalysis(Boolean ifCompleteAnalysis) {
        this.ifCompleteAnalysis = ifCompleteAnalysis;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}