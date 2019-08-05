package com.example.demo.beans.restful;

public class BaseResponse {

	int status;
	String description;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BaseResponse() {
		// TODO Auto-generated constructor stub
		this.status = 0;
		this.description = "success";
	}

	public BaseResponse(String description) {
		this.description = description;
	}
	
	public BaseResponse(int status, String description) {
		this.status = status;
		this.description = description;
	}
}
