package com.jshine.performance.system.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionBean implements Serializable {
    private String id;
    private String name;
    private String url;
}
