package com.canaslaner.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.canaslaner.scheduler")
public class SchedulerApplication
{
	public static void main(final String[] args)
	{
		SpringApplication.run(SchedulerApplication.class, args);
	}
}
