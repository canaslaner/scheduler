package com.canaslaner.scheduler.service;

import java.util.List;

import com.canaslaner.scheduler.domain.dto.ScheduleDto;

/**
 * @author caslaner
 * @since 12.6.2018
 */
public interface ScheduleService
{
	List<ScheduleDto> getSchedule(final Long eventId);
}
