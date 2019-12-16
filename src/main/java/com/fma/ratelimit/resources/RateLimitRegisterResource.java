package com.fma.ratelimit.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fma.ratelimit.handlers.RateLimitRegistrationHandler;
import com.fma.ratelimit.request.pojos.ServiceLimits;
import com.google.gson.Gson;


@RestController
@RequestMapping("/ratelimit")
public class RateLimitRegisterResource {

	@Autowired
	RateLimitRegistrationHandler rateLimitRegistrationHandler;

	// API for ratelimit registration
	@RequestMapping(value = "/register/v1", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> registerMovie(@RequestBody String serviceLimits)
	{

		return rateLimitRegistrationHandler.register(serviceLimits);
	}

}
