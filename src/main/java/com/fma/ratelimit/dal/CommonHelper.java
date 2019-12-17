package com.fma.ratelimit.dal;


import com.fma.ratelimit.exceptions.ParsingFailedException;
import com.fma.ratelimit.handlers.ServiceAccessHandler;
import com.fma.ratelimit.request.pojos.RegisterRateLimitBean;
import com.fma.ratelimit.request.pojos.ServiceLimits;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommonHelper {
	private static final Logger log = LoggerFactory.getLogger(CommonHelper.class);
    public ServiceLimits getRateLimits(String URL) throws ParsingFailedException
    {
        try {
            Gson parser = new Gson();
            return parser.fromJson(getURLResponse(URL), ServiceLimits.class);
        }
        catch (Exception exp)
        {
            throw new ParsingFailedException("Parsing falied :: "+exp.getMessage());
        }
    }
    
    public RegisterRateLimitBean  getRateLimitBean(String serviceName,String apiName,String type)
    {
        RegisterRateLimitBean rrlb = new RegisterRateLimitBean();
        rrlb.setService(serviceName);
        rrlb.setApiName(apiName);
        rrlb.setType(type);
        return rrlb;
    }

    public String getObjectToJson(Object obj)
    {
        Gson gson = new Gson();
        System.out.println(gson.toJson(obj));
        return gson.toJson(obj);
    }
    
    public String getServiceHash(Object object)
    {
        return getHashOfString(getObjectToJson(object));
    }
    
    public String getHashOfString (String str)
    {
        if(str!=null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] hashInBytes = md.digest(str.getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                for (byte b : hashInBytes) {
                    sb.append(String.format("%02x", b));
                }
                log.debug("{}",sb.toString());
                return sb.toString();
            } catch (Exception exp) {
                System.err.println(" exp : " + exp.getMessage());
            }
        }
        return null;
    }
    private String getURLResponse(String URL){

        try {
            URL obj = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            //con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer sb = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                return sb.toString();
            }
        }
        catch (Exception exp){
            System.err.println("exception :: "+exp.getMessage());
        }
        return null;

    }

}
