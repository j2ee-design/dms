package com.xinho.service;

import com.xinho.bean.Student;
import com.xinho.dto.StudentDto;

import java.util.List;

public interface StudentService {
    /**
     * 获取表单校检错误输入字段，以空格分开
     * @param studentDto 校检的 dto
     * @return 结果
     */
    public List<String> validate(StudentDto studentDto);

    /**
     * 添加一个学生
     * @param studentDto
     * @return 添加结果
     */
    public boolean addSingle(StudentDto studentDto);
}
