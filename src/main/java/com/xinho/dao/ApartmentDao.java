package com.xinho.dao;

import com.xinho.bean.Apartment;
import com.xinho.bean.ApartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentDao {
    long countByExample(ApartmentExample example);

    int deleteByExample(ApartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Apartment record);

    int insertSelective(Apartment record);

    List<Apartment> selectByExample(ApartmentExample example);

    Apartment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Apartment record, @Param("example") ApartmentExample example);

    int updateByExample(@Param("record") Apartment record, @Param("example") ApartmentExample example);

    int updateByPrimaryKeySelective(Apartment record);

    int updateByPrimaryKey(Apartment record);
}