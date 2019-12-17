package com.fma.ratelimit.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fma.ratelimit.request.pojos.ServiceAccessRequestPojo;
import com.fma.ratelimit.request.pojos.ServiceLimits;
import com.fma.ratelimit.services.RegisterService;
import com.fma.ratelimit.services.ServiceAccessService;

@Component
public class ServiceAccessHandler {
	private static final Logger log = LoggerFactory.getLogger(ServiceAccessHandler.class);

	@Autowired
	ServiceAccessService serviceAccessService;
	
	public ResponseEntity<String> access(ServiceAccessRequestPojo serviceAccessRequestPojo)
	{
		return serviceAccessService.accessService(serviceAccessRequestPojo);
	}
	
}
