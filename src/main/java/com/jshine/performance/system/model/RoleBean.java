package com.jshine.performance.system.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class RoleBean implements Serializable {
    private String id;
    private String name;
    private Set<PermissionBean> permissions = new HashSet<PermissionBean>();
}
