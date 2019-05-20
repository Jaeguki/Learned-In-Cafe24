package com.cafe24.mysite.dto;

public class JSONResult {
	private String result;  //success, fail
	private String message; //if fail, set
	private Object data;    //if success, set
	
	private JSONResult(String result, String message, Object data) {
	}
	
	public String getResult() {
		return result;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
}
