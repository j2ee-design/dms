package com.xinho.controller;

import com.xinho.bean.Apartment;
import com.xinho.bean.Dormitory;
import com.xinho.constant.PageCodeEnum;
import com.xinho.dto.ApartmentDto;
import com.xinho.service.ApartmentService;
import com.xinho.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/apart")
public class ApartmentController {


    @Autowired private ApartmentService apartmentService;
    @Autowired private DormitoryService dormitoryService;

    /**
     * 初始化 apartList 页面=，获取所有 apartment 信息
     * @return apartment 的逻辑视图名
     */
    @RequestMapping(method = RequestMethod.GET)
    public String iniApartList(Model model){
        // 1. 查询所有apartment，返回。
        List<ApartmentDto> apartmentDtoList = apartmentService.searchAllApartment();
        model.addAttribute("apartmentDtoList",apartmentDtoList);
        return "apartList";
    }

    /**
     * ----RESTful 风格的url，put 类型请求，请求服务器修改----
     * 暂时不支持 RESTful，没有找到 Restful 的最佳实践
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void modifyApart(ApartmentDto apartmentDto, Model model){

    }

    /**
     * 添加公寓
     * 1. 数据校检，格式和唯一性检查（该 ID 的公寓是否已经存在）
     * 2. 在数据库中加入一条 apartment(要先补全信息，生成宿舍信息)
     * 3. sql 操作 -- 补全宿舍信息，并更新
     * @return 主要返回操作结果代码（无数据）
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public @ResponseBody Map addApart(@RequestBody ApartmentDto apartmentDto){
        Map result = new HashMap();
        // 1 格式校检【前端数据是不可靠的】
        if (!apartmentService.validateDto(apartmentDto)){
            result.put(PageCodeEnum.KEY,PageCodeEnum.APART_ADD_FAIL_ID_EXIST.getCode());
            return result;
        }
        // 2.
        apartmentService.addApartByDto(apartmentDto);
        // 3.
        dormitoryService.initDormByNewApartment(apartmentDto.getId());
        result.put(PageCodeEnum.KEY,PageCodeEnum.APART_ADD_SUCCESS.getCode());
        return result;
    }

}








