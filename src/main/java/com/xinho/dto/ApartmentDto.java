package com.xinho.dto;

import com.xinho.bean.Apartment;
import com.xinho.bean.ApartmentExample;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data public class ApartmentDto extends Apartment{
    private String apartmentTypeName;
}