package stu01.model;

public class ReturnCode {
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
private Integer code;
private String msg;
public ReturnCode(Integer code,String msg) {
	this.code=code;
	this.msg=msg;
}
}
