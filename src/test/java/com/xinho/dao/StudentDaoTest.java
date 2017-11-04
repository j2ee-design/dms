package com.xinho.dao;

import com.xinho.bean.Student;
import com.xinho.bean.StudentExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext*.xml"})
public class StudentDaoTest {
    @Autowired
    private StudentDao studentDao;

    @Test
    public void insertTest(){
        Student student = new Student(10000000,"江北",0,new Date(),10014,1,1,1,0,0,0);
        studentDao.insert(student);
    }

    @Test
    public void deleteTest(){
        studentDao.deleteByPrimaryKey(new Integer(10000));
    }

    @Test
    public void searchByIdTest(){
        Student student =  studentDao.selectByPrimaryKey(new Integer(1507094231));
        System.out.println(student.toString());
    }

    @Test
    public void searchTest(){
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andGenderEqualTo(0);
        List<Student> studentList = studentDao.selectByExample(studentExample);
        System.out.println(studentList.toString());
    }

    @Test
    public void updateTest(){
        Student student = studentDao.selectByPrimaryKey(new Integer(10000000));
        student.setName("吴艳君");
        StudentExample studentExample = new StudentExample();
        studentDao.updateByPrimaryKeySelective(student);
    }

    @Test
    public void deleteBatchByIdTest(){
        List<Integer> idList = new ArrayList<>();
        idList.add(20000000);
        idList.add(10000000);
        studentDao.deleteBatchByIdList(idList);
    }

}












