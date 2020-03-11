package com.jshine.performance.system.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserBean implements Serializable {
    private String id;
    private String name;
    private String password;
    private Set<RoleBean> roles = new HashSet<RoleBean>();
}
