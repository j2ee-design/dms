package com.xinho.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext*.xml"})
public class MajorServiceTest {

    @Autowired
    private MajorService majorService;

    @Test
    public void getMajorsByPidTest(){
        majorService.getMajorsByPid(1).forEach(item-> System.out.println(item.getName()));
    }
}
