package com.canaslaner.scheduler.service;

import java.util.List;

import com.canaslaner.scheduler.domain.Event;

/**
 * @author caslaner
 * @since 12.6.2018
 */
public interface EventService
{
	List<Event> getAll();

	Event get(final Long id);

	Event create(final Event event);

	Event update(final Event event);

	void delete(final Long id);
}
