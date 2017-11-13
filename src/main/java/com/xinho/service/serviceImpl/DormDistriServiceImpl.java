package com.xinho.service.serviceImpl;

import com.xinho.bean.Academy;
import com.xinho.bean.Apartment;
import com.xinho.bean.Dormitory;
import com.xinho.bean.Student;
import com.xinho.constant.PageCodeEnum;
import com.xinho.dao.AcademyDao;
import com.xinho.dao.ApartmentDao;
import com.xinho.dao.DormitoryDao;
import com.xinho.dao.StudentDao;
import com.xinho.service.DormDistriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dormDistriService")
public class DormDistriServiceImpl implements DormDistriService{

    @Autowired private DormitoryDao dormitoryDao;
    @Autowired private StudentDao studentDao;
    @Autowired private ApartmentDao apartmentDao;

    /**
     * 批量分配学生至宿舍。
     * --> 宿舍床位变化，公寓床位变化，公寓已经使用的宿舍变化，学生宿舍状态变化，学生宿舍ID变化
     * @param stuIds 要分配的学生的 ID
     * @param dormIds 被分配宿舍的 ID
     * @return 成功分配的个数
     */
    @Override
    public int distrbuteBatch(List<Integer> stuIds, List<Integer> dormIds) {
        // 如果学生、宿舍列表为空，退出
        if (stuIds==null || stuIds.size()==0 || dormIds == null || dormIds.size()==0){
            return 0;
        }
        //
        Student student = null;
        Dormitory dormitory = null;
        // 获取宿舍所在公寓信息。用于升级准备。
        int apartId = dormitoryDao.selectByPrimaryKey(dormIds.get(0)).getApartId();
        Apartment apartment = apartmentDao.selectByPrimaryKey(apartId);

        int i=0;
        int successSum = 0;
        int newUsedDorm = 0;
        // 循环写入
        for (Integer sid: stuIds){
            // 如果宿舍用完。退出。
            if (i==dormIds.size()){
                apartment.setRemainBed(apartment.getRemainBed()-successSum);
                apartment.setUsedDorm(apartment.getUsedDorm()+newUsedDorm);
                apartmentDao.updateByPrimaryKeySelective(apartment);
                return successSum;
            }

            while(i<dormIds.size()){
                dormitory = dormitoryDao.selectByPrimaryKey(dormIds.get(i));
                // 宿舍不存在或人数已满,j+1，换下一个宿舍
                int test = (dormitory.getAllBed()-dormitory.getUsedBed());
                if (dormitory==null || dormitory.getAllBed()==dormitory.getUsedBed() ){
                    i++;
                    continue;
                }
                // 宿舍人未满，退出对宿舍的遍历
                break;
            }

            student = studentDao.selectByPrimaryKey(sid);
            // 如果当前学生不存在（事实上我觉得不可能）
            if (student==null){
                continue;
            }
            student.setDormStatus(2);
            student.setDormId(dormitory.getId());
            // 默认设置第一个进入宿舍的人为宿舍长
            if (dormitory.getUsedBed()==0){
                dormitory.setClassId(student.getClassId());
                dormitory.setChiefId(student.getId());
                // 如果是新开了一个宿舍，公寓宿舍已使用量应该加一
                newUsedDorm++;
            }
            // 宿舍床位变化
            dormitory.setUsedBed(dormitory.getUsedBed()+1);
            dormitoryDao.updateByPrimaryKeySelective(dormitory);
            studentDao.updateByPrimaryKeySelective(student);
            successSum++;
        }
        apartment.setRemainBed(apartment.getRemainBed()-successSum);
        apartment.setUsedDorm(apartment.getUsedDorm()+newUsedDorm);
        apartmentDao.updateByPrimaryKeySelective(apartment);
        return successSum;
    }

    /**
     * 1. 学生状态改变，宿舍号改变，宿舍床位改变，公寓床位和宿舍改变。
     * @param stuId 学生ID
     * @return 结果
     */
    @Override
    public int delSingle(Integer stuId) {
        Student student = studentDao.selectByPrimaryKey(stuId);

        if (stuId==null){
            return PageCodeEnum.DELETEe_FAIL_DONT_KNOW.getCode();
        }
        if (student==null){
            return PageCodeEnum.DELETE_FAIL_NO_STU.getCode();
        }
        // 该学生是否已经分配
        if (student.getDormStatus()==null || student.getDormStatus().longValue()==0){
            return PageCodeEnum.DELETE_FAIL_NO_DISCRI.getCode();
        }
        Dormitory dormitory = dormitoryDao.selectByPrimaryKey(student.getDormId());
        Apartment apartment = apartmentDao.selectByPrimaryKey(dormitory.getApartId());
        student.setDormStatus(0);
        // 这里产生了一个悖论，想要给学生宿舍ID设为null，但是用的是selective的statement，所以会被忽略。
        student.setDormId(null);
        dormitory.setUsedBed(dormitory.getUsedBed()-1);
        apartment.setRemainBed(apartment.getRemainBed()+1);
        if (dormitory.getUsedBed()==0){
            apartment.setUsedDorm(apartment.getDormNum()-1);
            dormitory.setChiefId(null);
            dormitory.setClassId(null);
        }
        studentDao.updateByPrimaryKey(student);
        dormitoryDao.updateByPrimaryKey(dormitory);
        apartmentDao.updateByPrimaryKey(apartment);
        return PageCodeEnum.DELETE_SUCCESS.getCode();
    }
}
