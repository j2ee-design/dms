package com.xinho.service.serviceImpl;

import com.xinho.bean.*;
import com.xinho.bean.Class;
import com.xinho.constant.PageCodeEnum;
import com.xinho.dao.*;
import com.xinho.dto.DormitoryDto;
import com.xinho.dto.StudentDto;
import com.xinho.service.DormitoryService;
import com.xinho.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("dormitoryService")
public class DormitoryServiceImpl implements DormitoryService{
    @Autowired private DormitoryDao dormitoryDao;
    @Autowired private ClassDao classDao;
    @Autowired private StudentDao studentDao;
    @Autowired private StudentService studentService;
    @Autowired private ApartmentDao apartmentDao;

    public DormitoryDto beanToDto(Dormitory dormitory){
        DormitoryDto dormitoryDto = new DormitoryDto();
        BeanUtils.copyProperties(dormitory, dormitoryDto);
        // 获取班级长ID（再次骂自己1万遍）
        if (dormitory.getClassId()!=null){ // 再次为我建数据库表示捉急
            ClassExample classExample = new ClassExample();
            classExample.createCriteria().andIdEqualTo(dormitory.getClassId());
            List<Class> classList = classDao.selectByExample(classExample);
            if (classList.size()>0 && classList.get(0).getClassId()!=null){
                dormitoryDto.setClassName(classList.get(0).getClassId());
            }
        }
        // 获取宿舍楼名
        if (dormitory.getApartId()!=null){
            ApartmentExample apartmentExample = new ApartmentExample();
            apartmentExample.createCriteria().andIdEqualTo(dormitory.getApartId());
            List<Apartment> apartmentList = apartmentDao.selectByExample(apartmentExample);
            if (apartmentList.size()>0 && apartmentList.get(0).getName()!=null){
                dormitoryDto.setApartName(apartmentList.get(0).getName());
            }
        }
        return dormitoryDto;
    }

    public DormitoryExample getExampleByDto(DormitoryDto dormitoryDto){
        DormitoryExample dormitoryExample = new DormitoryExample();
        DormitoryExample.Criteria criteria = dormitoryExample.createCriteria();

        if (dormitoryDto.getId()!=null){
            criteria.andIdEqualTo(dormitoryDto.getId());
        }
        if (dormitoryDto.getApartId()!=null){
            criteria.andApartIdEqualTo(dormitoryDto.getApartId());
        }
        if (dormitoryDto.getClassId()!=null){
            criteria.andClassIdEqualTo(dormitoryDto.getClassId());
        }
        if (dormitoryDto.getDicDormType()!=null){
            criteria.andDicDormTypeEqualTo(dormitoryDto.getDicDormType());
        }
        if (dormitoryDto.getDormId()!=null){
            criteria.andDormIdEqualTo(dormitoryDto.getDormId());
        }
        if (dormitoryDto.getChiefId()!=null){
            criteria.andChiefIdEqualTo(dormitoryDto.getChiefId());
        }
        if (dormitoryDto.getAllBed()!=null){
            criteria.andAllBedEqualTo(dormitoryDto.getAllBed());
        }
        if (dormitoryDto.getUsedBed()!=null){
            criteria.andUsedBedEqualTo(dormitoryDto.getUsedBed());
        }

        return dormitoryExample;
    }

    public Dormitory dtoToBean(DormitoryDto dormitoryDto){
        Dormitory dormitory = new Dormitory();
        BeanUtils.copyProperties(dormitoryDto,dormitory);

        if (dormitoryDto.getApartId()==null && dormitoryDto.getApartName()!=null){
            ApartmentExample apartmentExample = new ApartmentExample();
            apartmentExample.createCriteria().andNameEqualTo(dormitoryDto.getApartName());
            List<Apartment> apartmentList = apartmentDao.selectByExample(apartmentExample);
            if(apartmentList.size()>0){
                dormitory.setApartId(apartmentList.get(0).getId());
            }
        }

        if (dormitoryDto.getClassId()==null && dormitoryDto.getClassName()!=null){
            ClassExample classExample = new ClassExample();
            classExample.createCriteria().andClassIdEqualTo(dormitoryDto.getClassName());
            List<Class> classList = classDao.selectByExample(classExample);
            if(classList .size()>0){
                dormitory.setApartId(classList.get(0).getId());
            }
        }

        return dormitory;
    }

    @Override
    public boolean addSingle(DormitoryDto dormitoryDto){
        // 如果 dto 的额外属性不为空且对应属性为空，那么进行转换
        Dormitory dormitory = new Dormitory();
        BeanUtils.copyProperties(dormitoryDto,dormitory);

        if(dormitoryDto.getApartName()!=null && dormitoryDto.getApartId()==null){
            ApartmentExample apartmentExample = new ApartmentExample();
            apartmentExample.createCriteria().andNameEqualTo(dormitoryDto.getApartName());
            dormitory.setApartId(apartmentDao.selectByExample(apartmentExample).get(0).getId());
        }
        if(dormitoryDto.getClassName()!=null && dormitoryDto.getClassId()==null){
            ClassExample classExample = new ClassExample();
            classExample.createCriteria().andClassIdEqualTo(dormitoryDto.getClassName());
            dormitory.setApartId(classDao.selectByExample(classExample).get(0).getClassId());
        }
        return dormitoryDao.insertSelective(dormitory)==1;
    }

    @Override
    public List<DormitoryDto> getDormListByApartId(Integer apartId) {
        if (apartId==null || apartId.longValue()==0){
            return null;
        }
        DormitoryExample dormitoryExample = new DormitoryExample();
        dormitoryExample.createCriteria().andApartIdEqualTo(apartId);

        List<Dormitory> dormitoryList = dormitoryDao.selectByExample(dormitoryExample);
        if (dormitoryList.size()>0){
            List<DormitoryDto> dormitoryDtoList = new ArrayList<DormitoryDto>();
            for (Dormitory dormitory: dormitoryList) {
                dormitoryDtoList.add(beanToDto(dormitory));
            }
            return dormitoryDtoList;
        }

        return null;
    }

    /**
     * 根据 apartID 新建公寓后初始化初始化其中的宿舍
     * [事实上现在我觉得宿舍还是应该由用户手动添加好，而不是自动生成]
     * @param id 公寓ID
     * @return 初始化的结果
     */
    @Override
    public void initDormByNewApartment(Integer id) {
        Apartment apartment = apartmentDao.selectByPrimaryKey(id);

        int floorNum = apartment.getFloorNum();
        int allDorm = apartment.getDormNum();
        int limit = allDorm / floorNum;
        int rest = allDorm % floorNum;
        // 得出limit的位数，用于补0.limit最大支持3位数（一层楼几百个宿舍也是蛮吓人的）
        int temp = limit;
        int length = 1;
        for (int x=0;temp>0;x++){
            length *= 10;
            temp /= 10;
        }

        Dormitory dormitory = new Dormitory();
        dormitory.setApartId(id);
        dormitory.setUsedBed(0);
        dormitory.setAllBed(apartment.getDormBedNum());
        dormitory.setDicDormType(apartment.getDicApartType());

        int dormId = 0;
        for (int i=0; i<floorNum;i++){
            // 构造每一层的宿舍
            for (int j=0;j<limit;j++){
                // 构造每一间宿舍
                dormId = length*(i+1)+j;
                dormitory.setDormId(dormId);
                dormitoryDao.insertSelective(dormitory);
            }
        }
        // 将剩余宿舍加到最后
        for (int i=0; i<rest; i++){
            dormId = floorNum*length + limit + i;
            dormitory.setDormId(dormId);
            dormitoryDao.insertSelective(dormitory);
        }
    }

    @Override
    public DormitoryDto getDormListById(Integer dormId) {
        DormitoryDto dormitoryDto = null;
        Dormitory dormitory = null;
        if (dormId!=null && dormId.longValue()!=0){
            dormitory = dormitoryDao.selectByPrimaryKey(dormId);
            if (dormitory!=null){
                dormitoryDto = beanToDto(dormitory);
            }
        }
        return dormitoryDto;
    }


    /**
     * 获取该宿舍下的所有学生信息
     * @param dormId
     * @return 查询到的学生
     */
    @Override
    public List<StudentDto> getStudentDtoListByDormId(Integer dormId) {
        List<Student> studentList = null;
        List<StudentDto> studentDtoList = new ArrayList<>();

        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andDormIdEqualTo(dormId);
        studentList = studentDao.selectByExample(studentExample);
        for (Student student: studentList){
            studentDtoList.add(studentService.beanToDto(student));
        }

        return studentDtoList;
    }

    /**
     * 往宿舍中添加单个学生，
     * 1. 检查数据真实性与逻辑正确（学生数是否已满，学生是否已经分配） TODO 更好的解决方案，在前端展示学生和宿舍状态，或者查询的时候只查询没有分配宿舍的学生。
     * 2. 更新学生、宿舍、公寓的状态。（状态和宿舍号，宿舍人数加1）
     * @param dormId 被添加宿舍ID
     * @param stuId 被添加学生ID
     * @return 执行结果代码
     */
    @Override
    public int distributeSingle(Integer dormId, Integer stuId) {
        Dormitory dormitory = dormitoryDao.selectByPrimaryKey(dormId);
        Student student = studentDao.selectByPrimaryKey(stuId);
        Apartment apartment = null;
        // 1.1
        if (dormitory!=null && student!=null){
            apartment = apartmentDao.selectByPrimaryKey(dormitory.getApartId());

            // 1.2
            if (dormitory.getUsedBed()/dormitory.getAllBed()==1 || student.getDormStatus()!=0){
                return PageCodeEnum.DISTRI_ADD_STU_FAIL_DISTRIED.getCode();
            }
            // 2.1
            student.setDormId(dormId);
            student.setDormStatus(2);
            apartment.setRemainBed(apartment.getRemainBed()-1);// 新增学生，公寓中剩余床位数-1
            int dormUseBed = dormitory.getUsedBed();
            if (dormUseBed==0){
                apartment.setUsedDorm(apartment.getUsedDorm()+1);// 是否新用了一间宿舍,是则更新
            }
            dormitory.setUsedBed(dormUseBed+1);// 宿舍已用床位+1
            // 设置宿舍长 -- 如果当时宿舍没有宿舍长，默认设置这个学生为宿舍长 TODO 这里的设置可以更好
            if (dormitory.getChiefId() == null){
                dormitory.setChiefId(student.getId());
            }
            // 2.2
            studentDao.updateByPrimaryKeySelective(student);
            dormitoryDao.updateByPrimaryKeySelective(dormitory);
            apartmentDao.updateByPrimaryKeySelective(apartment);
            return PageCodeEnum.DISTRI_ADD_STU_SUCCESS.getCode();
        }
        return PageCodeEnum.DISTRI_ADD_STUNOEXIST.getCode();
    }

    @Override
    public List<DormitoryDto> getDormListByClassId(Integer classId) {
        DormitoryDto dormitoryDto = new DormitoryDto();
        dormitoryDto.setClassId(classId);
        DormitoryExample dormitoryExample = getExampleByDto(dormitoryDto);
        List<DormitoryDto> dormitoryDtoList = new ArrayList<>();
        // forEach 没想象的那么好用

        List<Dormitory> dormitoryList= dormitoryDao.selectByExample(dormitoryExample);
        for (Dormitory dormitory: dormitoryList){
            dormitoryDtoList.add(beanToDto(dormitory));
        }

        return dormitoryDtoList;
    }
}
