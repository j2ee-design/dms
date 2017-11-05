package com.xinho.dto;

import com.xinho.bean.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data public class StudentDto extends Student{

    // 学院名称字符串（bean中保存的是 ID，我们收到的是名称）
    private String academyName;

    // 专业名称字符串（同上）
    private String majorName;

    // 真正的班号（草泥马。。。）
    private Integer className;

    public StudentDto(Integer id, String name, Integer gender, Date enroYear, Integer dicGrade, Integer academyId, Integer majorId, Integer classId, Integer dormId, Integer dormStatus, Integer stuStatus, String academyName, String majorName, Integer className) {
        super(id, name, gender, enroYear, dicGrade, academyId, majorId, classId, dormId, dormStatus, stuStatus);
        this.academyName = academyName;
        this.majorName = majorName;
        this.className = className;
    }
}
