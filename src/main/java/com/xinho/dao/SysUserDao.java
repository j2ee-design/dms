package com.xinho.dao;

import com.xinho.bean.sysUser;
import com.xinho.bean.sysUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao {
    long countByExample(sysUserExample example);

    int deleteByExample(sysUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(sysUser record);

    int insertSelective(sysUser record);

    List<sysUser> selectByExample(sysUserExample example);

    sysUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") sysUser record, @Param("example") sysUserExample example);

    int updateByExample(@Param("record") sysUser record, @Param("example") sysUserExample example);

    int updateByPrimaryKeySelective(sysUser record);

    int updateByPrimaryKey(sysUser record);
}