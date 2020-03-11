package com.jshine.performance.system.service;

import com.jshine.performance.system.model.SysUserInfo;

import java.util.List;



public interface SysUserInfoService {
  List<SysUserInfo> findList();
  List<SysUserInfo> testFindPage();
  void deleteEntity();
  void addUser(SysUserInfo user);
  List<SysUserInfo> findPage();
}
