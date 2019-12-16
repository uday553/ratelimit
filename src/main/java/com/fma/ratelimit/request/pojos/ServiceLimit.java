package com.fma.ratelimit.request.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ServiceLimit {

	@SerializedName("service")
	@Expose
	private String service;
	@SerializedName("globalLimits")
	@Expose
	private GlobalLimits globalLimits;
	@SerializedName("apiLimits")
	@Expose
	private List<ApiLimit> apiLimits = null;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public GlobalLimits getGlobalLimits() {
		return globalLimits;
	}

	public void setGlobalLimits(GlobalLimits globalLimits) {
		this.globalLimits = globalLimits;
	}

	public List<ApiLimit> getApiLimits() {
		return apiLimits;
	}

	public void setApiLimits(List<ApiLimit> apiLimits) {
		this.apiLimits = apiLimits;
	}


}