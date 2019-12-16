package com.fma.ratelimit.request.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Methods {

    @SerializedName("GET")
    @Expose
    private GET get;
    @SerializedName("POST")
    @Expose
    private POST post;
	public GET getGet() {
		return get;
	}
	public void setGet(GET get) {
		this.get = get;
	}
	public POST getPost() {
		return post;
	}
	public void setPost(POST post) {
		this.post = post;
	}

}