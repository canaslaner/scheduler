package com.canaslaner.scheduler.service;

import java.util.List;

import com.canaslaner.scheduler.domain.Activity;

/**
 * @author caslaner
 * @since 12.6.2018
 */
public interface ActivityService
{
	Activity get(final Long id);

	List<Activity> getByEvent(final Long eventId);

	Activity create(final Activity activity);

	Activity update(final Activity activity);

	void delete(final Long id);
}
