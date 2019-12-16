package com.fma.ratelimit.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fma.ratelimit.request.pojos.ServiceLimits;
import com.fma.ratelimit.services.RegisterService;

@Component
public class RateLimitRegistrationHandler {

	@Autowired
	RegisterService registerService;
	
	public ResponseEntity<String> register(String serviceLimits)
	{
		return registerService.registerAPIs(serviceLimits);
	}
	
}
