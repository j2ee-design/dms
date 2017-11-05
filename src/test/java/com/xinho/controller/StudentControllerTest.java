package com.xinho.controller;

import com.xinho.dto.StudentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext*.xml"})
public class StudentControllerTest {

    @Autowired
    private StudentController studentController;


    @Test
    public void getStudentTest(){
        System.out.println(studentController.getStudent(new StudentDto()).toString());
    }

    @Test
    public void initListTest(){
        Model model = new BindingAwareModelMap();
        studentController.initList(model);
        System.out.println(model.toString());
    }


}
