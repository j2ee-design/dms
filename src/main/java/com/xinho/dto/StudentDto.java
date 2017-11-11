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

    // 尼玛，写到最后发现忘了加 dormName 字段
    /**
     * 真正的宿舍号（唉，唉，唉）(草泥马了，这里的宿舍不能是宿舍表里面的宿舍号！)dormName = apartId + dormId
     */
    private Integer DormName;

    public StudentDto(Integer id, String name, Integer gender, Date enroYear, Integer dicGrade, Integer academyId,
                      Integer majorId, Integer classId, Integer dormId, Integer dormStatus,
                      Integer stuStatus, String academyName, String majorName, Integer className) {
        super(id, name, gender, enroYear, dicGrade, academyId, majorId, classId, dormId, dormStatus, stuStatus);
        this.academyName = academyName;
        this.majorName = majorName;
        this.className = className;
    }

    public StudentDto(String academyName, String majorName, Integer className, Integer dormName) {
        this.academyName = academyName;
        this.majorName = majorName;
        this.className = className;
        DormName = dormName;
    }

    public StudentDto(Integer id, String name, Integer gender, Date enroYear, Integer dicGrade, Integer academyId,
                      Integer majorId, Integer classId, Integer dormId, Integer dormStatus, Integer stuStatus,
                      String academyName, String majorName, Integer className, Integer dormName) {
        super(id, name, gender, enroYear, dicGrade, academyId, majorId, classId, dormId, dormStatus, stuStatus);
        this.academyName = academyName;
        this.majorName = majorName;
        this.className = className;
        DormName = dormName;
    }
}
