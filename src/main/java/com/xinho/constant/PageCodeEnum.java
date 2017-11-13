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
    DELETE_SUCCESS(1500,"删除成功"),
    DELETE_FAIL_NO_DISCRI(1501,"删除失败，该生未分配宿舍。"),
    DELETE_FAIL_NO_STU(1502,"删除失败，该学生不存在。"),
    DELETEe_FAIL_DONT_KNOW(1503,"删除失败，未知错误"),
    USER_LOGIN_FAIL_DATA_ILLIGLE(1601,"登录失败，数据非法"),
    USER_LOGIN_FAIL_VALIDATE(1602,"登录失败，用户名或密码不正确");


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
