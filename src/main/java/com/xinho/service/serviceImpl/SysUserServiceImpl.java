package com.xinho.service.serviceImpl;

import com.xinho.bean.SysUser;
import com.xinho.dao.SysUserDao;
import com.xinho.service.SysUserService;
import com.xinho.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService{

    @Autowired private SysUserDao sysUserDao;

    @Override
    public boolean validateDate(SysUser user) {
        // 非空
        if (user.getId()==null || user.getPassword()==null || user.getPassword().trim().length()==0){
            return false;
        }
        // 用户名为数字
        if (!CommonUtils.isNumber(user.getId())){
            return false;
        }
        return true;
    }

    @Override
    public boolean validate(SysUser user) {
        SysUser sysUser = sysUserDao.selectByPrimaryKey(user.getId());
        if (sysUser==null){
            return false;
        }
        return sysUser.getPassword().equals(user.getPassword());
    }
}








