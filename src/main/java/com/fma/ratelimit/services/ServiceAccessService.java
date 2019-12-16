package com.fma.ratelimit.services;

import java.time.Instant;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fma.ratelimit.dal.CommonHelper;
import com.fma.ratelimit.dal.RateLimitData;
import com.fma.ratelimit.exceptions.ServiceNotRegisteredException;
import com.fma.ratelimit.request.pojos.RegisterRateLimitBean;
import com.fma.ratelimit.request.pojos.ServiceAccessRequestPojo;
import com.fma.ratelimit.request.pojos.ServiceLimit;
import com.fma.ratelimit.request.pojos.ServiceLimits;
import com.fma.ratelimit.response.Response;

@Component
public class ServiceAccessService {

	@Autowired
	Response response;

	@Autowired
	CommonHelper commonHelper;

	public ResponseEntity<String> accessService(ServiceAccessRequestPojo serviceAccessRequestPojo)
	{	
		if(serviceAccessRequestPojo!=null )
		{
			try {		
				if(isAllowedToAccessService(commonHelper.getServiceHash(
						commonHelper.getRateLimitBean(serviceAccessRequestPojo.getServiceName(),serviceAccessRequestPojo.getApiName(),
								serviceAccessRequestPojo.getType()
								))))
				{
					return response.successResponse;
				}
			}
			catch(Exception exp)
			{
				System.err.println("exp "+exp.getMessage());
				return response.ErrorResponse;
			}
		}
		return response.ErrorResponse;
	}

	public boolean isAllowedToAccessService(String hash) throws ServiceNotRegisteredException
	{
		System.out.println(RateLimitData.hashRRLBMapLimits);
		System.out.println(RateLimitData.hashRRLBMapLimitsInterval);
		System.out.println(RateLimitData.rateLimitcounters);
		long now = Instant.now().toEpochMilli();
		if(RateLimitData.hashRRLBMapLimits.containsKey(hash))
		{
			int count = RateLimitData.hashRRLBMapLimits.get(hash);
			int intervalLimit = RateLimitData.hashRRLBMapLimitsInterval.get(hash);
			long interval = now-(intervalLimit*1000);
			System.out.println("-----------");
			System.out.println(RateLimitData.rateLimitcounters.subMap(interval, now));
		}

		throw new ServiceNotRegisteredException("Service Not registered");

	}

}
