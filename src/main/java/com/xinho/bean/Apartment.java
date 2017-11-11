package com.xinho.bean;

import lombok.Data;

@Data public class Apartment {
    private Integer id;

    private String name;

    private Integer dicApartType;

    private Integer floorNum;

    private Integer dormNum;

    private Integer usedDorm;

    private Integer dormBedNum;

    private Integer allBed;

    private Integer remainBed;

}