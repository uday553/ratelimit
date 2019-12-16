package com.fma.ratelimit.request.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ApiLimit {


	@SerializedName("methods")
	@Expose
	private Methods methods;
	@SerializedName("api")
	@Expose
	private String api;

	public Methods getMethods() {
		return methods;
	}

	public void setMethods(Methods methods) {
		this.methods = methods;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}


}