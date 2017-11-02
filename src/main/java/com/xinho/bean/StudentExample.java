package com.xinho.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * BeanExample 是复杂查询
 * 字段：
 *  1. 通过条件子句排序字符串
 *  2. 不同标志 boolean
 *  3. 内置 Criteria 类，实现对查询条件的操作
 */
public class StudentExample {
    // order -> 命令，顺序，规则

    // clause -> 条款，子句
    protected String orderByClause;
    // distinct -> 不同的
    protected boolean distinct;
    // criteria -> 条件
    protected List<Criteria> oredCriteria;

    public StudentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /*构造器和setter、getter*/
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


    /* or() 方法，添加一个条件对象，并且可以返回*/
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }
    /* Criteria 对象初始化，如果当前条件对象list为空则添加进去*/
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
    /*创建 Criteria，和初始化是有区别的*/
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }
    /*清除方法*/
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


        /*添加条件*/
        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("条件的值不能为空");
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

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(Integer value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(Integer value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(Integer value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(Integer value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(Integer value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<Integer> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<Integer> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(Integer value1, Integer value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(Integer value1, Integer value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andEnroYearIsNull() {
            addCriterion("enro_year is null");
            return (Criteria) this;
        }

        public Criteria andEnroYearIsNotNull() {
            addCriterion("enro_year is not null");
            return (Criteria) this;
        }

        public Criteria andEnroYearEqualTo(Date value) {
            addCriterion("enro_year =", value, "enroYear");
            return (Criteria) this;
        }

        public Criteria andEnroYearNotEqualTo(Date value) {
            addCriterion("enro_year <>", value, "enroYear");
            return (Criteria) this;
        }

        public Criteria andEnroYearGreaterThan(Date value) {
            addCriterion("enro_year >", value, "enroYear");
            return (Criteria) this;
        }

        public Criteria andEnroYearGreaterThanOrEqualTo(Date value) {
            addCriterion("enro_year >=", value, "enroYear");
            return (Criteria) this;
        }

        public Criteria andEnroYearLessThan(Date value) {
            addCriterion("enro_year <", value, "enroYear");
            return (Criteria) this;
        }

        public Criteria andEnroYearLessThanOrEqualTo(Date value) {
            addCriterion("enro_year <=", value, "enroYear");
            return (Criteria) this;
        }

        public Criteria andEnroYearIn(List<Date> values) {
            addCriterion("enro_year in", values, "enroYear");
            return (Criteria) this;
        }

        public Criteria andEnroYearNotIn(List<Date> values) {
            addCriterion("enro_year not in", values, "enroYear");
            return (Criteria) this;
        }

        public Criteria andEnroYearBetween(Date value1, Date value2) {
            addCriterion("enro_year between", value1, value2, "enroYear");
            return (Criteria) this;
        }

        public Criteria andEnroYearNotBetween(Date value1, Date value2) {
            addCriterion("enro_year not between", value1, value2, "enroYear");
            return (Criteria) this;
        }

        public Criteria andDicGradeIsNull() {
            addCriterion("dic_grade is null");
            return (Criteria) this;
        }

        public Criteria andDicGradeIsNotNull() {
            addCriterion("dic_grade is not null");
            return (Criteria) this;
        }

        public Criteria andDicGradeEqualTo(Integer value) {
            addCriterion("dic_grade =", value, "dicGrade");
            return (Criteria) this;
        }

        public Criteria andDicGradeNotEqualTo(Integer value) {
            addCriterion("dic_grade <>", value, "dicGrade");
            return (Criteria) this;
        }

        public Criteria andDicGradeGreaterThan(Integer value) {
            addCriterion("dic_grade >", value, "dicGrade");
            return (Criteria) this;
        }

        public Criteria andDicGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dic_grade >=", value, "dicGrade");
            return (Criteria) this;
        }

        public Criteria andDicGradeLessThan(Integer value) {
            addCriterion("dic_grade <", value, "dicGrade");
            return (Criteria) this;
        }

        public Criteria andDicGradeLessThanOrEqualTo(Integer value) {
            addCriterion("dic_grade <=", value, "dicGrade");
            return (Criteria) this;
        }

        public Criteria andDicGradeIn(List<Integer> values) {
            addCriterion("dic_grade in", values, "dicGrade");
            return (Criteria) this;
        }

        public Criteria andDicGradeNotIn(List<Integer> values) {
            addCriterion("dic_grade not in", values, "dicGrade");
            return (Criteria) this;
        }

        public Criteria andDicGradeBetween(Integer value1, Integer value2) {
            addCriterion("dic_grade between", value1, value2, "dicGrade");
            return (Criteria) this;
        }

        public Criteria andDicGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("dic_grade not between", value1, value2, "dicGrade");
            return (Criteria) this;
        }

        public Criteria andAcademyIdIsNull() {
            addCriterion("academy_id is null");
            return (Criteria) this;
        }

        public Criteria andAcademyIdIsNotNull() {
            addCriterion("academy_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcademyIdEqualTo(Integer value) {
            addCriterion("academy_id =", value, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdNotEqualTo(Integer value) {
            addCriterion("academy_id <>", value, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdGreaterThan(Integer value) {
            addCriterion("academy_id >", value, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("academy_id >=", value, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdLessThan(Integer value) {
            addCriterion("academy_id <", value, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdLessThanOrEqualTo(Integer value) {
            addCriterion("academy_id <=", value, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdIn(List<Integer> values) {
            addCriterion("academy_id in", values, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdNotIn(List<Integer> values) {
            addCriterion("academy_id not in", values, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdBetween(Integer value1, Integer value2) {
            addCriterion("academy_id between", value1, value2, "academyId");
            return (Criteria) this;
        }

        public Criteria andAcademyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("academy_id not between", value1, value2, "academyId");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNull() {
            addCriterion("major_id is null");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNotNull() {
            addCriterion("major_id is not null");
            return (Criteria) this;
        }

        public Criteria andMajorIdEqualTo(Integer value) {
            addCriterion("major_id =", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotEqualTo(Integer value) {
            addCriterion("major_id <>", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThan(Integer value) {
            addCriterion("major_id >", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("major_id >=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThan(Integer value) {
            addCriterion("major_id <", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThanOrEqualTo(Integer value) {
            addCriterion("major_id <=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdIn(List<Integer> values) {
            addCriterion("major_id in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotIn(List<Integer> values) {
            addCriterion("major_id not in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdBetween(Integer value1, Integer value2) {
            addCriterion("major_id between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("major_id not between", value1, value2, "majorId");
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

        public Criteria andDormStatusIsNull() {
            addCriterion("dorm_status is null");
            return (Criteria) this;
        }

        public Criteria andDormStatusIsNotNull() {
            addCriterion("dorm_status is not null");
            return (Criteria) this;
        }

        public Criteria andDormStatusEqualTo(Integer value) {
            addCriterion("dorm_status =", value, "dormStatus");
            return (Criteria) this;
        }

        public Criteria andDormStatusNotEqualTo(Integer value) {
            addCriterion("dorm_status <>", value, "dormStatus");
            return (Criteria) this;
        }

        public Criteria andDormStatusGreaterThan(Integer value) {
            addCriterion("dorm_status >", value, "dormStatus");
            return (Criteria) this;
        }

        public Criteria andDormStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("dorm_status >=", value, "dormStatus");
            return (Criteria) this;
        }

        public Criteria andDormStatusLessThan(Integer value) {
            addCriterion("dorm_status <", value, "dormStatus");
            return (Criteria) this;
        }

        public Criteria andDormStatusLessThanOrEqualTo(Integer value) {
            addCriterion("dorm_status <=", value, "dormStatus");
            return (Criteria) this;
        }

        public Criteria andDormStatusIn(List<Integer> values) {
            addCriterion("dorm_status in", values, "dormStatus");
            return (Criteria) this;
        }

        public Criteria andDormStatusNotIn(List<Integer> values) {
            addCriterion("dorm_status not in", values, "dormStatus");
            return (Criteria) this;
        }

        public Criteria andDormStatusBetween(Integer value1, Integer value2) {
            addCriterion("dorm_status between", value1, value2, "dormStatus");
            return (Criteria) this;
        }

        public Criteria andDormStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("dorm_status not between", value1, value2, "dormStatus");
            return (Criteria) this;
        }

        public Criteria andStuStatusIsNull() {
            addCriterion("stu_status is null");
            return (Criteria) this;
        }

        public Criteria andStuStatusIsNotNull() {
            addCriterion("stu_status is not null");
            return (Criteria) this;
        }

        public Criteria andStuStatusEqualTo(Integer value) {
            addCriterion("stu_status =", value, "stuStatus");
            return (Criteria) this;
        }

        public Criteria andStuStatusNotEqualTo(Integer value) {
            addCriterion("stu_status <>", value, "stuStatus");
            return (Criteria) this;
        }

        public Criteria andStuStatusGreaterThan(Integer value) {
            addCriterion("stu_status >", value, "stuStatus");
            return (Criteria) this;
        }

        public Criteria andStuStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("stu_status >=", value, "stuStatus");
            return (Criteria) this;
        }

        public Criteria andStuStatusLessThan(Integer value) {
            addCriterion("stu_status <", value, "stuStatus");
            return (Criteria) this;
        }

        public Criteria andStuStatusLessThanOrEqualTo(Integer value) {
            addCriterion("stu_status <=", value, "stuStatus");
            return (Criteria) this;
        }

        public Criteria andStuStatusIn(List<Integer> values) {
            addCriterion("stu_status in", values, "stuStatus");
            return (Criteria) this;
        }

        public Criteria andStuStatusNotIn(List<Integer> values) {
            addCriterion("stu_status not in", values, "stuStatus");
            return (Criteria) this;
        }

        public Criteria andStuStatusBetween(Integer value1, Integer value2) {
            addCriterion("stu_status between", value1, value2, "stuStatus");
            return (Criteria) this;
        }

        public Criteria andStuStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("stu_status not between", value1, value2, "stuStatus");
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