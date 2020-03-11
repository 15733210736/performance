package com.jshine.performance.system.service.impl;

import com.jshine.performance.system.mapper.UserMapper;
import com.jshine.performance.system.model.UserBean;
import com.jshine.performance.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserBean findByName(String name) {
        UserBean bean = userMapper.findByName(name);
        if(bean != null){
            bean = userMapper.findById(bean.getId());
        }
        return bean;
    }
}
