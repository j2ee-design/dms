package com.xinho.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext*.xml"})
public class DicServiceTest {

    @Autowired
    private DicService dicService;

    @Test
    public void getDicByPIdTest(){
        System.out.println("lllla");
        dicService.getDicByPId(10001).forEach(item-> System.out.println(item.getValue()));
        System.out.println("lllllb");
    }
}
