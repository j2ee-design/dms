package com.xinho.service;

import com.xinho.bean.Student;
import com.xinho.dto.StudentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext*.xml"})
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void addSingleTest(){
        StudentDto studentDto = new StudentDto(50000000,"白璐",1,new Date(),10014,0,0,15070942,0,0,0,"大数据学院","物联网工程",1507094236);
        System.out.println(studentService.addSingle(studentDto));
    }

    @Test
    public void removeStudentByIdTest(){
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(50000000);
        idList.add(10000000);
        studentService.removeStudentById(idList);
    }

    @Test
    public void searchStudentTest(){
        StudentDto studentDto = new StudentDto();
        studentDto.setGender(1);
        List<StudentDto> studentDtoList = studentService.searchStudent(studentDto);
        System.out.println(studentDtoList.toString());
    }

    @Test
    public void searchStudentByIdTemp(){
        System.out.println(studentService.searchStudentById(10000000));
    }


}
