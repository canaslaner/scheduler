package com.canaslaner.scheduler.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.canaslaner.scheduler.domain.Event;
import com.canaslaner.scheduler.repository.EventRepository;
import com.canaslaner.scheduler.service.EventService;

/**
 * @author caslaner
 * @since 12.6.2018
 */
@Service("eventService")
public class EventServiceImpl implements EventService
{
	final private String[] beanCopyIgnoredProperties = {"createdTs"};

	@Resource(name = "eventRepository")
	private EventRepository eventRepository;

	@Override
	public List<Event> getAll()
	{
		return eventRepository.findAll();
	}

	@Override
	public Event get(final Long id)
	{
		return eventRepository.getOne(id);
	}

	@Override
	public Event create(final Event event)
	{
		return eventRepository.save(event);
	}

	@Override
	public Event update(final Event event)
	{
		return eventRepository
				.findById(event.getId())
				.map(persisted -> {
					BeanUtils.copyProperties(event, persisted, beanCopyIgnoredProperties);
					return eventRepository.save(persisted);
				})
				.orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public void delete(final Long id)
	{
		eventRepository.deleteById(id);
	}
}
