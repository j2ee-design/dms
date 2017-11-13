package com.xinho.controller;

import com.xinho.constant.PageCodeEnum;
import com.xinho.dto.DormitoryDto;
import com.xinho.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/distribute")
public class DormDistriController {

    @Autowired private AcademyService academyService;
    @Autowired private ApartmentService apartmentService;
    @Autowired private DicService dicService;
    @Autowired private DormitoryService dormitoryService;
    @Autowired private DormDistriService dormDistriService;
    /**
     * 初始化分配页面
     * 1. 返回学院、年级和公寓楼信息
     * 2. 返回逻辑视图
     * @return 逻辑视图 dormDistri
     */
    @RequestMapping
    public String init(Model model){
        // 1.
        model.addAttribute("academyDtos",academyService.getAllAcademy());
        model.addAttribute("apartDtos",apartmentService.searchAllApartment());
        model.addAttribute("dicGradeList",dicService.getDicByPId(10001));
        return "dormDistri";
    }

    @RequestMapping("/getDorm/{apartId}")
    public @ResponseBody Map getDorm(@PathVariable("apartId") Integer apartId){
        Map result = new HashMap();
        List<DormitoryDto> dormitoryDtos = dormitoryService.getDormListByApartId(apartId);
        result.put("dormDtos",dormitoryDtos);
        result.put("apartDto",apartmentService.searchByApartId(apartId));
        return result;
    }

    /**
     * 1. 数据真实性校检
     * 2. 将学生添加至宿舍
     * 3. 返回数据（）
     * @return
     */
    @RequestMapping("/batchDistribute")
    public @ResponseBody Map distributeBatch(@RequestParam (value = "stuIds")String stuIdsStr,
                                             @RequestParam(value="dormIds")String dormIdsStr){
        String[] stuIdStrList = stuIdsStr.split(",");
        String[] dormIdStrList = dormIdsStr.split(",");
        List<Integer> stuIds = new ArrayList<>();
        List<Integer> dormIds = new ArrayList<>();
        for (String id: stuIdStrList){
            stuIds.add(Integer.parseInt(id));
        }
        for (String id: dormIdStrList){
            dormIds.add(Integer.parseInt(id));
        }
        Map result = new HashMap();
        int successNum = dormDistriService.distrbuteBatch(stuIds,dormIds);
        // TODO 这里可有更好的解决方式？
        result.put("successNum",successNum);
        return result;
    }

    @RequestMapping("/delSingle/{stuId}")
    public @ResponseBody Map deleteSingle(@PathVariable(value = "stuId")Integer stuId){
        Map result = new HashMap();
        int pageCode = dormDistriService.delSingle(stuId);
        result.put(PageCodeEnum.KEY,pageCode);
        return result;
    }
}







