package com.canaslaner.scheduler.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.canaslaner.scheduler.domain.Activity;
import com.canaslaner.scheduler.domain.dto.ActivityDto;
import com.canaslaner.scheduler.domain.dto.ScheduleDto;
import com.canaslaner.scheduler.service.ActivityService;
import com.canaslaner.scheduler.service.ScheduleService;
import com.canaslaner.scheduler.service.builder.ScheduleBuilder;
import com.canaslaner.scheduler.service.converter.Converter;

/**
 * @author caslaner
 * @since 12.6.2018
 */
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService
{
	public static final String SCHEDULE_NAME_PREFIX = "Track";

	@Resource(name = "activityService")
	private ActivityService activityService;

	@Resource(name = "activityConverter")
	private Converter<Activity, ActivityDto> converter;

	@Override
	public List<ScheduleDto> getSchedule(final Long eventId)
	{
		final List<ActivityDto> activityList = activityService.getByEvent(eventId)
				.stream()
				.map(converter::fromEntity)
				.collect(Collectors.toList());

		final String[] names = {SCHEDULE_NAME_PREFIX + " I", SCHEDULE_NAME_PREFIX + " II"};

		return ScheduleBuilder.get(activityList, names).build();
	}


}
