package com.xinho.dto;

import com.xinho.bean.Dormitory;
import lombok.Data;

@Data public class DormitoryDto extends Dormitory{
    private Integer className;
    private String apartName;
}
