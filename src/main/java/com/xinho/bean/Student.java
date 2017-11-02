package com.xinho.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data public class Student extends BaseBean{
    private Integer id;

    private String name;

    private Integer gender;

    private Date enroYear;

    private Integer dicGrade;

    private Integer academyId;

    private Integer majorId;

    private Integer classId;

    private Integer dormId;

    private Integer dormStatus;

    private Integer stuStatus;

}