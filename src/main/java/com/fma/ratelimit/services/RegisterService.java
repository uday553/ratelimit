package com.fma.ratelimit.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fma.ratelimit.constants.Constants;
import com.fma.ratelimit.dal.CommonHelper;
import com.fma.ratelimit.dal.RateLimitData;
import com.fma.ratelimit.exceptions.ParsingFailedException;
import com.fma.ratelimit.request.pojos.ApiLimit;
import com.fma.ratelimit.request.pojos.RegisterRateLimitBean;
import com.fma.ratelimit.request.pojos.ServiceLimit;
import com.fma.ratelimit.request.pojos.ServiceLimits;
import com.fma.ratelimit.response.Response;
import com.google.gson.Gson;

import lombok.extern.apachecommons.CommonsLog;

@Component
public class RegisterService {

	@Autowired
	CommonHelper commons;

	@Autowired
	Response response;

	public ResponseEntity<String> registerAPIs(String str)
	{	
		if(str!=null && str.length()>0)
		{
			Gson gson = new Gson();
			ServiceLimits serviceLimits = gson.fromJson(str, ServiceLimits.class);

			if (serviceLimits != null && serviceLimits.getServiceLimits() != null && serviceLimits.getServiceLimits().size() > 0) {
				for (ServiceLimit serviceLimit : serviceLimits.getServiceLimits())
					registerAPI(serviceLimit);
				return response.createdResponse;
			}
		}
		return response.ErrorResponse;
	}

	public void registerAPI(ServiceLimit serviceLimit)
	{
		int interval=1;
		if(serviceLimit.getGlobalLimits().getGET()!=null) {
			RegisterRateLimitBean rrlb = CommonHelper.getRateLimitBean(serviceLimit.getService(),null,Constants.GET);
			String hash = CommonHelper.getServiceHash(rrlb);

			int limit = serviceLimit.getGlobalLimits().getGET().getLimit();
			if(serviceLimit.getGlobalLimits().getGET().getGranularity().equals(Constants.Minute))
			{
				limit = limit*60;
				interval=60;
			}
			addServices(hash,limit,rrlb,interval);
		}
		if(serviceLimit.getGlobalLimits().getPOST()!=null) {
			RegisterRateLimitBean rrlb = CommonHelper.getRateLimitBean(serviceLimit.getService(),null,Constants.POST);
			String hash = CommonHelper.getServiceHash(rrlb);
			int limit = serviceLimit.getGlobalLimits().getPOST().getLimit();
			if(serviceLimit.getGlobalLimits().getPOST().getGranularity().equals(Constants.Minute))
			{
				limit = limit*60;
				interval=60;
			}
			addServices(hash,limit,rrlb,interval);
		}
		List<ApiLimit> apilitmitList = serviceLimit.getApiLimits();
		if(apilitmitList!=null && apilitmitList.size()!=0)
		{
			Iterator<ApiLimit> it = apilitmitList.iterator();
			while (it.hasNext())
			{
				ApiLimit apiLimit = it.next();
				interval=1;
				if(apiLimit.getMethods().getGet()!=null){
					RegisterRateLimitBean rrlb = CommonHelper.getRateLimitBean(serviceLimit.getService(),apiLimit.getApi(),Constants.GET);
					String hash = CommonHelper.getServiceHash(rrlb);
					int limit = serviceLimit.getGlobalLimits().getGET().getLimit();

					if(serviceLimit.getGlobalLimits().getGET().getGranularity().equals(Constants.Minute))
					{
						limit = limit*60;
						interval=60;
					}
					addServices(hash,limit,rrlb,interval);
				}
				if(apiLimit.getMethods().getPost()!=null){
					RegisterRateLimitBean rrlb = CommonHelper.getRateLimitBean(serviceLimit.getService(),apiLimit.getApi(),Constants.POST);
					String hash = CommonHelper.getServiceHash(rrlb);
					int limit = serviceLimit.getGlobalLimits().getPOST().getLimit();
					if(serviceLimit.getGlobalLimits().getPOST().getGranularity().equals(Constants.Minute))
					{
						limit = limit*60;
						interval=60;
					}
					addServices(hash,limit,rrlb,interval);
				}
			}

		}

	}

	public void addServices(String hash, int limit, RegisterRateLimitBean rrlb, int interval )
	{
		RateLimitData.hashRRLBMapLimits.put(hash,limit);
		RateLimitData.hashRRLBMapLimitsInterval.put(hash,interval);
		RateLimitData.hashRRLBMap.put(hash,rrlb);
	}
}
