package com.xinho.service;

import com.xinho.dto.DormitoryDto;

import java.util.List;

public interface DormitoryService {
    /**
     * 通过
     * @param classId
     * @return
     */
    List<DormitoryDto> getDormListByClassId(Integer classId);

    /**
     * 添加单个宿舍
     * @param dormitoryDto 要添加的宿舍
     * @return 添加成功与否的 flag
     */
    boolean addSingle(DormitoryDto dormitoryDto);

    /**
     * 批量添加
     */
}
