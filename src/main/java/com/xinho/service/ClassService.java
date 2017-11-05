package com.xinho.service;

import com.xinho.bean.Class;

import java.util.List;

public interface ClassService {

    /**
     * 通过 majorId 查找所有班级
     * @param majorId 专业ID
     * @return 查找到的值
     */
    List<Class> getClassesByMajorIdAndGradeId(Integer majorId,Integer dicGrade);
}
