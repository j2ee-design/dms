package com.xinho.bean;

public class Apartment {
    private Integer id;

    private String name;

    private Integer dicApartType;

    private Integer floorNum;

    private Integer dormNum;

    private Integer usedDorm;

    private Integer dormBedNum;

    private Integer allBed;

    private Integer remainBed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDicApartType() {
        return dicApartType;
    }

    public void setDicApartType(Integer dicApartType) {
        this.dicApartType = dicApartType;
    }

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public Integer getDormNum() {
        return dormNum;
    }

    public void setDormNum(Integer dormNum) {
        this.dormNum = dormNum;
    }

    public Integer getUsedDorm() {
        return usedDorm;
    }

    public void setUsedDorm(Integer usedDorm) {
        this.usedDorm = usedDorm;
    }

    public Integer getDormBedNum() {
        return dormBedNum;
    }

    public void setDormBedNum(Integer dormBedNum) {
        this.dormBedNum = dormBedNum;
    }

    public Integer getAllBed() {
        return allBed;
    }

    public void setAllBed(Integer allBed) {
        this.allBed = allBed;
    }

    public Integer getRemainBed() {
        return remainBed;
    }

    public void setRemainBed(Integer remainBed) {
        this.remainBed = remainBed;
    }
}