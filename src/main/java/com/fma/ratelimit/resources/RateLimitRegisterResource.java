package com.fma.ratelimit.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger log = LoggerFactory.getLogger(RateLimitRegisterResource.class);

	@Autowired
	RateLimitRegistrationHandler rateLimitRegistrationHandler;

	// API for ratelimit registration
	@RequestMapping(value = "/register/v1", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> registerRateLimit(@RequestBody String serviceLimits)
	{
		log.debug("registerRateLimit Called");
		return rateLimitRegistrationHandler.register(serviceLimits);
	}

}
