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

import com.fma.ratelimit.handlers.ServiceAccessHandler;
import com.fma.ratelimit.request.pojos.ServiceAccessRequestPojo;

@RestController
@RequestMapping("/service/ratelimit")
public class RateLimitCheckResource {
	private static final Logger log = LoggerFactory.getLogger(RateLimitCheckResource.class);

	@Autowired
	ServiceAccessHandler serviceAccessHandler;

	// API for access service
	@RequestMapping(value = "/api/access/v1", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> accessService(@RequestBody ServiceAccessRequestPojo serviceAccessRequestPojo)
	{
		log.debug("{}",serviceAccessRequestPojo);
		return serviceAccessHandler.access(serviceAccessRequestPojo);
	}
	
}
