package com.HUEdge.NetflixApp.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@WebFilter("/*")
public class TimeMiddleware implements Filter{

	
	 private static final Logger LOGGER = LoggerFactory.getLogger(TimeMiddleware.class);

	    public void init(FilterConfig filterConfig) throws ServletException {
	        // empty
	    }
	    
	  
	    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
	    	
	    	long time = System.currentTimeMillis();
	        try {
	        	
	            chain.doFilter(req, resp);
	        } finally {
	        	time = System.currentTimeMillis() - time;
	            ((HttpServletResponse) resp).addHeader("x-time", String.valueOf(System.currentTimeMillis() - time));
	            LOGGER.info("Time taken to execute query -- {}: {} ms ", ((HttpServletRequest) req).getRequestURI(),  time);
	        }
	    }

	    public void destroy() {
	        // empty
	    }
}
