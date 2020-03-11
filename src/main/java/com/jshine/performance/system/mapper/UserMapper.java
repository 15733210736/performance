package com.jshine.performance.system.mapper;

import com.jshine.performance.system.model.UserBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserBean findByName(String name);
    UserBean findById(String id);
}
