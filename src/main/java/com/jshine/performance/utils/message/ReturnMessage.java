package com.jshine.performance.utils.message;

public class ReturnMessage<T> {
	private Integer code;//错误码
	private String message;//提示信息
	private T data;//返回具体内容
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public ReturnMessage(Integer code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
