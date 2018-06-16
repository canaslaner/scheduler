package com.canaslaner.scheduler.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.canaslaner.scheduler.domain.Activity;
import com.canaslaner.scheduler.repository.ActivityRepository;
import com.canaslaner.scheduler.service.ActivityService;

/**
 * @author caslaner
 * @since 12.6.2018
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService
{
	final private String[] beanCopyIgnoredProperties = {"createdTs"};

	@Resource(name = "activityRepository")
	private ActivityRepository activityRepository;

	@Override
	public Activity get(final Long id)
	{
		return activityRepository.getOne(id);
	}

	@Override
	public List<Activity> getByEvent(final Long eventId)
	{
		return activityRepository.findByEvent_Id(eventId);
	}

	@Override
	public Activity create(final Activity activity)
	{
		return activityRepository.save(activity);
	}

	@Override
	public Activity update(final Activity activity)
	{
		return activityRepository
				.findById(activity.getId())
				.map(persisted -> {
					BeanUtils.copyProperties(activity, persisted, beanCopyIgnoredProperties);
					return activityRepository.save(persisted);
				})
				.orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public void delete(final Long id)
	{
		activityRepository.deleteById(id);
	}
}
