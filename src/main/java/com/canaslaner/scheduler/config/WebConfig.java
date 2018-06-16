package com.canaslaner.scheduler.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author caslaner
 * @since 12.6.2018
 */
@Configuration
public class WebConfig implements WebMvcConfigurer
{
	@Override
	public void configureViewResolvers(final ViewResolverRegistry registry)
	{
		final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/view/");
		resolver.setSuffix(".html");
		registry.viewResolver(resolver);
	}
}
