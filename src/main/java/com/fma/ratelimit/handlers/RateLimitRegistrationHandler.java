package com.fma.ratelimit.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fma.ratelimit.RateLimitApplication;
import com.fma.ratelimit.request.pojos.ServiceLimits;
import com.fma.ratelimit.services.RegisterService;

@Component
public class RateLimitRegistrationHandler {
	private static final Logger log = LoggerFactory.getLogger(RateLimitRegistrationHandler.class);
	@Autowired
	RegisterService registerService;
	
	public ResponseEntity<String> register(String serviceLimits)
	{
		return registerService.registerAPIs(serviceLimits);
	}
	
}
