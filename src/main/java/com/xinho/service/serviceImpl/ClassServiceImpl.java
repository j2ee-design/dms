package com.xinho.service.serviceImpl;

import com.xinho.bean.Class;
import com.xinho.bean.ClassExample;
import com.xinho.bean.DicExample;
import com.xinho.bean.MajorExample;
import com.xinho.dao.ClassDao;
import com.xinho.dao.DicDao;
import com.xinho.dao.MajorDao;
import com.xinho.dto.ClassDto;
import com.xinho.service.ClassService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("classService")
public class ClassServiceImpl implements ClassService{
    @Autowired private ClassDao classDao;
    @Autowired private MajorDao majorDao;
    @Autowired private DicDao dicDao;
    private ClassDto transBeanToDto(Class aclass){
        ClassDto classDto = new ClassDto();
        BeanUtils.copyProperties(aclass,classDto);

        String majorName = null;
        if (aclass.getMajorId()!=null){
            try{
                majorName = majorDao.selectByPrimaryKey(aclass.getMajorId()).getName();
            } catch (Exception e){
                return classDto;
            }
        }
        classDto.setMajorName(majorName);

        String dicGradeValue = null;
        if (aclass.getDicGrade()!=null){
            try{
                dicGradeValue = dicDao.selectByPrimaryKey(aclass.getDicGrade()).getValue();
            }catch (Exception e){
                return classDto;
            }
        }
        classDto.setDicGradeValue(dicGradeValue);

        return classDto;
    }
    private ClassExample getExampleByDto(ClassDto classDto){
        ClassExample classExample = new ClassExample();
        ClassExample.Criteria criteria = classExample.createCriteria();

        if (classDto.getId()!=null){
            criteria.andIdEqualTo(classDto.getId());
        }
        if (classDto.getClassId()!=null){
            criteria.andClassIdEqualTo(classDto.getClassId());
        }
        if (classDto.getMajorId()!=null){
            criteria.andMajorIdEqualTo(classDto.getMajorId());
        }
        if (classDto.getStuNum()!=null){
            criteria.andStuNumEqualTo(classDto.getStuNum());
        }
        if (classDto.getDicGrade()!=null){
            criteria.andDicGradeEqualTo(classDto.getDicGrade());
        }

        return classExample;
    }

    @Override
    public List<Class> getClassesByMajorIdAndGradeId(Integer majorId,Integer dicGrade) {
        List<Class> classList = new ArrayList<Class>();
        ClassDto classDto = new ClassDto();
        classDto.setMajorId(majorId);
        classDto.setDicGrade(dicGrade);
        classDao.selectByExample(getExampleByDto(classDto)).forEach(item->classList.add(item));
        return classList;
    }
}
