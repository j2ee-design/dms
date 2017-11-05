package com.xinho.service;

import com.xinho.dto.DormitoryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext*.xml"})
public class DormitoryServiceTest {

    @Autowired
    private DormitoryService dormitoryService;

    @Test
    public void addSingle(){
        DormitoryDto dormitoryDto = new DormitoryDto();
        dormitoryDto.setTrueClassId(15070942);
        dormitoryService.addSingle(dormitoryDto);
    }

    @Test
    public void getDormListByClassIdTest(){
        dormitoryService.getDormListByClassId(2).forEach(item-> System.out.println(item.getDormId()));
    }
}
