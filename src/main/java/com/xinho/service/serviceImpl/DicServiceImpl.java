package com.xinho.service.serviceImpl;

import com.xinho.bean.Dic;
import com.xinho.bean.DicExample;
import com.xinho.dao.DicDao;
import com.xinho.dto.DicDto;
import com.xinho.service.DicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("dicService")
public class DicServiceImpl implements DicService{

    @Autowired
    private DicDao dicDao;

    @Override
    public List<DicDto> getDicByPId(Integer pId) {
        DicDto  dicDto = new DicDto();
        dicDto.setPid(pId);
        List<DicDto> dicDtoList = new ArrayList<>();
        dicDao.selectByExample(getExampleByDto(dicDto)).forEach(item->dicDtoList.add(transBeanToDto(item)));
        return dicDtoList;

    }

    private DicExample getExampleByDto(DicDto dicDto) {
        DicExample dicExample = new DicExample();
        DicExample.Criteria criteria = dicExample.createCriteria();
        if (dicDto.getPid()!=null){
            criteria.andPidEqualTo(dicDto.getPid());
        }
        if (dicDto.getSeq()!=null){
            criteria.andSeqEqualTo(dicDto.getSeq());
        }
        if (dicDto.getValue()!=null){
            criteria.andValueEqualTo(dicDto.getValue());
        }
        if (dicDto.getId()!=null){ // 我也是蠢得一匹，这特么要写个主键干嘛？？？脑子有病的典范。用父ID加Squence做一个 unique 不就行了？？？
            criteria.andIdEqualTo(dicDto.getId());
        }
        return dicExample;
    }
    private DicDto transBeanToDto(Dic dic){
        DicDto dicDto = new DicDto();
        BeanUtils.copyProperties(dic,dicDto);
        return dicDto;
    }

    @Override
    public boolean addDic(Dic dic) {
        return dicDao.insert(dic) == 1;
    }

    @Override
    public boolean deleteDicById(Integer id) {
        return dicDao.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public boolean modefy(Dic dic) {
        return dicDao.updateByPrimaryKeySelective(dic) == 1;
    }
}
