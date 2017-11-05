package com.xinho.service;

import com.xinho.dto.AcademyDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/*.xml"})
public class AcademyServiceTest {

    @Autowired
    private AcademyService academyService;

    @Test
    public void getAllAcademyTest(){
        List<AcademyDto> academyDtoList = academyService.getAllAcademy();
        for (AcademyDto academyDto: academyDtoList) {
            System.out.println(academyDto.toString());
        }
    }
}
