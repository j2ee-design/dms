package com.xinho.constant;

public enum PageCodeEnum {
    STUDENT_ADD_SUCCESS(1000,"学生添加成功！"),
    STUDENT_ADD_FAIL(1001,"新增失败！请检查学生学号是否已存在，或者填入的学院、专业信息是否正确！"),
    MODIFY_SUCCESS(1100,"修改成功！"),
    MODIFY_FAIL(1101,"修改失败！"),
    STUDENT_REMOVE_SUCCESS(1200,"删除成功！"),
    STUDENT_REMOVE_FAIL(1201,"删除失败！"),
    LOGIN_FAIL(1301,"登录失败！用户名密码错误！"),
    SESSION_TIMEOUT(1302,"session超时，请重新登录！"),
    NO_AUTH(1303,"没有权限访问请求资源，请切换账户后重试！"),
    USERNAME_EXISTS(1401,"用户名已存在！"),
    GROUPNAME_EXISTS(1402,"用户组名已存在！"),
    ASSIGN_SUCCESS(1500,"分配成功！"),
    ASSIGN_FAIL(1501,"分配失败！"),;

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
