package com.xinho.dao;

import com.xinho.bean.sysMenu;
import com.xinho.bean.sysMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysMenuDao {
    long countByExample(sysMenuExample example);

    int deleteByExample(sysMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(sysMenu record);

    int insertSelective(sysMenu record);

    List<sysMenu> selectByExample(sysMenuExample example);

    sysMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") sysMenu record, @Param("example") sysMenuExample example);

    int updateByExample(@Param("record") sysMenu record, @Param("example") sysMenuExample example);

    int updateByPrimaryKeySelective(sysMenu record);

    int updateByPrimaryKey(sysMenu record);
}