package com.xinho.service.serviceImpl;

import com.xinho.bean.*;
import com.xinho.bean.Class;
import com.xinho.dao.AcademyDao;
import com.xinho.dao.ClassDao;
import com.xinho.dao.MajorDao;
import com.xinho.dao.StudentDao;
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

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private MajorDao majorDao;

    @Autowired
    private ClassDao classDao;

    @Autowired
    private AcademyDao academyDao;

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
        return errorResults;
    }

    /**
     * 添加一个学生（有两种想法：1. 根据 academy 表和 major 表插入到 student 表，2.先查找，根据查找结果选择要不要插入）
     * 1. 检查学院、专业、班级信息在数据库中的真实性
     * 2. 如果正确，构建要插入的 bean 对象。
     * 3. 插值，返回结果
     * @param studentDto
     * @return
     */
    public boolean addSingle(StudentDto studentDto) {
        /*  1. */
        AcademyExample academyExample = new AcademyExample();
        MajorExample majorExample = new MajorExample();
        ClassExample classExample = new ClassExample();
        academyExample.createCriteria().andNameEqualTo(studentDto.getAcademyName());
        majorExample.createCriteria().andNameEqualTo(studentDto.getMajorName());
        classExample.createCriteria().andClassIdEqualTo(studentDto.getClassName());

        if ( studentDao.selectByPrimaryKey(studentDto.getId()) != null ){
            return false;
        };

        int academyId = 0;
        int majorId = 0;
        int classId = 0;
        try{
            academyId = academyDao.selectByExample(academyExample).get(0).getId();
            majorId = majorDao.selectByExample(majorExample).get(0).getId();
            classId = classDao.selectByExample(classExample).get(0).getId();// 我艹，把这里的 classId 和 class 的 ID 搞混了。。。尼玛
        } catch (Exception e){
            // 如果产生异常（如果该数据不存在，那么会产生空指针异常），那么直接返回 false，节省一次查询提高性能。
            e.printStackTrace();
            return false;
        }

        Student student = new Student();
        BeanUtils.copyProperties(studentDto,student);
        student.setAcademyId(academyId);
        student.setClassId(classId);
        student.setMajorId(majorId);

        /*  3. */
        return studentDao.insertSelective(student)==1;
    }

    @Override
    public int removeStudentById(List<Integer> idList) {
//        int i = 0;
//        int sum = 0;
//        for (Integer id: idList) {
//            i = studentDao.deleteByPrimaryKey(id);
//            sum += i;
//            i = 0;
//        }
//        return sum;
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
     * 有两种方式，
     *  1. 先查询数据库，填充 Student，在用 Student 来查询。
     *  2. 直接用 dto 来查询。
     *  不知该如何取舍。严格的来说 dto 仅仅作用于各个层之间数据的传递与承载，它直接打交道的应该是页面数据，和数据库
     *  打交道的应该是 bean，但是基于性能的考虑，使用1方法会查询3次，2则只要查询1次。。。
     *  或许是我还没有弄清楚 dto 真正的解决的痛点所在吧。
     * @param studentDto
     * @return
     */
    @Override
    public List<StudentDto> searchStudent(StudentDto studentDto) {
        List<Student> studentList = studentDao.selectByExample(getExampleByDto(studentDto));
        List<StudentDto> studentDtoList = new ArrayList<StudentDto>();
        for (Student student: studentList) {
            studentDtoList.add(beanToDtoWithMoreInfo(student));
        }
        return studentDtoList;
    }

    @Override
    public StudentDto searchStudentById(Integer id) {
        StudentDto studentDto = null;
        if (id != null){
            Student student = studentDao.selectByPrimaryKey(id);
            studentDto = beanToDtoWithMoreInfo(student);
        }
        return studentDto;
    }

    public StudentExample getExampleByDto(StudentDto studentDto){

        StudentExample studentExample = new StudentExample();
        AcademyExample academyExample = new AcademyExample();
        MajorExample majorExample = new MajorExample();
        ClassExample classExample = new ClassExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();

        if (studentDto.getId()!=null){
            criteria.andIdEqualTo(studentDto.getId());
        }

        if (studentDto.getName()!=null){
            criteria.andNameEqualTo(studentDto.getName());
        }

        if(studentDto.getGender()!= null){
            criteria.andGenderEqualTo(studentDto.getGender());
        }

        if(studentDto.getEnroYear()!=null){
            criteria.andEnroYearEqualTo(studentDto.getEnroYear());
        }

        if (studentDto.getDicGrade()!=null){
            criteria.andDicGradeEqualTo(studentDto.getDicGrade());
        }

        if (studentDto.getAcademyId()!=null){
            criteria.andAcademyIdEqualTo(studentDto.getAcademyId());
        }

        if (studentDto.getMajorId()!=null){
            criteria.andMajorIdEqualTo(studentDto.getMajorId());
        }

        if (studentDto.getDormId()!=null){
            criteria.andDormIdEqualTo(studentDto.getDormId());
        }

        if (studentDto.getClassId()!=null){
            criteria.andClassIdEqualTo(studentDto.getClassId());
        }

        if (studentDto.getDormStatus()!=null){
            criteria.andDormStatusEqualTo(studentDto.getDormStatus());
        }

        if(studentDto.getStuStatus()!=null){
            criteria.andStuStatusEqualTo(studentDto.getStuStatus());
        }

        Object object = null;
        if (studentDto.getAcademyName()!=null){
            academyExample.createCriteria().andNameEqualTo(studentDto.getAcademyName());
            if ((object=academyDao.selectByExample(academyExample))!=null){
                List<Academy> academyListTemp = (List<Academy>) object;
                studentDto.setAcademyId(academyListTemp.get(0).getId());
                academyListTemp = null;
                criteria.andAcademyIdEqualTo(studentDto.getAcademyId());
            }
        }

        if (studentDto.getMajorName()!=null){
            majorExample.createCriteria().andNameEqualTo(studentDto.getMajorName());
            if ((object=majorDao.selectByExample(majorExample))!=null){
                List<Major> majorListTemp = (List<Major>) object;
                studentDto.setMajorId(majorListTemp.get(0).getId());
                majorListTemp = null;
                criteria.andMajorIdEqualTo(studentDto.getMajorId());
            }
        }

        if (studentDto.getClassName()!=null){
            classExample.createCriteria().andClassIdEqualTo(studentDto.getClassName());
            if ((object=classDao.selectByExample(classExample))!=null){
                List<Class> classListTemp = (List<Class>) object;
                studentDto.setMajorId(classListTemp.get(0).getId());
                classListTemp = null;
                criteria.andMajorIdEqualTo(studentDto.getMajorId());
            }
        }

        object = null;
        return studentExample;
    }

    public StudentDto beanToDtoWithMoreInfo(Student student){
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student,studentDto);

        AcademyExample academyExample = new AcademyExample();
        academyExample.createCriteria().andIdEqualTo(student.getAcademyId());
        String academyName = academyDao.selectByExample(academyExample).get(0).getName();
        if (academyName != null){
            studentDto.setAcademyName(academyName);
        }

        MajorExample majorExample = new MajorExample();
        majorExample.createCriteria().andIdEqualTo(student.getMajorId());
        String majorName = majorDao.selectByExample(majorExample).get(0).getName();
        if (majorName!=null){
            studentDto.setMajorName(majorName);
        }

        ClassExample classExample = new ClassExample();
        classExample.createCriteria().andIdEqualTo(student.getClassId());
        // 这里被自己弄晕了。。。Student 中的 ClassId 是 Class 中的 Id，Student 中的 ClassName 是 Class 中的 ClassId
        Integer className = classDao.selectByExample(classExample).get(0).getClassId();
        if (className!=null){
            studentDto.setClassName(className);
        }
        return studentDto;
    }
}
