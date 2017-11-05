package com.xinho.service.serviceImpl;

import com.xinho.bean.AcademyExample;
import com.xinho.bean.Major;
import com.xinho.bean.MajorExample;
import com.xinho.dao.AcademyDao;
import com.xinho.dao.MajorDao;
import com.xinho.dto.MajorDto;
import com.xinho.service.MajorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MajorServiceImpl implements MajorService{

    @Autowired
    private MajorDao majorDao;
    @Autowired
    private AcademyDao academyDao;

    @Override
    public List<MajorDto> getMajorsByPid(Integer academyId) {
        MajorDto majorDto = new MajorDto();
        majorDto.setAcademyId(academyId);
        MajorExample majorExample = getExampleByDto(majorDto);
        List<MajorDto> majorDtoList = new ArrayList<MajorDto>();
        majorDao.selectByExample(majorExample).forEach(item->majorDtoList.add(transBeantoDto(item)));
        return majorDtoList;
    }

    private MajorExample getExampleByDto(MajorDto majorDto){
        MajorExample majorExample = new MajorExample();
        MajorExample.Criteria criteria = majorExample.createCriteria();

//        // 判断 academyID 和 academyName 是否有出入、不匹配
//        AcademyExample academyExample = new AcademyExample();
//        academyExample.createCriteria().andIdEqualTo(majorDto.getAcademyId());
//        Integer academyId = academyDao.selectByExample(academyExample).get(0).getId();
//        if (academyId.longValue()!=majorDto.getAcademyId().longValue()){
//            return null;
//        }
//        // 以上这一部分不应该写在 getExampleByDto 中，应该由他的上级自己来处理。


        if (majorDto.getId()!=null){
            criteria.andIdEqualTo(majorDto.getId());
        }
        if (majorDto.getName()!=null){
            criteria.andNameEqualTo(majorDto.getName());
        }
        if (majorDto.getAcademyId()!=null){
            criteria.andAcademyIdEqualTo(majorDto.getAcademyId());
        }
        if (majorDto.getClassNum()!=null){
            criteria.andClassNumEqualTo(majorDto.getClassNum());
        }
        if (majorDto.getStuNum()!=null){
            criteria.andStuNumEqualTo(majorDto.getStuNum());
        }
        return majorExample;
    }
    private MajorDto transBeantoDto(Major major){
        MajorDto majorDto = new MajorDto();
        BeanUtils.copyProperties(major,majorDto);

        AcademyExample academyExample = new AcademyExample();
        academyExample.createCriteria().andIdEqualTo(majorDto.getAcademyId());
        Integer academyId = academyDao.selectByExample(academyExample).get(0).getId();
        if (academyId != null){
            majorDto.setAcademyId(academyId);
        }

        return majorDto;
    }
}








