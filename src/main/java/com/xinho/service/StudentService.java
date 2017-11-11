package com.xinho.service;

import com.xinho.bean.Student;
import com.xinho.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<String> validate(StudentDto studentDto);

    boolean addSingle(StudentDto studentDto);

    int removeStudentById(List<Integer> idList);

    int removeStudentById(Integer studentId);

    int removeStudent(StudentDto studentDto);

    List<StudentDto> searchStudent(StudentDto studentDto);

    List<StudentDto> searchAllStudent();

    StudentDto searchStudentById(Integer id);

    boolean modifyStudentByDto(StudentDto studentDto);

    boolean modifyLogicValidate(StudentDto studentDto);

    boolean modifyIsDataReal(StudentDto studentDto);

    List<String> validateModify(StudentDto studentDto);

    void finishStudentInfo(StudentDto studentDto);

    void updateDorm(Integer dormId, Integer studentDto);

    int[] removeStudentByIdStr(String idStr);

    boolean validateInfoExact(StudentDto studentDto);

    boolean validateAddLogic(StudentDto studentDto);

    StudentDto beanToDto(Student student);
}
