package com.canaslaner.scheduler.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author caslaner
 * @since 12.6.2018
 */
@Controller
public class ApplicationController
{
	@SuppressWarnings("SameReturnValue")
	@GetMapping("/")
	public String getIndexPage()
	{
		return "index";
	}
}
