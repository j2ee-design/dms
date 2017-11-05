package com.xinho.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext*.xml"})
public class ClassServiceTest {

    @Autowired
    private ClassService classService;

    @Test
    public void getClassesByMajorIdTest(){
        classService.getClassesByMajorIdAndGradeId(2,10014).forEach(item-> System.out.println(item.getClassId()));
    }
}
