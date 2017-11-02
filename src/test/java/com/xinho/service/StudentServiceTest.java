package com.xinho.service;

import com.xinho.dto.StudentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext*.xml"})
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void addSingleTest(){
        StudentDto studentDto = new StudentDto(50000000,"白璐",1,new Date(),10014,0,0,15070942,0,0,0,"大数据学院","物联网工程");
        System.out.println(studentService.addSingle(studentDto));
    }
}
