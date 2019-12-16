package com.fma.ratelimit.request.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class GlobalLimits {

	@SerializedName("GET")
	@Expose
	private GET gET;
	@SerializedName("POST")
	@Expose
	private POST pOST;

	public GET getGET() {
		return gET;
	}

	public void setGET(GET gET) {
		this.gET = gET;
	}

	public POST getPOST() {
		return pOST;
	}

	public void setPOST(POST pOST) {
		this.pOST = pOST;
	}

}