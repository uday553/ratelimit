package com.fma.ratelimit.dal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.fma.ratelimit.request.pojos.RegisterRateLimitBean;

@Component
public class RateLimitData {
    public static Map<String,RegisterRateLimitBean> hashRRLBMap = new HashMap<String, RegisterRateLimitBean>();
    public static Map<String,Integer> hashRRLBMapLimits = new HashMap<String, Integer>();
    public static Map<String,Integer> hashRRLBMapLimitsInterval = new HashMap<String, Integer>();
    public static TreeMap<Long, ConcurrentHashMap<String, Integer>> rateLimitcounters = new TreeMap<Long, ConcurrentHashMap<String, Integer>>(Collections.reverseOrder());


}
