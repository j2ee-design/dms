package com.xinho.bean;

public class Grade {
    private Integer id;

    private Integer dicType;

    private Integer gradeTimes;

    private Integer dormId;

    private Float score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDicType() {
        return dicType;
    }

    public void setDicType(Integer dicType) {
        this.dicType = dicType;
    }

    public Integer getGradeTimes() {
        return gradeTimes;
    }

    public void setGradeTimes(Integer gradeTimes) {
        this.gradeTimes = gradeTimes;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}