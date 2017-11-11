package com.xinho.service;

import com.xinho.bean.Academy;
import com.xinho.dto.AcademyDto;

import java.util.List;

public interface AcademyService {
    /**
     * 获取所有学院对象
     * @return 数据库中的所有学院
     */
    List<AcademyDto> getAllAcademy();
}
