package com.canaslaner.scheduler.controller.api;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.canaslaner.scheduler.domain.dto.ScheduleDto;
import com.canaslaner.scheduler.service.ScheduleService;

/**
 * @author caslaner
 * @since 12.6.2018
 */
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController
{
	@Resource(name = "scheduleService")
	private ScheduleService scheduleService;

	@GetMapping("/{id}")
	public ResponseEntity<List<ScheduleDto>> get(@PathVariable @NotNull final Long id)
	{
		final List<ScheduleDto> scheduleDtoList = scheduleService.getSchedule(id);

		return ResponseEntity.ok(scheduleDtoList);
	}

}
