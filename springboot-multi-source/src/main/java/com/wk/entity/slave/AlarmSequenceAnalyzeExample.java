package com.wk.entity.slave;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlarmSequenceAnalyzeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AlarmSequenceAnalyzeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAutoIdIsNull() {
            addCriterion("auto_id is null");
            return (Criteria) this;
        }

        public Criteria andAutoIdIsNotNull() {
            addCriterion("auto_id is not null");
            return (Criteria) this;
        }

        public Criteria andAutoIdEqualTo(Integer value) {
            addCriterion("auto_id =", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdNotEqualTo(Integer value) {
            addCriterion("auto_id <>", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdGreaterThan(Integer value) {
            addCriterion("auto_id >", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("auto_id >=", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdLessThan(Integer value) {
            addCriterion("auto_id <", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdLessThanOrEqualTo(Integer value) {
            addCriterion("auto_id <=", value, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdIn(List<Integer> values) {
            addCriterion("auto_id in", values, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdNotIn(List<Integer> values) {
            addCriterion("auto_id not in", values, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdBetween(Integer value1, Integer value2) {
            addCriterion("auto_id between", value1, value2, "autoId");
            return (Criteria) this;
        }

        public Criteria andAutoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("auto_id not between", value1, value2, "autoId");
            return (Criteria) this;
        }

        public Criteria andSourceIdIsNull() {
            addCriterion("source_id is null");
            return (Criteria) this;
        }

        public Criteria andSourceIdIsNotNull() {
            addCriterion("source_id is not null");
            return (Criteria) this;
        }

        public Criteria andSourceIdEqualTo(String value) {
            addCriterion("source_id =", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdNotEqualTo(String value) {
            addCriterion("source_id <>", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdGreaterThan(String value) {
            addCriterion("source_id >", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("source_id >=", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdLessThan(String value) {
            addCriterion("source_id <", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdLessThanOrEqualTo(String value) {
            addCriterion("source_id <=", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdLike(String value) {
            addCriterion("source_id like", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdNotLike(String value) {
            addCriterion("source_id not like", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdIn(List<String> values) {
            addCriterion("source_id in", values, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdNotIn(List<String> values) {
            addCriterion("source_id not in", values, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdBetween(String value1, String value2) {
            addCriterion("source_id between", value1, value2, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdNotBetween(String value1, String value2) {
            addCriterion("source_id not between", value1, value2, "sourceId");
            return (Criteria) this;
        }

        public Criteria andAlarmSeqIsNull() {
            addCriterion("alarm_seq is null");
            return (Criteria) this;
        }

        public Criteria andAlarmSeqIsNotNull() {
            addCriterion("alarm_seq is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmSeqEqualTo(Integer value) {
            addCriterion("alarm_seq =", value, "alarmSeq");
            return (Criteria) this;
        }

        public Criteria andAlarmSeqNotEqualTo(Integer value) {
            addCriterion("alarm_seq <>", value, "alarmSeq");
            return (Criteria) this;
        }

        public Criteria andAlarmSeqGreaterThan(Integer value) {
            addCriterion("alarm_seq >", value, "alarmSeq");
            return (Criteria) this;
        }

        public Criteria andAlarmSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("alarm_seq >=", value, "alarmSeq");
            return (Criteria) this;
        }

        public Criteria andAlarmSeqLessThan(Integer value) {
            addCriterion("alarm_seq <", value, "alarmSeq");
            return (Criteria) this;
        }

        public Criteria andAlarmSeqLessThanOrEqualTo(Integer value) {
            addCriterion("alarm_seq <=", value, "alarmSeq");
            return (Criteria) this;
        }

        public Criteria andAlarmSeqIn(List<Integer> values) {
            addCriterion("alarm_seq in", values, "alarmSeq");
            return (Criteria) this;
        }

        public Criteria andAlarmSeqNotIn(List<Integer> values) {
            addCriterion("alarm_seq not in", values, "alarmSeq");
            return (Criteria) this;
        }

        public Criteria andAlarmSeqBetween(Integer value1, Integer value2) {
            addCriterion("alarm_seq between", value1, value2, "alarmSeq");
            return (Criteria) this;
        }

        public Criteria andAlarmSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("alarm_seq not between", value1, value2, "alarmSeq");
            return (Criteria) this;
        }

        public Criteria andEventTimeIsNull() {
            addCriterion("event_time is null");
            return (Criteria) this;
        }

        public Criteria andEventTimeIsNotNull() {
            addCriterion("event_time is not null");
            return (Criteria) this;
        }

        public Criteria andEventTimeEqualTo(Date value) {
            addCriterion("event_time =", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeNotEqualTo(Date value) {
            addCriterion("event_time <>", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeGreaterThan(Date value) {
            addCriterion("event_time >", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("event_time >=", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeLessThan(Date value) {
            addCriterion("event_time <", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeLessThanOrEqualTo(Date value) {
            addCriterion("event_time <=", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeIn(List<Date> values) {
            addCriterion("event_time in", values, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeNotIn(List<Date> values) {
            addCriterion("event_time not in", values, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeBetween(Date value1, Date value2) {
            addCriterion("event_time between", value1, value2, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeNotBetween(Date value1, Date value2) {
            addCriterion("event_time not between", value1, value2, "eventTime");
            return (Criteria) this;
        }

        public Criteria andIfCompleteAnalysisIsNull() {
            addCriterion("if_complete_analysis is null");
            return (Criteria) this;
        }

        public Criteria andIfCompleteAnalysisIsNotNull() {
            addCriterion("if_complete_analysis is not null");
            return (Criteria) this;
        }

        public Criteria andIfCompleteAnalysisEqualTo(Boolean value) {
            addCriterion("if_complete_analysis =", value, "ifCompleteAnalysis");
            return (Criteria) this;
        }

        public Criteria andIfCompleteAnalysisNotEqualTo(Boolean value) {
            addCriterion("if_complete_analysis <>", value, "ifCompleteAnalysis");
            return (Criteria) this;
        }

        public Criteria andIfCompleteAnalysisGreaterThan(Boolean value) {
            addCriterion("if_complete_analysis >", value, "ifCompleteAnalysis");
            return (Criteria) this;
        }

        public Criteria andIfCompleteAnalysisGreaterThanOrEqualTo(Boolean value) {
            addCriterion("if_complete_analysis >=", value, "ifCompleteAnalysis");
            return (Criteria) this;
        }

        public Criteria andIfCompleteAnalysisLessThan(Boolean value) {
            addCriterion("if_complete_analysis <", value, "ifCompleteAnalysis");
            return (Criteria) this;
        }

        public Criteria andIfCompleteAnalysisLessThanOrEqualTo(Boolean value) {
            addCriterion("if_complete_analysis <=", value, "ifCompleteAnalysis");
            return (Criteria) this;
        }

        public Criteria andIfCompleteAnalysisIn(List<Boolean> values) {
            addCriterion("if_complete_analysis in", values, "ifCompleteAnalysis");
            return (Criteria) this;
        }

        public Criteria andIfCompleteAnalysisNotIn(List<Boolean> values) {
            addCriterion("if_complete_analysis not in", values, "ifCompleteAnalysis");
            return (Criteria) this;
        }

        public Criteria andIfCompleteAnalysisBetween(Boolean value1, Boolean value2) {
            addCriterion("if_complete_analysis between", value1, value2, "ifCompleteAnalysis");
            return (Criteria) this;
        }

        public Criteria andIfCompleteAnalysisNotBetween(Boolean value1, Boolean value2) {
            addCriterion("if_complete_analysis not between", value1, value2, "ifCompleteAnalysis");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}