package com.jshine.performance.system.model;

import lombok.Data;

@Data
public class SysUserInfo {

	private static final long serialVersionUID = 8943114639060423863L;
	private String userId;
	private String loginName;
	private String loginPwd;
	private Integer loginNum;
	
	
}
