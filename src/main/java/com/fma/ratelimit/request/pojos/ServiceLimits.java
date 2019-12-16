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
public class ServiceLimits {

	@SerializedName("serviceLimits")
	@Expose
	private List<ServiceLimit> serviceLimits = null;

	public List<ServiceLimit> getServiceLimits() {
	return serviceLimits;
	}

	public void setServiceLimits(List<ServiceLimit> serviceLimits) {
	this.serviceLimits = serviceLimits;
	}

}