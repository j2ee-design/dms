package com.xinho.bean;

import java.util.ArrayList;
import java.util.List;

public class DormitoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DormitoryExample() {
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

        public Criteria andDormIdIsNull() {
            addCriterion("dorm_id is null");
            return (Criteria) this;
        }

        public Criteria andDormIdIsNotNull() {
            addCriterion("dorm_id is not null");
            return (Criteria) this;
        }

        public Criteria andDormIdEqualTo(Integer value) {
            addCriterion("dorm_id =", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdNotEqualTo(Integer value) {
            addCriterion("dorm_id <>", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdGreaterThan(Integer value) {
            addCriterion("dorm_id >", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dorm_id >=", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdLessThan(Integer value) {
            addCriterion("dorm_id <", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdLessThanOrEqualTo(Integer value) {
            addCriterion("dorm_id <=", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdIn(List<Integer> values) {
            addCriterion("dorm_id in", values, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdNotIn(List<Integer> values) {
            addCriterion("dorm_id not in", values, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdBetween(Integer value1, Integer value2) {
            addCriterion("dorm_id between", value1, value2, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dorm_id not between", value1, value2, "dormId");
            return (Criteria) this;
        }

        public Criteria andApartIdIsNull() {
            addCriterion("apart_id is null");
            return (Criteria) this;
        }

        public Criteria andApartIdIsNotNull() {
            addCriterion("apart_id is not null");
            return (Criteria) this;
        }

        public Criteria andApartIdEqualTo(Integer value) {
            addCriterion("apart_id =", value, "apartId");
            return (Criteria) this;
        }

        public Criteria andApartIdNotEqualTo(Integer value) {
            addCriterion("apart_id <>", value, "apartId");
            return (Criteria) this;
        }

        public Criteria andApartIdGreaterThan(Integer value) {
            addCriterion("apart_id >", value, "apartId");
            return (Criteria) this;
        }

        public Criteria andApartIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("apart_id >=", value, "apartId");
            return (Criteria) this;
        }

        public Criteria andApartIdLessThan(Integer value) {
            addCriterion("apart_id <", value, "apartId");
            return (Criteria) this;
        }

        public Criteria andApartIdLessThanOrEqualTo(Integer value) {
            addCriterion("apart_id <=", value, "apartId");
            return (Criteria) this;
        }

        public Criteria andApartIdIn(List<Integer> values) {
            addCriterion("apart_id in", values, "apartId");
            return (Criteria) this;
        }

        public Criteria andApartIdNotIn(List<Integer> values) {
            addCriterion("apart_id not in", values, "apartId");
            return (Criteria) this;
        }

        public Criteria andApartIdBetween(Integer value1, Integer value2) {
            addCriterion("apart_id between", value1, value2, "apartId");
            return (Criteria) this;
        }

        public Criteria andApartIdNotBetween(Integer value1, Integer value2) {
            addCriterion("apart_id not between", value1, value2, "apartId");
            return (Criteria) this;
        }

        public Criteria andDicDormTypeIsNull() {
            addCriterion("dic_dorm_type is null");
            return (Criteria) this;
        }

        public Criteria andDicDormTypeIsNotNull() {
            addCriterion("dic_dorm_type is not null");
            return (Criteria) this;
        }

        public Criteria andDicDormTypeEqualTo(Integer value) {
            addCriterion("dic_dorm_type =", value, "dicDormType");
            return (Criteria) this;
        }

        public Criteria andDicDormTypeNotEqualTo(Integer value) {
            addCriterion("dic_dorm_type <>", value, "dicDormType");
            return (Criteria) this;
        }

        public Criteria andDicDormTypeGreaterThan(Integer value) {
            addCriterion("dic_dorm_type >", value, "dicDormType");
            return (Criteria) this;
        }

        public Criteria andDicDormTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dic_dorm_type >=", value, "dicDormType");
            return (Criteria) this;
        }

        public Criteria andDicDormTypeLessThan(Integer value) {
            addCriterion("dic_dorm_type <", value, "dicDormType");
            return (Criteria) this;
        }

        public Criteria andDicDormTypeLessThanOrEqualTo(Integer value) {
            addCriterion("dic_dorm_type <=", value, "dicDormType");
            return (Criteria) this;
        }

        public Criteria andDicDormTypeIn(List<Integer> values) {
            addCriterion("dic_dorm_type in", values, "dicDormType");
            return (Criteria) this;
        }

        public Criteria andDicDormTypeNotIn(List<Integer> values) {
            addCriterion("dic_dorm_type not in", values, "dicDormType");
            return (Criteria) this;
        }

        public Criteria andDicDormTypeBetween(Integer value1, Integer value2) {
            addCriterion("dic_dorm_type between", value1, value2, "dicDormType");
            return (Criteria) this;
        }

        public Criteria andDicDormTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("dic_dorm_type not between", value1, value2, "dicDormType");
            return (Criteria) this;
        }

        public Criteria andChiefIdIsNull() {
            addCriterion("chief_id is null");
            return (Criteria) this;
        }

        public Criteria andChiefIdIsNotNull() {
            addCriterion("chief_id is not null");
            return (Criteria) this;
        }

        public Criteria andChiefIdEqualTo(Integer value) {
            addCriterion("chief_id =", value, "chiefId");
            return (Criteria) this;
        }

        public Criteria andChiefIdNotEqualTo(Integer value) {
            addCriterion("chief_id <>", value, "chiefId");
            return (Criteria) this;
        }

        public Criteria andChiefIdGreaterThan(Integer value) {
            addCriterion("chief_id >", value, "chiefId");
            return (Criteria) this;
        }

        public Criteria andChiefIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("chief_id >=", value, "chiefId");
            return (Criteria) this;
        }

        public Criteria andChiefIdLessThan(Integer value) {
            addCriterion("chief_id <", value, "chiefId");
            return (Criteria) this;
        }

        public Criteria andChiefIdLessThanOrEqualTo(Integer value) {
            addCriterion("chief_id <=", value, "chiefId");
            return (Criteria) this;
        }

        public Criteria andChiefIdIn(List<Integer> values) {
            addCriterion("chief_id in", values, "chiefId");
            return (Criteria) this;
        }

        public Criteria andChiefIdNotIn(List<Integer> values) {
            addCriterion("chief_id not in", values, "chiefId");
            return (Criteria) this;
        }

        public Criteria andChiefIdBetween(Integer value1, Integer value2) {
            addCriterion("chief_id between", value1, value2, "chiefId");
            return (Criteria) this;
        }

        public Criteria andChiefIdNotBetween(Integer value1, Integer value2) {
            addCriterion("chief_id not between", value1, value2, "chiefId");
            return (Criteria) this;
        }

        public Criteria andAllBedIsNull() {
            addCriterion("all_bed is null");
            return (Criteria) this;
        }

        public Criteria andAllBedIsNotNull() {
            addCriterion("all_bed is not null");
            return (Criteria) this;
        }

        public Criteria andAllBedEqualTo(Integer value) {
            addCriterion("all_bed =", value, "allBed");
            return (Criteria) this;
        }

        public Criteria andAllBedNotEqualTo(Integer value) {
            addCriterion("all_bed <>", value, "allBed");
            return (Criteria) this;
        }

        public Criteria andAllBedGreaterThan(Integer value) {
            addCriterion("all_bed >", value, "allBed");
            return (Criteria) this;
        }

        public Criteria andAllBedGreaterThanOrEqualTo(Integer value) {
            addCriterion("all_bed >=", value, "allBed");
            return (Criteria) this;
        }

        public Criteria andAllBedLessThan(Integer value) {
            addCriterion("all_bed <", value, "allBed");
            return (Criteria) this;
        }

        public Criteria andAllBedLessThanOrEqualTo(Integer value) {
            addCriterion("all_bed <=", value, "allBed");
            return (Criteria) this;
        }

        public Criteria andAllBedIn(List<Integer> values) {
            addCriterion("all_bed in", values, "allBed");
            return (Criteria) this;
        }

        public Criteria andAllBedNotIn(List<Integer> values) {
            addCriterion("all_bed not in", values, "allBed");
            return (Criteria) this;
        }

        public Criteria andAllBedBetween(Integer value1, Integer value2) {
            addCriterion("all_bed between", value1, value2, "allBed");
            return (Criteria) this;
        }

        public Criteria andAllBedNotBetween(Integer value1, Integer value2) {
            addCriterion("all_bed not between", value1, value2, "allBed");
            return (Criteria) this;
        }

        public Criteria andUsedBedIsNull() {
            addCriterion("used_bed is null");
            return (Criteria) this;
        }

        public Criteria andUsedBedIsNotNull() {
            addCriterion("used_bed is not null");
            return (Criteria) this;
        }

        public Criteria andUsedBedEqualTo(Integer value) {
            addCriterion("used_bed =", value, "usedBed");
            return (Criteria) this;
        }

        public Criteria andUsedBedNotEqualTo(Integer value) {
            addCriterion("used_bed <>", value, "usedBed");
            return (Criteria) this;
        }

        public Criteria andUsedBedGreaterThan(Integer value) {
            addCriterion("used_bed >", value, "usedBed");
            return (Criteria) this;
        }

        public Criteria andUsedBedGreaterThanOrEqualTo(Integer value) {
            addCriterion("used_bed >=", value, "usedBed");
            return (Criteria) this;
        }

        public Criteria andUsedBedLessThan(Integer value) {
            addCriterion("used_bed <", value, "usedBed");
            return (Criteria) this;
        }

        public Criteria andUsedBedLessThanOrEqualTo(Integer value) {
            addCriterion("used_bed <=", value, "usedBed");
            return (Criteria) this;
        }

        public Criteria andUsedBedIn(List<Integer> values) {
            addCriterion("used_bed in", values, "usedBed");
            return (Criteria) this;
        }

        public Criteria andUsedBedNotIn(List<Integer> values) {
            addCriterion("used_bed not in", values, "usedBed");
            return (Criteria) this;
        }

        public Criteria andUsedBedBetween(Integer value1, Integer value2) {
            addCriterion("used_bed between", value1, value2, "usedBed");
            return (Criteria) this;
        }

        public Criteria andUsedBedNotBetween(Integer value1, Integer value2) {
            addCriterion("used_bed not between", value1, value2, "usedBed");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNull() {
            addCriterion("class_id is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(Integer value) {
            addCriterion("class_id =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(Integer value) {
            addCriterion("class_id <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(Integer value) {
            addCriterion("class_id >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_id >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(Integer value) {
            addCriterion("class_id <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("class_id <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<Integer> values) {
            addCriterion("class_id in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<Integer> values) {
            addCriterion("class_id not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(Integer value1, Integer value2) {
            addCriterion("class_id between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("class_id not between", value1, value2, "classId");
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