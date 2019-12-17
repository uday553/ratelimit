package com.fma.ratelimit.services;

import java.time.Instant;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.fma.ratelimit.resources.RateLimitCheckResource;
import com.fma.ratelimit.response.Response;

@Component
public class ServiceAccessService {

	private static final Logger log = LoggerFactory.getLogger(ServiceAccessService.class);

	@Autowired
	Response response;

	@Autowired
	CommonHelper commonHelper;

	public ResponseEntity<String> accessService(ServiceAccessRequestPojo serviceAccessRequestPojo)
	{	
		if(serviceAccessRequestPojo!=null )
		{
			try {
				if(serviceAccessRequestPojo.getApiName()!=null)
				{
					if(isAllowedToAccessService(commonHelper.getServiceHash(commonHelper.getRateLimitBean(serviceAccessRequestPojo.getServiceName(),null,serviceAccessRequestPojo.getType()))))
					{
						return response.successResponse;
					}else {
						return response.tresholdCrossedResponse;
					}
				}
				if(isAllowedToAccessService(commonHelper.getServiceHash(commonHelper.getRateLimitBean(serviceAccessRequestPojo.getServiceName(),serviceAccessRequestPojo.getApiName(), serviceAccessRequestPojo.getType()))))
				{
					return response.successResponse;
				}
				
			}
			catch(Exception exp)
			{
				log.error("exp {}",exp);
				return response.ErrorResponse;
			}
		}
		return response.ErrorResponse;
	}

	public boolean isAllowedToAccessService(String hash) throws ServiceNotRegisteredException
	{
		long now = Instant.now().toEpochMilli();
		boolean status=false;
		if(RateLimitData.hashRRLBMapLimits.containsKey(hash) && RateLimitData.hashRRLBMapLimitsInterval.containsKey(hash))
		{
			if(RateLimitData.rateLimitcounters.size()>0)
			{
				if(getCountOfAccessedServices(hash,(now-(RateLimitData.hashRRLBMapLimitsInterval.get(hash)*1000)),now) <= RateLimitData.hashRRLBMapLimits.get(hash))
				{
					if(RateLimitData.rateLimitcounters.containsKey(now))
					{
						RateLimitData.rateLimitcounters.get(now).put(hash, RateLimitData.rateLimitcounters.get(now).get(hash)+1);
						status=true;
					}else {
						status =  addRateLimitInitialValue(now,hash);
					}
				}
			}else {
				status =  addRateLimitInitialValue(now,hash);
			}
			return status;
		}
		else {
			throw new ServiceNotRegisteredException("Service Not registered");
		}

	}
	public boolean addRateLimitInitialValue(long time, String hash)
	{
		ConcurrentHashMap<String, Integer> rateLimitMap = new ConcurrentHashMap<String, Integer>();
		rateLimitMap.put(hash, 1);
		RateLimitData.rateLimitcounters.put(time,rateLimitMap);
		return true;
	}
	public int getCountOfAccessedServices( String hash,long start, long end )
	{
		int count =0;
		for (;start<=end;start++)
		{
			if(RateLimitData.rateLimitcounters.containsKey(start))
			{
				if(RateLimitData.rateLimitcounters.get(start).containsKey(hash))
				{
					count = count +RateLimitData.rateLimitcounters.get(start).get(hash);
				}
			}
		}	
		return count;
	}
}
