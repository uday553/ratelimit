package com.fma.ratelimit.request.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class GET {

	@SerializedName("limit")
	@Expose
	private Integer limit;
	@SerializedName("granularity")
	@Expose
	private String granularity;

	public Integer getLimit() {
	return limit;
	}

	public void setLimit(Integer limit) {
	this.limit = limit;
	}

	public String getGranularity() {
	return granularity;
	}

	public void setGranularity(String granularity) {
	this.granularity = granularity;
	}



}