package com.jshine.performance.system.service;

import com.jshine.performance.system.model.UserBean;

public interface UserService {
    UserBean findByName(String name);
}
