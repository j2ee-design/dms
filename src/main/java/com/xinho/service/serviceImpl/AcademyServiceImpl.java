package com.xinho.service.serviceImpl;

import com.xinho.bean.Academy;
import com.xinho.bean.AcademyExample;
import com.xinho.dao.AcademyDao;
import com.xinho.dto.AcademyDto;
import com.xinho.service.AcademyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademyServiceImpl implements AcademyService {

    @Autowired
    private AcademyDao academyDao;

    @Override
    public List<AcademyDto> getAllAcademy() {
        AcademyExample academyExample = getExampleByDto(new AcademyDto());
        List<AcademyDto> academyList = new ArrayList<>();
        academyDao.selectByExample(academyExample).forEach(item-> academyList.add(transDaoToDto(item)));
        return academyList;
    }


    /**
     * 通过 Dto 对象获取 Example
     * @return
     */
    private AcademyExample getExampleByDto(AcademyDto academyDto){
        AcademyExample academyExample = new AcademyExample();
        AcademyExample.Criteria criteria = academyExample.createCriteria();

        if (academyDto.getId()!=null){
            criteria.andIdEqualTo(academyDto.getId());
        }
        if (academyDto.getName()!=null){
            criteria.andNameEqualTo(academyDto.getName());
        }
        if (academyDto.getAcademyId()!=null){ // 我不知道我是哪根神经抽错了，要写个这字段。
            criteria.andAcademyIdEqualTo(academyDto.getAcademyId());
        }
        if (academyDto.getMajorNum()!=null){
            criteria.andMajorNumEqualTo(academyDto.getMajorNum());
        }
        if (academyDto.getStuNum()!=null){
            criteria.andStuNumEqualTo(academyDto.getStuNum());
        }
        return academyExample;
    }

    private AcademyDto transDaoToDto(Academy academy){
        AcademyDto academyDto = new AcademyDto();
        BeanUtils.copyProperties(academy, academyDto);
        return academyDto;
    }

}
