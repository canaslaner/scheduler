package com.canaslaner.scheduler.controller.api;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canaslaner.scheduler.domain.Activity;
import com.canaslaner.scheduler.domain.dto.ActivityDto;
import com.canaslaner.scheduler.service.ActivityService;
import com.canaslaner.scheduler.service.converter.Converter;

/**
 * @author caslaner
 * @since 12.6.2018
 */
@RestController
@RequestMapping("/api/activities")
public class ActivityController
{
	@Resource(name = "activityConverter")
	private Converter<Activity, ActivityDto> converter;

	@Resource(name = "activityService")
	private ActivityService activityService;

	@GetMapping(params = {"event"})
	public ResponseEntity<List<ActivityDto>> getAllByEvent(
			@RequestParam(value = "event") @NotNull final Long eventId)
	{
		final List<Activity> resultList = activityService.getByEvent(eventId);
		return ResponseEntity.ok(resultList.stream().map(converter::fromEntity).collect(Collectors.toList()));
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/{id}")
	public ResponseEntity<ActivityDto> get(@PathVariable @NotNull final Long id)
	{
		final Activity activity = activityService.get(id);

		if (Objects.isNull(activity))
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		else
		{
			return ResponseEntity.ok(converter.fromEntity(activity));
		}
	}

	@PostMapping
	public ResponseEntity<ActivityDto> create(@NotNull @RequestBody final ActivityDto dto)
	{
		final Activity created = activityService.create(converter.toEntity(dto));
		return ResponseEntity.ok(converter.fromEntity(created));
	}

	@PutMapping
	public ResponseEntity<ActivityDto> update(@NotNull @RequestBody final ActivityDto dto)
	{
		final Activity updated = activityService.update(converter.toEntity(dto));
		return ResponseEntity.ok(converter.fromEntity(updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @NotNull final Long id)
	{
		activityService.delete(id);
		return ResponseEntity.ok().build();
	}
}
