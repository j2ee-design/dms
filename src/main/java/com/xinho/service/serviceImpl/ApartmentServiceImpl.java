package com.xinho.service.serviceImpl;

import com.xinho.bean.Apartment;
import com.xinho.bean.ApartmentExample;
import com.xinho.bean.Dic;
import com.xinho.bean.DicExample;
import com.xinho.dao.ApartmentDao;
import com.xinho.dao.DicDao;
import com.xinho.dto.ApartmentDto;
import com.xinho.service.ApartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("apartmentService")
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired private DicDao dicDao;
    @Autowired private ApartmentDao apartmentDao;

    @Override
    public List<ApartmentDto> searchAllApartment() {
        List<ApartmentDto> apartmentDtoList = new ArrayList<ApartmentDto>();
        List<Apartment> apartmentList = apartmentDao.selectByExample(new ApartmentExample());

        for (Apartment apartment:apartmentList){
            apartmentDtoList.add(beanToDto(apartment));
        }
        return apartmentDtoList;
    }

    @Override
    public ApartmentDto searchByApartId(Integer apartId) {
        if (apartId == null || apartId.longValue()==0){
            return null;
        }
        Apartment apartment = apartmentDao.selectByPrimaryKey(apartId);
        if (apartment!=null){
            return beanToDto(apartment);
        }
        return null;
    }

    /**
     * 1. 空值检查
     * 2. 格式检查
     * 3. 检查名字和id是否重复
     * @param apartmentDto 用户输入的 dto
     * @return 校检失败返回 false，校检成功返回 true
     */
    @Override
    public boolean validateDto(ApartmentDto apartmentDto) {
        // 1.
        if (apartmentDto.getId()==null || apartmentDto.getId().longValue()==0){
            return false;
        }
        if (apartmentDto.getName()==null || apartmentDto.getName().trim().length()==0){
            return false;
        }
        if (apartmentDto.getDicApartType()==null || apartmentDto.getDicApartType().longValue()==0){
            return false;
        }
        if (apartmentDto.getFloorNum()==null || apartmentDto.getFloorNum().longValue()==0){
            return false;
        }
        if (apartmentDto.getDormNum()==null || apartmentDto.getDormNum().longValue()==0){
            return false;
        }
        if (apartmentDto.getDormBedNum()==null || apartmentDto.getDormBedNum().longValue()==0){
            return false;
        }

        // 3.
        if (apartmentDao.selectByPrimaryKey(apartmentDto.getId())!=null){
            return false;
        }
        ApartmentExample apartmentExample = new ApartmentExample();
        apartmentExample.createCriteria().andNameEqualTo(apartmentDto.getName());
        if (apartmentDao.selectByExample(apartmentExample).size()!=0){
            return false;
        }
        return true;
    }

    /**
     * 根据 apartDto 添加 apart，注意要先把信息补全。正常来讲这个函数添加的信息是不存在空值的
     * 1. 补全信息(默认已经使用的 dorm 为 0，要根据用户的输入算出总 bed.
     * 2. 添加
     * @param apartmentDto 要添加的 dto
     * @return 成功与否的 flag
     */
    @Override
    public boolean addApartByDto(ApartmentDto apartmentDto) {
        apartmentDto.setUsedDorm(0);
        apartmentDto.setAllBed(apartmentDto.getDormBedNum()*apartmentDto.getDormNum());
        apartmentDto.setRemainBed(apartmentDto.getAllBed());
        Apartment apartment = dtoToBean(apartmentDto);
        return apartmentDao.insert(apartment)==1;
    }

    public Apartment dtoToBean(ApartmentDto apartmentDto){
        Apartment apartment = new Apartment();
        BeanUtils.copyProperties(apartmentDto,apartment);

        // 从字典中查找类型ID
        if (apartmentDto.getDicApartType()==null && apartmentDto.getApartmentTypeName()!=null){
            DicExample dicExample = new DicExample();
            dicExample.createCriteria().andValueEqualTo(apartmentDto.getApartmentTypeName());
            List<Dic> dicList = dicDao.selectByExample(dicExample);
            if (dicList.size()>0){
                apartment.setDicApartType(dicList.get(0).getId());
            }
        }
        return apartment;
    }

    public ApartmentDto beanToDto(Apartment apartment){
        ApartmentDto apartmentDto = new ApartmentDto();
        BeanUtils.copyProperties(apartment,apartmentDto);

        if (apartment.getDicApartType()!=null){
            Dic dic = dicDao.selectByPrimaryKey(apartment.getDicApartType());
            if (dic != null){
                apartmentDto.setApartmentTypeName(dic.getValue());
            }
        }

        return apartmentDto;
    }
}
