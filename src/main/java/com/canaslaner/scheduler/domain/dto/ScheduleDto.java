package com.canaslaner.scheduler.domain.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author caslaner
 * @since 15.6.2018
 */
@Getter
@Setter
public class ScheduleDto
{
	private String name;

	private Map<String, ActivityDto> schedule;
}
