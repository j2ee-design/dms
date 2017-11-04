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

    /**
     * 根据ID列表批量删除学生，不回滚
     * @param idList
     * @return 成功删除的数量
     */
    int removeStudentById(List<Integer> idList);

    /**
     * 根据ID删除单个学生
     * @param studentId
     * @return 成功删除的数量
     */
    int removeStudentById(Integer studentId);


    /**
     * 根据example删除学生
     * @param studentDto
     * @return 成功删除的数量
     */
    int removeStudent(StudentDto studentDto);

    /**
     * 根据dto查找学生
     * @param studentDto
     * @return
     */
    List<Student> searchStudent(StudentDto studentDto);

    /**
     * 根据 ID 查找学生
     * @param id
     * @return
     */
    Student searchStudentById(Integer id);
}
