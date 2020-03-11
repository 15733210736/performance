package com.jshine.performance.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jshine.performance.system.mapper.SysUserInfoMapper;
import com.jshine.performance.system.model.SysUserInfo;
import com.jshine.performance.system.service.SysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("sysUserInfoService")
public class SysUserInfoServiceImpl implements SysUserInfoService {
	 @Autowired
	 private SysUserInfoMapper  sysUserInfoMapper;

	@Override
	public List<SysUserInfo> findList() {
		IPage<SysUserInfo> userPage = new Page<>(1, 2);
		QueryWrapper<SysUserInfo> wrapper=new QueryWrapper<SysUserInfo>();
		wrapper.eq("LOGIN_NAME","zhangsan").eq("LOGIN_PWD","e43eb99dc48776beb62a5e8d84ae08d6");

		//sysUserInfoMapper.delete(wrapper);
		IPage<SysUserInfo> userIPage =sysUserInfoMapper.selectPage(userPage,wrapper);
		return userIPage.getRecords();
	}

	@Override
	public List<SysUserInfo> testFindPage() {
		IPage<SysUserInfo> userPage = new Page<>(1, 2);
		QueryWrapper<SysUserInfo> wrapper=new QueryWrapper<SysUserInfo>();
		wrapper.eq("LOGIN_NAME","kqgly").eq("LOGIN_PWD","e43eb99dc48776beb62a5e8d84ae08d6");
		return sysUserInfoMapper.testFindPage(userPage,wrapper);
	}

	@Override
	public void deleteEntity() {
		QueryWrapper<SysUserInfo> wrapper=new QueryWrapper<SysUserInfo>();
		wrapper.eq("USER_ID","3");
		sysUserInfoMapper.delete(wrapper);
        SysUserInfo userInfo =new SysUserInfo();
		userInfo.setUserId("sdsd1111");
		userInfo.setLoginName("55555");
		sysUserInfoMapper.insert(userInfo);
		int i=5/0;
	}
	@Override
	public void addUser(SysUserInfo user){
		user.setUserId("sdfasdf4s4df4sdf4sdf4sdf4");
		sysUserInfoMapper.insert(user);
	};
	@Override
	public List<SysUserInfo> findPage(){
		IPage<SysUserInfo> userPage = new Page<>(1, 5);
		return  sysUserInfoMapper.selectPage(userPage,null).getRecords();
	};
}
