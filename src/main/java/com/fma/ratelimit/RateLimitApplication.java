package com.fma.ratelimit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class RateLimitApplication {
	private static final Logger log = LoggerFactory.getLogger(RateLimitApplication.class);

	public static void main(String[] args) {
		log.debug("started");		
		SpringApplication.run(RateLimitApplication.class, args);
	}

}
