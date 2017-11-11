package com.xinho.service;

import com.xinho.dto.ApartmentDto;

import java.util.List;

public interface ApartmentService {

    /**
     * 查找所有 apartment(apartList页面初始化的时候用)
     * @return 查找的结果
     */
    List<ApartmentDto> searchAllApartment();

    /**
     * 根据ID查找公寓
     * @param apartId 公寓ID
     * @return 查找失败(包括为空)返回 null，查找成功返回 ApartmentDto
     */
    ApartmentDto searchByApartId(Integer apartId);


    boolean addApartByDto(ApartmentDto apartmentDto);

    /**
     * 表单校检
     * @param apartmentDto 用户输入的 dto
     * @return 校检失败返回 false，校检成功返回 true
     */
    boolean validateDto(ApartmentDto apartmentDto);
}
