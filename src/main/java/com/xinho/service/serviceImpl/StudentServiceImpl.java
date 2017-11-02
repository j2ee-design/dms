package com.xinho.service.serviceImpl;

import com.xinho.bean.AcademyExample;
import com.xinho.bean.ClassExample;
import com.xinho.bean.MajorExample;
import com.xinho.bean.Student;
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
            errorResults.add("academyStr");
        }
        if (CommonUtils.isEmpty(studentDto.getMajorName())) {
            errorResults.add("majorStr");
        }
        return errorResults;
    }



    /**
     * 添加一个学生（有两种想法：1. 根据 academy 表和 major 表插入到 student 表，2.先查找，根据查找结果选择要不要插入）
     * 1. 检查学院、专业在数据库中的真实性
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
        classExample.createCriteria().andClassIdEqualTo(studentDto.getClassId());

        if ( studentDao.selectByPrimaryKey(studentDto.getId()) != null ){
            return false;
        };

        int academyId = 0;
        int majorId = 0;
        int classId = 0;
        try{
            academyId = academyDao.selectByExample(academyExample).get(0).getId();
            majorId = majorDao.selectByExample(majorExample).get(0).getId();
            classId = classDao.selectByExample(classExample).get(0).getId();
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

}
