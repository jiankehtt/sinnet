package com.sinnet.listeners;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;


public class ContextLoaderToInitSystemListener extends ContextLoaderListener{

	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext context) {
		WebApplicationContext ret =  super.initWebApplicationContext(context);
		return ret;
	}

}
