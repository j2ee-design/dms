package com.xinho.constant;

public enum PageCodeEnum {
    STUDENT_ADD_SUCCESS(1000,"学生添加成功！"),
    STUDENT_ADD_FAIL(1001,"新增失败,请填入正确格式的学生信息！"),
    STUDENT_ADD_FAIL_DATA_NOT_EXIT(1002,"新增失败,输入的学院、专业或班级信息未找到！"),
    STUDENT_ADD_FAIL_DATA_Student_EXIT(1003,"新增失败,已存在的学号！"),
    STUDENT_ADD_FAIL_Logic(1004,"新增失败,不正确的学院与专业关系或班级关系！"),
    MODIFY_SUCCESS(1100,"修改学生信息成功！"),
    MODIFY_FAIL_DATA_VALIDATE(1101,"修改学生信息失败，输入数据格式错误"),
    MODIFY_FAIL_LOGIC_VALIDATE(1102,"修改学生信息失败，学院与班级或班级与宿舍之间的关系不匹配！"),
    MODIFY_FAIL_WRITE_TO_DB(1103,"修改学生信息失败，请检查输入数据真实性！"),
    MODIFY_FAIL(1104,"修改学生信息失败！！"),
    APART_ADD_SUCCESS(1200,"添加公寓成功！"),
    APART_ADD_FAIL_ID_EXIST(1201,"添加公寓失败，该公寓已经存在！"),
    APART_ADD_FAIL_DB_ERROR(1202,"添加公寓失败，数据库发生异常！"),
    DORM_SINGLE_SEARCH_SUCCESS(1300,"单个宿舍查找成功"),
    DORM_SINGLE_SEARCH_FAIL_NOT_EXIST(1301,"单个宿舍查找失败，宿舍不存在"),
    DISTRI_ADD_STU_SUCCESS(1400,"添加学生至宿舍成功"),
    DISTRI_ADD_STU_FAIL(1401,"添加学生至宿舍失败，未知错误"),
    DISTRI_ADD_STU_FAIL_DISTRIED(1402,"添加学生至宿舍失败，该学生已经分配宿舍/该宿舍人满。"),
    DISTRI_ADD_STUNOEXIST(1403,"添加学生失败，该学生ID/宿舍ID不存在"),



    STUDENT_REMOVE_SUCCESS(1200,"删除成功！"),
    STUDENT_REMOVE_FAIL(1201,"删除失败！"),
    LOGIN_FAIL(1301,"登录失败！用户名密码错误！"),
    SESSION_TIMEOUT(1302,"session超时，请重新登录！"),
    NO_AUTH(1303,"没有权限访问请求资源，请切换账户后重试！"),
    USERNAME_EXISTS(1401,"用户名已存在！"),
    GROUPNAME_EXISTS(1402,"用户组名已存在！"),
    ASSIGN_SUCCESS(1500,"分配成功！"),
    ASSIGN_FAIL(1501,"分配失败！"),
    ;

    private Integer code;
    private String msg;


    public static final String KEY = "pageCode";

    PageCodeEnum(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
