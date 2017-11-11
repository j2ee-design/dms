package com.xinho.service;

import com.xinho.bean.Student;
import com.xinho.dto.DormitoryDto;
import com.xinho.dto.StudentDto;

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
     * 查找某个公寓下的所有宿舍
     * @param apartId 公寓Id
     * @return 查找成功（包括记录为空）返回 dormDto 的 list.失败返回 null
     */
    List<DormitoryDto> getDormListByApartId(Integer apartId);

    /**
     * 新建公寓后初始化宿舍信息
     * 1. 如果公寓内已经住人，则操作退出。
     * @param id 公寓ID
     * @return 成功与否的 flag
     */
    void initDormByNewApartment(Integer id);

    DormitoryDto getDormListById(Integer dormId);

    List<StudentDto> getStudentDtoListByDormId(Integer dormId);

    int distributeSingle(Integer dormId, Integer stuId);
}
