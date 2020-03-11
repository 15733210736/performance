package com.jshine.performance.utils.message;

public enum CodeEnum {

	OK(200, "请求成功"),
	BAD_REQUEST(400, "请求出错"),
	UNAUTHORIZED(401, "没有登录"),
	FORBIDDEN(403, "没有权限"),
	NOT_FOUND(404, "找不到页面"),
	INTERNAL_SERVER_ERROR(500, "服务器出错");
	
	
	private CodeEnum(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}
	private Integer code;
	private String msg;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
