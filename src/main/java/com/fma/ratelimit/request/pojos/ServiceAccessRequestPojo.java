package com.fma.ratelimit.request.pojos;

import lombok.ToString;

@ToString
public class ServiceAccessRequestPojo {

	private String serviceName;
	private String apiName;
	private String type;

	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
