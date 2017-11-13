package com.xinho.service;

import com.xinho.bean.SysUser;

public interface SysUserService {
    boolean validateDate(SysUser user);

    boolean validate(SysUser user);

    SysUser getUser(Integer id);
}
