package com.xinho.service;

import com.xinho.dto.MajorDto;

import java.util.List;

public interface MajorService {

    /**
     * 通过PID获取
     * @param academyId
     * @return
     */
    List<MajorDto> getMajorsByPid(Integer academyId);
}
