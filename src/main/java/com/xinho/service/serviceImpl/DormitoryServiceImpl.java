package com.xinho.service.serviceImpl;

import com.xinho.bean.*;
import com.xinho.dao.AcademyDao;
import com.xinho.dao.ApartmentDao;
import com.xinho.dao.ClassDao;
import com.xinho.dao.DormitoryDao;
import com.xinho.dto.DormitoryDto;
import com.xinho.service.DormitoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("dormitoryService")
public class DormitoryServiceImpl implements DormitoryService{



    @Autowired private DormitoryDao dormitoryDao;
    @Autowired private ClassDao classDao;
    @Autowired private AcademyDao academyDao;
    @Autowired private ApartmentDao apartmentDao;
    private DormitoryDto transBeanToDto(Dormitory dormitory){
        DormitoryDto dormitoryDto = new DormitoryDto();
        BeanUtils.copyProperties(dormitory, dormitoryDto);
        // 处理两个页面属性 classId(cishi) 和 apartName
        if (dormitory.getClassId()!=null){ // 再次为我建数据库表示捉急
            ClassExample classExample = new ClassExample();
            classExample.createCriteria().andIdEqualTo(dormitory.getClassId());
            Integer trueClassId = classDao.selectByExample(classExample).get(0).getClassId();
            if (trueClassId!=null){
                dormitoryDto.setTrueClassId(trueClassId);
            }
        }
        if (dormitory.getApartId()!=null){
            AcademyExample academyExample = new AcademyExample();
            academyExample.createCriteria().andIdEqualTo(dormitory.getApartId());
            String academyName = academyDao.selectByExample(academyExample).get(0).getName();
            if (academyName!=null){
                dormitoryDto.setApartName(academyName);
            }
        }
        return dormitoryDto;
    }
    private DormitoryExample getExampleByDto(DormitoryDto dormitoryDto){
        DormitoryExample dormitoryExample = new DormitoryExample();
        DormitoryExample.Criteria criteria = dormitoryExample.createCriteria();

        if (dormitoryDto.getId()!=null){
            criteria.andIdEqualTo(dormitoryDto.getId());
        }
        if (dormitoryDto.getApartId()!=null){
            criteria.andApartIdEqualTo(dormitoryDto.getApartId());
        }
        if (dormitoryDto.getClassId()!=null){
            criteria.andClassIdEqualTo(dormitoryDto.getClassId());
        }
        if (dormitoryDto.getDicDormType()!=null){
            criteria.andDicDormTypeEqualTo(dormitoryDto.getDicDormType());
        }
        if (dormitoryDto.getDormId()!=null){
            criteria.andDormIdEqualTo(dormitoryDto.getDormId());
        }
        if (dormitoryDto.getChiefId()!=null){
            criteria.andChiefIdEqualTo(dormitoryDto.getChiefId());
        }
        if (dormitoryDto.getAllBed()!=null){
            criteria.andAllBedEqualTo(dormitoryDto.getAllBed());
        }
        if (dormitoryDto.getUsedBed()!=null){
            criteria.andUsedBedEqualTo(dormitoryDto.getUsedBed());
        }

        return dormitoryExample;
    }

    @Override
    public boolean addSingle(DormitoryDto dormitoryDto){
        // 如果 dto 的额外属性不为空且对应属性为空，那么进行转换
        Dormitory dormitory = new Dormitory();
        BeanUtils.copyProperties(dormitoryDto,dormitory);

        if(dormitoryDto.getApartName()!=null && dormitoryDto.getApartId()==null){
            ApartmentExample apartmentExample = new ApartmentExample();
            apartmentExample.createCriteria().andNameEqualTo(dormitoryDto.getApartName());
            dormitory.setApartId(apartmentDao.selectByExample(apartmentExample).get(0).getId());
        }
        if(dormitoryDto.getTrueClassId()!=null && dormitoryDto.getClassId()==null){
            ClassExample classExample = new ClassExample();
            classExample.createCriteria().andClassIdEqualTo(dormitoryDto.getTrueClassId());
            dormitory.setApartId(classDao.selectByExample(classExample).get(0).getClassId());
        }
        return dormitoryDao.insertSelective(dormitory)==1;
    }

    @Override
    public List<DormitoryDto> getDormListByClassId(Integer classId) {
        DormitoryDto dormitoryDto = new DormitoryDto();
        dormitoryDto.setClassId(classId);
        DormitoryExample dormitoryExample = getExampleByDto(dormitoryDto);
        List<DormitoryDto> dormitoryDtoList = new ArrayList<>();
        dormitoryDao.selectByExample(dormitoryExample).forEach(item->dormitoryDtoList.add(transBeanToDto(item)));
        return dormitoryDtoList;
    }
}
