package com.canaslaner.scheduler.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.canaslaner.scheduler.SchedulerApplication;

/**
 * @author caslaner
 * @since 13.6.2018
 */
public class ServletInitializer extends SpringBootServletInitializer
{
	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application)
	{
		return application.sources(SchedulerApplication.class);
	}

}

