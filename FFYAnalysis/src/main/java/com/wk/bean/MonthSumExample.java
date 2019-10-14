package com.wk.bean;

import java.util.ArrayList;
import java.util.List;

public class MonthSumExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MonthSumExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andMonthIsNull() {
            addCriterion("month is null");
            return (Criteria) this;
        }

        public Criteria andMonthIsNotNull() {
            addCriterion("month is not null");
            return (Criteria) this;
        }

        public Criteria andMonthEqualTo(Integer value) {
            addCriterion("month =", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotEqualTo(Integer value) {
            addCriterion("month <>", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThan(Integer value) {
            addCriterion("month >", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("month >=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThan(Integer value) {
            addCriterion("month <", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThanOrEqualTo(Integer value) {
            addCriterion("month <=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthIn(List<Integer> values) {
            addCriterion("month in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotIn(List<Integer> values) {
            addCriterion("month not in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthBetween(Integer value1, Integer value2) {
            addCriterion("month between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("month not between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andFirstPersonCountIsNull() {
            addCriterion("first_person_count is null");
            return (Criteria) this;
        }

        public Criteria andFirstPersonCountIsNotNull() {
            addCriterion("first_person_count is not null");
            return (Criteria) this;
        }

        public Criteria andFirstPersonCountEqualTo(Integer value) {
            addCriterion("first_person_count =", value, "firstPersonCount");
            return (Criteria) this;
        }

        public Criteria andFirstPersonCountNotEqualTo(Integer value) {
            addCriterion("first_person_count <>", value, "firstPersonCount");
            return (Criteria) this;
        }

        public Criteria andFirstPersonCountGreaterThan(Integer value) {
            addCriterion("first_person_count >", value, "firstPersonCount");
            return (Criteria) this;
        }

        public Criteria andFirstPersonCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("first_person_count >=", value, "firstPersonCount");
            return (Criteria) this;
        }

        public Criteria andFirstPersonCountLessThan(Integer value) {
            addCriterion("first_person_count <", value, "firstPersonCount");
            return (Criteria) this;
        }

        public Criteria andFirstPersonCountLessThanOrEqualTo(Integer value) {
            addCriterion("first_person_count <=", value, "firstPersonCount");
            return (Criteria) this;
        }

        public Criteria andFirstPersonCountIn(List<Integer> values) {
            addCriterion("first_person_count in", values, "firstPersonCount");
            return (Criteria) this;
        }

        public Criteria andFirstPersonCountNotIn(List<Integer> values) {
            addCriterion("first_person_count not in", values, "firstPersonCount");
            return (Criteria) this;
        }

        public Criteria andFirstPersonCountBetween(Integer value1, Integer value2) {
            addCriterion("first_person_count between", value1, value2, "firstPersonCount");
            return (Criteria) this;
        }

        public Criteria andFirstPersonCountNotBetween(Integer value1, Integer value2) {
            addCriterion("first_person_count not between", value1, value2, "firstPersonCount");
            return (Criteria) this;
        }

        public Criteria andEndPersonCountIsNull() {
            addCriterion("end_person_count is null");
            return (Criteria) this;
        }

        public Criteria andEndPersonCountIsNotNull() {
            addCriterion("end_person_count is not null");
            return (Criteria) this;
        }

        public Criteria andEndPersonCountEqualTo(Integer value) {
            addCriterion("end_person_count =", value, "endPersonCount");
            return (Criteria) this;
        }

        public Criteria andEndPersonCountNotEqualTo(Integer value) {
            addCriterion("end_person_count <>", value, "endPersonCount");
            return (Criteria) this;
        }

        public Criteria andEndPersonCountGreaterThan(Integer value) {
            addCriterion("end_person_count >", value, "endPersonCount");
            return (Criteria) this;
        }

        public Criteria andEndPersonCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("end_person_count >=", value, "endPersonCount");
            return (Criteria) this;
        }

        public Criteria andEndPersonCountLessThan(Integer value) {
            addCriterion("end_person_count <", value, "endPersonCount");
            return (Criteria) this;
        }

        public Criteria andEndPersonCountLessThanOrEqualTo(Integer value) {
            addCriterion("end_person_count <=", value, "endPersonCount");
            return (Criteria) this;
        }

        public Criteria andEndPersonCountIn(List<Integer> values) {
            addCriterion("end_person_count in", values, "endPersonCount");
            return (Criteria) this;
        }

        public Criteria andEndPersonCountNotIn(List<Integer> values) {
            addCriterion("end_person_count not in", values, "endPersonCount");
            return (Criteria) this;
        }

        public Criteria andEndPersonCountBetween(Integer value1, Integer value2) {
            addCriterion("end_person_count between", value1, value2, "endPersonCount");
            return (Criteria) this;
        }

        public Criteria andEndPersonCountNotBetween(Integer value1, Integer value2) {
            addCriterion("end_person_count not between", value1, value2, "endPersonCount");
            return (Criteria) this;
        }

        public Criteria andDepentsIdIsNull() {
            addCriterion("depents_id is null");
            return (Criteria) this;
        }

        public Criteria andDepentsIdIsNotNull() {
            addCriterion("depents_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepentsIdEqualTo(Integer value) {
            addCriterion("depents_id =", value, "depentsId");
            return (Criteria) this;
        }

        public Criteria andDepentsIdNotEqualTo(Integer value) {
            addCriterion("depents_id <>", value, "depentsId");
            return (Criteria) this;
        }

        public Criteria andDepentsIdGreaterThan(Integer value) {
            addCriterion("depents_id >", value, "depentsId");
            return (Criteria) this;
        }

        public Criteria andDepentsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("depents_id >=", value, "depentsId");
            return (Criteria) this;
        }

        public Criteria andDepentsIdLessThan(Integer value) {
            addCriterion("depents_id <", value, "depentsId");
            return (Criteria) this;
        }

        public Criteria andDepentsIdLessThanOrEqualTo(Integer value) {
            addCriterion("depents_id <=", value, "depentsId");
            return (Criteria) this;
        }

        public Criteria andDepentsIdIn(List<Integer> values) {
            addCriterion("depents_id in", values, "depentsId");
            return (Criteria) this;
        }

        public Criteria andDepentsIdNotIn(List<Integer> values) {
            addCriterion("depents_id not in", values, "depentsId");
            return (Criteria) this;
        }

        public Criteria andDepentsIdBetween(Integer value1, Integer value2) {
            addCriterion("depents_id between", value1, value2, "depentsId");
            return (Criteria) this;
        }

        public Criteria andDepentsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("depents_id not between", value1, value2, "depentsId");
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