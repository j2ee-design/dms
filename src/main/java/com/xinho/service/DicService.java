package com.xinho.service;

import com.xinho.bean.Dic;
import com.xinho.dto.DicDto;

import java.util.List;

public interface DicService {

    /**
     * 获取根据父字典值获取字典值
     * @param fId 父字典值
     * @return 查找到的所有字典类型
     */
    List<DicDto> getDicByPId(Integer fId);

    /**
     * 添加一条字典值
     * @param dic 字典对象
     * @return 成功与否的 flag
     */
    boolean addDic(Dic dic);

    /**
     * 删除匹配的字典值
     * @param id 要删除的字典值
     * @return 成功与否的 flag
     */
    boolean deleteDicById(Integer id);

    /**
     * 修改字典值
     * @param dic 修改过后的字典值
     * @return 修改成功与否的 flag
     */
    boolean modefy(Dic dic);
}
