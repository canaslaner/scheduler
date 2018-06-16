package com.canaslaner.scheduler.service.converter.impl;

import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.canaslaner.scheduler.domain.Activity;
import com.canaslaner.scheduler.domain.Event;
import com.canaslaner.scheduler.domain.dto.ActivityDto;
import com.canaslaner.scheduler.service.EventService;
import com.canaslaner.scheduler.service.converter.Converter;

/**
 * @author caslaner
 * @since 12.6.2018
 */
@Service("activityConverter")
public class ActivityConverter implements Converter<Activity, ActivityDto>
{

	@Resource(name = "eventService")
	private EventService eventService;

	@Override
	public ActivityDto fromEntity(final Activity entity)
	{
		final ActivityDto dto = new ActivityDto();

		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDuration(entity.getDuration());
		dto.setCreatedTs(entity.getCreatedTs());
		dto.setSummary(entity.getSummary());
		dto.setIsNetworking(entity.getIsNetworking());

		if (Objects.nonNull(entity.getEvent()))
		{
			dto.setEventId(entity.getEvent().getId());
		}

		return dto;
	}

	@Override
	public Activity toEntity(final ActivityDto dto)
	{
		final Activity entity = new Activity();

		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDuration(dto.getDuration());
		entity.setSummary(dto.getSummary());
		entity.setIsNetworking(dto.getIsNetworking());

		final Event event = eventService.get(dto.getEventId());

		if (Objects.nonNull(event))
		{
			entity.setEvent(event);
		}

		return entity;
	}
}
