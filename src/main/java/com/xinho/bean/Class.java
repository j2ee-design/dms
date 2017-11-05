package com.xinho.bean;

import lombok.Data;

@Data public class Class {
    private Integer id;

    private Integer majorId;

    private Integer classId;

    private Integer stuNum;

    /**
     * 该班级所在年级的字典ID
     */
    private Integer dicGrade;

}