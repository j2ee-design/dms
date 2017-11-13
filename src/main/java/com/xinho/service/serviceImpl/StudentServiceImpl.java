package com.xinho.service.serviceImpl;

import com.xinho.bean.*;
import com.xinho.bean.Class;
import com.xinho.dao.*;
import com.xinho.dto.StudentDto;
import com.xinho.service.StudentService;
import com.xinho.util.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired private ApartmentDao apartmentDao;

    @Autowired private StudentDao studentDao;

    @Autowired private MajorDao majorDao;

    @Autowired private ClassDao classDao;

    @Autowired private AcademyDao academyDao;

    @Autowired private DormitoryDao dormitoryDao;

    /**
     * TODO 这个函数可以用反射实现嘛。
     * 自定义校检方法，用正则表达式过滤掉所有错误输入。。。。。
     * @param studentDto 校检的 dto
     * @return 错误输入对应的标志
     */
    @Override
    public List<String> validate(StudentDto studentDto) {
        List<String> errorResults = new ArrayList<String>();
        if (CommonUtils.isEmpty(studentDto.getName())) {
            errorResults.add("name");
        }
        if (CommonUtils.isEmpty(studentDto.getAcademyName())) {
            errorResults.add("academyName");
        }
        if (CommonUtils.isEmpty(studentDto.getMajorName())) {
            errorResults.add("majorName");
        }
        if (!CommonUtils.isNumber(studentDto.getClassName())){
            errorResults.add("className");
        }
        if (!CommonUtils.isNumber(studentDto.getDicGrade())){
            errorResults.add("dicGrade");
        }
        return errorResults;
    }

    public List<String> validateModify(StudentDto studentDto) {
        List<String> errorResults = new ArrayList<String>();
        if (studentDto.getClassName()!=null){
            if (!CommonUtils.isNumber(studentDto.getClassName())){
                errorResults.add("className");
            }
        }
        if (studentDto.getDormName()!=null){
            if (!CommonUtils.isNumber(studentDto.getDormName())){
                errorResults.add("dormName");
            }
        }
        return errorResults;
    }

    /**
     * 将用户输入的学生信息补全，注意几点：
     * 1. 要置空的，即用户删除的数据，name,年级,入学日期，学院，专业，班级，宿舍。不过鉴于我在页面写死必须用户输入，（也就是说正常的数据肯定是有的）
     * 2. 要默认原来数据的，没有提供给用户修改的：在学状态，id,【我在这偷个懒，就不查找了，直接将Name对应的ID注入】
     * 3. 不能为空的，性别
     * @param studentDto
     */
    @Override
    public void finishStudentInfo(StudentDto studentDto) {

    }

    @Override
    public void updateDorm(Integer dormName, Integer studentId) {
        Student student = studentDao.selectByPrimaryKey(studentId);
        StudentDto studentDto = beanToDto(student);
        if (studentDto.getDormName().longValue()!=dormName.longValue()){
            Dormitory dormitory = dormitoryDao.selectByPrimaryKey(studentDto.getDormId());
            dormitory.setUsedBed(dormitory.getUsedBed()+1);
            dormitoryDao.updateByPrimaryKeySelective(dormitory);
        }
    }

    @Override
    public int[] removeStudentByIdStr(String idStr) {
        String[] idListStr = idStr.split(",");
        List<Integer> idList = new ArrayList<Integer>();
        for (int i=0; i<idListStr.length; i++){
            idList.add(Integer.parseInt(idListStr[i]));
        }
        int success = removeStudentById(idList);
        int all = idListStr.length;
        int[] results = {success,all};
        return results;
    }

    @Override
    public boolean validateInfoExact(StudentDto studentDto) {
        // 1. 检验学院是否存在
        AcademyExample academyExample = new AcademyExample();
        academyExample.createCriteria().andNameEqualTo(studentDto.getAcademyName());
        if (academyDao.selectByExample(academyExample).size()!=1){
            return false;
        }

        // 检验专业是否存在
        MajorExample majorExample = new MajorExample();
        majorExample.createCriteria().andNameEqualTo(studentDto.getMajorName());
        if (majorDao.selectByExample(majorExample).size()!=1){
            return false;
        }

        // 检验班级是否存在
        ClassExample classExample = new ClassExample();
        classExample.createCriteria().andClassIdEqualTo(studentDto.getClassName());
        if (classDao.selectByExample(classExample).size()!=1){
            return false;
        }
        return true;
    }

    /**
     * 判断dto中专业和学院、班级和专业+年级的对应关系是否正确
     * @param studentDto
     * @return 逻辑正确返回 true，逻辑错误返回 false
     */
    @Override
    public boolean validateAddLogic(StudentDto studentDto) {
        // 1. 校检学院和专业的关系
        MajorExample majorExample = new MajorExample();
        majorExample.createCriteria().andNameEqualTo(studentDto.getMajorName());
        Major major = majorDao.selectByExample(majorExample).get(0);
        Integer academyIdInMajor = major.getAcademyId();

        AcademyExample academyExample = new AcademyExample();
        academyExample.createCriteria().andNameEqualTo(studentDto.getAcademyName());
        Integer academyIdInAcademy = academyDao.selectByExample(academyExample).get(0).getId();

        if (academyIdInAcademy.longValue()!=academyIdInMajor.longValue()){
            return false;
        }

        // 校检 专业+年级 和 班级是否匹配
        ClassExample classExample = new ClassExample();
        classExample.createCriteria().andClassIdEqualTo(studentDto.getClassName());
        Class myClass = classDao.selectByExample(classExample).get(0);
        if (myClass.getMajorId().longValue()!=major.getId().longValue()){
            return false;
        }
        if (myClass.getDicGrade().longValue() != studentDto.getDicGrade().longValue()){
            return false;
        }
        return true;
    }

    /**
     * 添加一个学生
     * @param studentDto
     * @return
     */
    @Override
    public boolean addSingle(StudentDto studentDto) {
        Student student = dtoToBean(studentDto);
        return studentDao.insertSelective(student)==1;
    }

    @Override
    public int removeStudentById(List<Integer> idList) {
        return studentDao.deleteBatchByIdList(idList);
    }

    @Override
    public int removeStudentById(Integer id) {
        if (id!=0){
            studentDao.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public int removeStudent(StudentDto studentDto) {
        return studentDao.deleteByExample(getExampleByDto(studentDto));
    }

    /**
     * 查询情况如下：
     * 1. 是否有宿舍ID，有则根据宿舍ID查询
     * 2. 是否有班级ID，有则根据班级ID查询
     * 3. 是否有专业ID，有则根据专业ID+年级ID查询
     * 4. 是否有学院ID，有则根据学院ID+年级ID查询
     * 5. 是否有年级ID，有则根据年级ID查询
     * ***如果性别不为空，则将性别也算上（宿舍分配用）***
     * @param studentDto
     * @return
     */
    @Override
    public List<StudentDto> searchStudent(StudentDto studentDto) {
        List<Student> studentList = null;
        List<StudentDto>  studentDtoList = new ArrayList<StudentDto>();
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();

        // 退宿用
        if (studentDto.getDormStatus()!=null){
            criteria.andDormStatusEqualTo(studentDto.getDormStatus());
        }
        // 宿舍分配用，性别和宿舍分配状态。
        if (studentDto.getGender()!=null){
            criteria.andGenderEqualTo(studentDto.getGender());
        }
        if (studentDto.getDormStatus()!=null && studentDto.getDormStatus()==0){
            criteria.andDormStatusEqualTo(studentDto.getDormStatus());
        }

        if (studentDto.getDormId()!=null){
            criteria.andDormIdEqualTo(studentDto.getDormId());
            studentList = studentDao.selectByExample(studentExample);
            for (Student student: studentList){
                studentDtoList.add(beanToDto(student));
            }
            return studentDtoList;
        }

        if (studentDto.getClassId()!=null){
            criteria.andClassIdEqualTo(studentDto.getClassId());
            studentList = studentDao.selectByExample(studentExample);
            for (Student student: studentList){
                studentDtoList.add(beanToDto(student));
            }
            return studentDtoList;
        }

        // dicGrade 和后面几个搜索条件可以并列，因此需要设置。
        if (studentDto.getDicGrade()!=null){
            criteria.andDicGradeEqualTo(studentDto.getDicGrade());
        }

        if (studentDto.getMajorId()!=null){
            criteria.andMajorIdEqualTo(studentDto.getMajorId());
            studentList = studentDao.selectByExample(studentExample);
            for (Student student: studentList){
                studentDtoList.add(beanToDto(student));
            }
            return studentDtoList;
        }

        if (studentDto.getAcademyId()!=null){
            criteria.andAcademyIdEqualTo(studentDto.getAcademyId());
            studentList = studentDao.selectByExample(studentExample);
            for (Student student: studentList){
                studentDtoList.add(beanToDto(student));
            }
            return studentDtoList;
        }

        if (studentDto.getDicGrade()!=null){
            studentList = studentDao.selectByExample(studentExample);
            for (Student student: studentList){
                studentDtoList.add(beanToDto(student));
            }
            return studentDtoList;
        }



        studentList = studentDao.selectByExample(studentExample);
        for (Student student: studentList){
            studentDtoList.add(beanToDto(student));
        }
        return studentDtoList;
    }

    /**
     * 查找所有学生，初始化学生list页面用
     * @return
     */
    @Override
    public List<StudentDto> searchAllStudent() {
        List<StudentDto> studentDtoList = new ArrayList<>();
        List<Student> studentList = studentDao.selectByExample(new StudentExample());
        for(Student student:studentList){
            studentDtoList.add(beanToDto(student));
        }
        return studentDtoList;
    }

    /**
     * 根据学生ID查找学生
     * @param id 查找的 ID
     * @return 如果ID为空/不正确，则返回 null，否则返回查询到的值。
     */
    @Override
    public StudentDto searchStudentById(Integer id) {
        StudentDto studentDto = null;
        if (id != null){
            Student student = studentDao.selectByPrimaryKey(id);
            if (student!=null) {
                studentDto = beanToDto(student);
            }
        }
        return studentDto;
    }

    @Override
    public boolean modifyStudentByDto(StudentDto studentDto) {
        // 更新有两种方式，一种如此，selective 来更新（空值不更新），另一种则是全更新，但是要在写入之前先查一遍在覆盖。
        return studentDao.updateByPrimaryKeySelective(dtoToBean(studentDto))==1;
    }

    /**
     * 更新学生信息的逻辑验证：
     * 当前数据下
     * 1. 学院与专业是否正确
     * 2. 专业+年级与班级是否匹配
     * @param studentDto 要校检的 dto
     * @return 校检结果
     */
    @Override
    public boolean modifyLogicValidate(StudentDto studentDto) {
        // 1. 校检学院和专业的关系
        MajorExample majorExample = new MajorExample();
        majorExample.createCriteria().andNameEqualTo(studentDto.getMajorName());
        Major major = majorDao.selectByExample(majorExample).get(0);
        Integer academyIdInMajor = major.getAcademyId();

        AcademyExample academyExample = new AcademyExample();
        academyExample.createCriteria().andNameEqualTo(studentDto.getAcademyName());
        Integer academyIdInAcademy = academyDao.selectByExample(academyExample).get(0).getId();

        if (academyIdInAcademy.longValue()!=academyIdInMajor.longValue()){
            return false;
        }

        // 校检 专业+年级 和 班级是否匹配
        ClassExample classExample = new ClassExample();
        classExample.createCriteria().andClassIdEqualTo(studentDto.getClassName());
        Class myClass = classDao.selectByExample(classExample).get(0);
        if (myClass.getMajorId().longValue()!=major.getId().longValue()){
            return false;
        }
        if (myClass.getDicGrade().longValue() != studentDto.getDicGrade().longValue()){
            return false;
        }

        // 判断学生性别和宿舍类型是否匹配
        String dormNameTemp = studentDto.getDormName().longValue()+"";
        Integer dormName = Integer.parseInt(dormNameTemp.substring(dormNameTemp.length()-3,dormNameTemp.length()));
        DormitoryExample dormitoryExample = new DormitoryExample();
        dormitoryExample.createCriteria().andDormIdEqualTo(dormName);
        Dormitory dormitory = dormitoryDao.selectByExample(dormitoryExample).get(0);
        Integer dormType = dormitory.getDicDormType();
        if (dormType==10003||dormType==10006||dormType==10009){
            if (studentDto.getGender().longValue()!=0){
                return false;
            }
        } else if (dormType==10004||dormType==10007||dormType==100011){
            if (studentDto.getGender().longValue()!=1){
                return false;
            }
        }
        // 检查新宿舍是否住满
        if (dormitory.getId()!= studentDto.getDormId()){
            if (dormitory.getUsedBed().longValue() == dormitory.getAllBed().longValue()){
                return false;
            }
        }

        return true;
    }

    /**
     * 检验用户输入数据的真实性
     * @param studentDto
     * @return
     */
    @Override
    public boolean modifyIsDataReal(StudentDto studentDto) {
        // 检验性别输入
        int gender = studentDto.getGender();
        if (gender!=0){
            if (gender!=1){
                return false;
            }
        }

        // 1. 检验学院是否存在
        if (studentDto.getAcademyName()!=null){
            AcademyExample academyExample = new AcademyExample();
            academyExample.createCriteria().andNameEqualTo(studentDto.getAcademyName());
            if (academyDao.selectByExample(academyExample).size()!=1){
                return false;
            }
        }
        // 检验专业是否存在
        MajorExample majorExample = new MajorExample();
        majorExample.createCriteria().andNameEqualTo(studentDto.getMajorName());
        if (majorDao.selectByExample(majorExample).size()!=1){
            return false;
        }

        // 检验班级是否存在
        ClassExample classExample = new ClassExample();
        classExample.createCriteria().andClassIdEqualTo(studentDto.getClassName());
        if (classDao.selectByExample(classExample).size()!=1){
            return false;
        }
        // 检验宿舍是否存在
        String dormNameTemp = studentDto.getDormName().longValue()+"";
        Integer dormName = Integer.parseInt(dormNameTemp.substring(dormNameTemp.length()-3,dormNameTemp.length()));
        DormitoryExample dormitoryExample = new DormitoryExample();
        dormitoryExample.createCriteria().andDormIdEqualTo(dormName);
        if (dormitoryDao.selectByExample(dormitoryExample).size()!=1){
            return false;
        }

        return true;
    }

    /**
     * 通过 dto 获取搜索条件（就是给通过dto搜索使用的）
     * @param studentDto
     * @return 条件对象
     */
    public StudentExample getExampleByDto(StudentDto studentDto){
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();

        Student student = dtoToBean(studentDto);

        if (student.getId()!=null){
            criteria.andIdEqualTo(student.getId());
        }

        if (student.getName()!=null){
            criteria.andNameEqualTo(student.getName());
        }

        if(student.getGender()!= null){
            criteria.andGenderEqualTo(student.getGender());
        }

        if(student.getEnroYear()!=null){
            criteria.andEnroYearEqualTo(student.getEnroYear());
        }

        if (student.getDicGrade()!=null){
            criteria.andDicGradeEqualTo(student.getDicGrade());
        }

        if (student.getAcademyId()!=null){
            criteria.andAcademyIdEqualTo(student.getAcademyId());
        }

        if (student.getMajorId()!=null){
            criteria.andMajorIdEqualTo(student.getMajorId());
        }

        if (student.getDormId()!=null){
            criteria.andDormIdEqualTo(student.getDormId());
        }

        if (student.getClassId()!=null){
            criteria.andClassIdEqualTo(student.getClassId());
        }

        if (student.getDormStatus()!=null){
            criteria.andDormStatusEqualTo(student.getDormStatus());
        }

        if(student.getStuStatus()!=null){
            criteria.andStuStatusEqualTo(studentDto.getStuStatus());
        }

        return studentExample;
    }

    /**
     * Bean -> Dto ，根据 bean 中已有的信息在数据库中搜索不全 dto 中的信息
     * @param student
     * @return
     */
    public StudentDto beanToDto(Student student){
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student,studentDto);

        if (student.getAcademyId()!=null && student.getAcademyId()!=0){
            AcademyExample academyExample = new AcademyExample();
            academyExample.createCriteria().andIdEqualTo(student.getAcademyId());
            String academyName = academyDao.selectByPrimaryKey(student.getAcademyId()).getName();
            studentDto.setAcademyName(academyName);
        }

        if (student.getMajorId()!=null && student.getMajorId()!=0){
            MajorExample majorExample = new MajorExample();
            majorExample.createCriteria().andIdEqualTo(student.getMajorId());
            String majorName = majorDao.selectByPrimaryKey(student.getMajorId()).getName();
            studentDto.setMajorName(majorName);
        }

        if (student.getClassId()!=null && student.getClassId()!=0){
            ClassExample classExample = new ClassExample();
            classExample.createCriteria().andIdEqualTo(student.getClassId());
            // 这里被自己弄晕了。。。Student 中的 ClassId 是 Class 中的 Id，Student 中的 ClassName 是 Class 中的 ClassId
            Integer className = classDao.selectByPrimaryKey(student.getClassId()).getClassId();
            studentDto.setClassName(className);
        }

        if (student.getDormId()!=null && student.getDormId()!=0){
            DormitoryExample dormitoryExample = new DormitoryExample();
            dormitoryExample.createCriteria().andIdEqualTo(student.getDormId());
            Dormitory dormitory =  dormitoryDao.selectByPrimaryKey(student.getDormId());
            Integer apartId = dormitory.getApartId();
            Integer dormId = dormitory.getDormId();
            Integer dormName = Integer.parseInt((""+apartId.longValue()+dormId.longValue()));
            studentDto.setDormName(dormName);
        }

        return studentDto;
    }

    /**
     * 根据搜索条件批量删除。
     * TODO ×
     * @param studentDto
     * @return
     */
    @Override
    public int searchStudentDelete(StudentDto studentDto) {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();

        if (studentDto.getDicGrade()!=null){
            criteria.andDicGradeEqualTo(studentDto.getDicGrade());
        }
        if (studentDto.getAcademyId()!=null){
            criteria.andAcademyIdEqualTo(studentDto.getAcademyId());
        }
        if (studentDto.getMajorId()!=null){
            criteria.andMajorIdEqualTo(studentDto.getMajorId());
        }
        if (studentDto.getClassId()!=null){
            criteria.andClassIdEqualTo(studentDto.getClassId());
        }
        criteria.andDormStatusEqualTo(2);

        int successNum = 0;
        Dormitory dormitory = null;
        Apartment apartment = null;
        // 把每个学生都查出来，因为要连带的修改宿舍和公寓信息
        List<Student> studentList = studentDao.selectByExample(studentExample);
        for (Student student: studentList){
            if (student.getDormId()!=null){
                dormitory = dormitoryDao.selectByPrimaryKey(student.getDormId());
                dormitory.setUsedBed(dormitory.getUsedBed()-1);
                apartment = apartmentDao.selectByPrimaryKey(dormitory.getApartId());
                apartment.setRemainBed(apartment.getRemainBed()+1);
                if (dormitory.getUsedBed()==0){
                    apartment.setUsedDorm(apartment.getUsedDorm()-1);
                    dormitory.setChiefId(null);
                    dormitory.setClassId(null);
                }
                dormitoryDao.updateByPrimaryKey(dormitory);
                apartmentDao.updateByPrimaryKey(apartment);
            }
            student.setDormId(null);
            student.setDormStatus(0);
            successNum += studentDao.updateByPrimaryKey(student);
        }
        return successNum;
    }

    /**
     * 通过 dto 将 bean 补全
     * @param studentDto 蓝本 dto
     * @return 返回的 bean
     */
    public Student dtoToBean(StudentDto studentDto){
        Student student = new Student();
        BeanUtils.copyProperties(studentDto,student);
        if (studentDto.getAcademyName()!=null && studentDto.getAcademyId()==null){
            AcademyExample academyExample = new AcademyExample();
            academyExample.createCriteria().andNameEqualTo(studentDto.getAcademyName());
            student.setAcademyId(academyDao.selectByExample(academyExample).get(0).getId());
        }
        if (studentDto.getClassName()!=null && studentDto.getClassId()==null){
            ClassExample classExample = new ClassExample();
            classExample.createCriteria().andClassIdEqualTo(studentDto.getClassName());
            student.setClassId(classDao.selectByExample(classExample).get(0).getId());
        }
        if (studentDto.getMajorName()!=null && studentDto.getMajorId()==null){
            MajorExample majorExample = new MajorExample();
            majorExample.createCriteria().andNameEqualTo(studentDto.getMajorName());
            student.setMajorId(majorDao.selectByExample(majorExample).get(0).getId());
        }
        if (studentDto.getDormName()!=null && studentDto.getDormId()==null){
            String dormNameTemp = studentDto.getDormName().longValue()+"";
            // 规定最后三位是宿舍表中的 dorm_id
            Integer dormName = Integer.parseInt(dormNameTemp.substring(dormNameTemp.length()-3,dormNameTemp.length()));
            DormitoryExample dormitoryExample = new DormitoryExample();
            dormitoryExample.createCriteria().andDormIdEqualTo(dormName);
            student.setDormId(dormitoryDao.selectByExample(dormitoryExample).get(0).getId());
        }
        return student;
    }
}
