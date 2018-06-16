package com.canaslaner.scheduler.service.converter.impl;

import org.springframework.stereotype.Service;

import com.canaslaner.scheduler.domain.Event;
import com.canaslaner.scheduler.domain.dto.EventDto;
import com.canaslaner.scheduler.service.converter.Converter;

/**
 * @author caslaner
 * @since 12.6.2018
 */
@Service("eventConverter")
public class EventConverter implements Converter<Event, EventDto>
{

	@Override
	public EventDto fromEntity(final Event entity)
	{
		final EventDto dto = new EventDto();

		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDate(entity.getDate());
		dto.setCreatedTs(entity.getCreatedTs());
		dto.setSummary(entity.getSummary());

		return dto;
	}

	@Override
	public Event toEntity(final EventDto dto)
	{
		final Event entity = new Event();

		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setSummary(dto.getSummary());

		return entity;
	}
}
