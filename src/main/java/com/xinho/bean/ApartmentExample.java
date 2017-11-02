package com.xinho.bean;

import java.util.ArrayList;
import java.util.List;

public class ApartmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApartmentExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDicApartTypeIsNull() {
            addCriterion("dic_apart_type is null");
            return (Criteria) this;
        }

        public Criteria andDicApartTypeIsNotNull() {
            addCriterion("dic_apart_type is not null");
            return (Criteria) this;
        }

        public Criteria andDicApartTypeEqualTo(Integer value) {
            addCriterion("dic_apart_type =", value, "dicApartType");
            return (Criteria) this;
        }

        public Criteria andDicApartTypeNotEqualTo(Integer value) {
            addCriterion("dic_apart_type <>", value, "dicApartType");
            return (Criteria) this;
        }

        public Criteria andDicApartTypeGreaterThan(Integer value) {
            addCriterion("dic_apart_type >", value, "dicApartType");
            return (Criteria) this;
        }

        public Criteria andDicApartTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dic_apart_type >=", value, "dicApartType");
            return (Criteria) this;
        }

        public Criteria andDicApartTypeLessThan(Integer value) {
            addCriterion("dic_apart_type <", value, "dicApartType");
            return (Criteria) this;
        }

        public Criteria andDicApartTypeLessThanOrEqualTo(Integer value) {
            addCriterion("dic_apart_type <=", value, "dicApartType");
            return (Criteria) this;
        }

        public Criteria andDicApartTypeIn(List<Integer> values) {
            addCriterion("dic_apart_type in", values, "dicApartType");
            return (Criteria) this;
        }

        public Criteria andDicApartTypeNotIn(List<Integer> values) {
            addCriterion("dic_apart_type not in", values, "dicApartType");
            return (Criteria) this;
        }

        public Criteria andDicApartTypeBetween(Integer value1, Integer value2) {
            addCriterion("dic_apart_type between", value1, value2, "dicApartType");
            return (Criteria) this;
        }

        public Criteria andDicApartTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("dic_apart_type not between", value1, value2, "dicApartType");
            return (Criteria) this;
        }

        public Criteria andFloorNumIsNull() {
            addCriterion("floor_num is null");
            return (Criteria) this;
        }

        public Criteria andFloorNumIsNotNull() {
            addCriterion("floor_num is not null");
            return (Criteria) this;
        }

        public Criteria andFloorNumEqualTo(Integer value) {
            addCriterion("floor_num =", value, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumNotEqualTo(Integer value) {
            addCriterion("floor_num <>", value, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumGreaterThan(Integer value) {
            addCriterion("floor_num >", value, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("floor_num >=", value, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumLessThan(Integer value) {
            addCriterion("floor_num <", value, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumLessThanOrEqualTo(Integer value) {
            addCriterion("floor_num <=", value, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumIn(List<Integer> values) {
            addCriterion("floor_num in", values, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumNotIn(List<Integer> values) {
            addCriterion("floor_num not in", values, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumBetween(Integer value1, Integer value2) {
            addCriterion("floor_num between", value1, value2, "floorNum");
            return (Criteria) this;
        }

        public Criteria andFloorNumNotBetween(Integer value1, Integer value2) {
            addCriterion("floor_num not between", value1, value2, "floorNum");
            return (Criteria) this;
        }

        public Criteria andDormNumIsNull() {
            addCriterion("dorm_num is null");
            return (Criteria) this;
        }

        public Criteria andDormNumIsNotNull() {
            addCriterion("dorm_num is not null");
            return (Criteria) this;
        }

        public Criteria andDormNumEqualTo(Integer value) {
            addCriterion("dorm_num =", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumNotEqualTo(Integer value) {
            addCriterion("dorm_num <>", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumGreaterThan(Integer value) {
            addCriterion("dorm_num >", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("dorm_num >=", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumLessThan(Integer value) {
            addCriterion("dorm_num <", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumLessThanOrEqualTo(Integer value) {
            addCriterion("dorm_num <=", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumIn(List<Integer> values) {
            addCriterion("dorm_num in", values, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumNotIn(List<Integer> values) {
            addCriterion("dorm_num not in", values, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumBetween(Integer value1, Integer value2) {
            addCriterion("dorm_num between", value1, value2, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumNotBetween(Integer value1, Integer value2) {
            addCriterion("dorm_num not between", value1, value2, "dormNum");
            return (Criteria) this;
        }

        public Criteria andUsedDormIsNull() {
            addCriterion("used_dorm is null");
            return (Criteria) this;
        }

        public Criteria andUsedDormIsNotNull() {
            addCriterion("used_dorm is not null");
            return (Criteria) this;
        }

        public Criteria andUsedDormEqualTo(Integer value) {
            addCriterion("used_dorm =", value, "usedDorm");
            return (Criteria) this;
        }

        public Criteria andUsedDormNotEqualTo(Integer value) {
            addCriterion("used_dorm <>", value, "usedDorm");
            return (Criteria) this;
        }

        public Criteria andUsedDormGreaterThan(Integer value) {
            addCriterion("used_dorm >", value, "usedDorm");
            return (Criteria) this;
        }

        public Criteria andUsedDormGreaterThanOrEqualTo(Integer value) {
            addCriterion("used_dorm >=", value, "usedDorm");
            return (Criteria) this;
        }

        public Criteria andUsedDormLessThan(Integer value) {
            addCriterion("used_dorm <", value, "usedDorm");
            return (Criteria) this;
        }

        public Criteria andUsedDormLessThanOrEqualTo(Integer value) {
            addCriterion("used_dorm <=", value, "usedDorm");
            return (Criteria) this;
        }

        public Criteria andUsedDormIn(List<Integer> values) {
            addCriterion("used_dorm in", values, "usedDorm");
            return (Criteria) this;
        }

        public Criteria andUsedDormNotIn(List<Integer> values) {
            addCriterion("used_dorm not in", values, "usedDorm");
            return (Criteria) this;
        }

        public Criteria andUsedDormBetween(Integer value1, Integer value2) {
            addCriterion("used_dorm between", value1, value2, "usedDorm");
            return (Criteria) this;
        }

        public Criteria andUsedDormNotBetween(Integer value1, Integer value2) {
            addCriterion("used_dorm not between", value1, value2, "usedDorm");
            return (Criteria) this;
        }

        public Criteria andDormBedNumIsNull() {
            addCriterion("dorm_bed_num is null");
            return (Criteria) this;
        }

        public Criteria andDormBedNumIsNotNull() {
            addCriterion("dorm_bed_num is not null");
            return (Criteria) this;
        }

        public Criteria andDormBedNumEqualTo(Integer value) {
            addCriterion("dorm_bed_num =", value, "dormBedNum");
            return (Criteria) this;
        }

        public Criteria andDormBedNumNotEqualTo(Integer value) {
            addCriterion("dorm_bed_num <>", value, "dormBedNum");
            return (Criteria) this;
        }

        public Criteria andDormBedNumGreaterThan(Integer value) {
            addCriterion("dorm_bed_num >", value, "dormBedNum");
            return (Criteria) this;
        }

        public Criteria andDormBedNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("dorm_bed_num >=", value, "dormBedNum");
            return (Criteria) this;
        }

        public Criteria andDormBedNumLessThan(Integer value) {
            addCriterion("dorm_bed_num <", value, "dormBedNum");
            return (Criteria) this;
        }

        public Criteria andDormBedNumLessThanOrEqualTo(Integer value) {
            addCriterion("dorm_bed_num <=", value, "dormBedNum");
            return (Criteria) this;
        }

        public Criteria andDormBedNumIn(List<Integer> values) {
            addCriterion("dorm_bed_num in", values, "dormBedNum");
            return (Criteria) this;
        }

        public Criteria andDormBedNumNotIn(List<Integer> values) {
            addCriterion("dorm_bed_num not in", values, "dormBedNum");
            return (Criteria) this;
        }

        public Criteria andDormBedNumBetween(Integer value1, Integer value2) {
            addCriterion("dorm_bed_num between", value1, value2, "dormBedNum");
            return (Criteria) this;
        }

        public Criteria andDormBedNumNotBetween(Integer value1, Integer value2) {
            addCriterion("dorm_bed_num not between", value1, value2, "dormBedNum");
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

        public Criteria andRemainBedIsNull() {
            addCriterion("remain_bed is null");
            return (Criteria) this;
        }

        public Criteria andRemainBedIsNotNull() {
            addCriterion("remain_bed is not null");
            return (Criteria) this;
        }

        public Criteria andRemainBedEqualTo(Integer value) {
            addCriterion("remain_bed =", value, "remainBed");
            return (Criteria) this;
        }

        public Criteria andRemainBedNotEqualTo(Integer value) {
            addCriterion("remain_bed <>", value, "remainBed");
            return (Criteria) this;
        }

        public Criteria andRemainBedGreaterThan(Integer value) {
            addCriterion("remain_bed >", value, "remainBed");
            return (Criteria) this;
        }

        public Criteria andRemainBedGreaterThanOrEqualTo(Integer value) {
            addCriterion("remain_bed >=", value, "remainBed");
            return (Criteria) this;
        }

        public Criteria andRemainBedLessThan(Integer value) {
            addCriterion("remain_bed <", value, "remainBed");
            return (Criteria) this;
        }

        public Criteria andRemainBedLessThanOrEqualTo(Integer value) {
            addCriterion("remain_bed <=", value, "remainBed");
            return (Criteria) this;
        }

        public Criteria andRemainBedIn(List<Integer> values) {
            addCriterion("remain_bed in", values, "remainBed");
            return (Criteria) this;
        }

        public Criteria andRemainBedNotIn(List<Integer> values) {
            addCriterion("remain_bed not in", values, "remainBed");
            return (Criteria) this;
        }

        public Criteria andRemainBedBetween(Integer value1, Integer value2) {
            addCriterion("remain_bed between", value1, value2, "remainBed");
            return (Criteria) this;
        }

        public Criteria andRemainBedNotBetween(Integer value1, Integer value2) {
            addCriterion("remain_bed not between", value1, value2, "remainBed");
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