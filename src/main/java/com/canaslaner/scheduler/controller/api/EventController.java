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
import org.springframework.web.bind.annotation.RestController;

import com.canaslaner.scheduler.domain.Event;
import com.canaslaner.scheduler.domain.dto.EventDto;
import com.canaslaner.scheduler.service.EventService;
import com.canaslaner.scheduler.service.converter.Converter;

/**
 * @author caslaner
 * @since 12.6.2018
 */
@RestController
@RequestMapping("/api/events")
public class EventController
{
	@Resource(name = "eventConverter")
	private Converter<Event, EventDto> converter;

	@Resource(name = "eventService")
	private EventService eventService;

	@GetMapping
	public ResponseEntity<List<EventDto>> getAll()
	{
		final List<Event> resultList = eventService.getAll();

		return ResponseEntity.ok(resultList.stream().map(converter::fromEntity).collect(Collectors.toList()));
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/{id}")
	public ResponseEntity<EventDto> get(@PathVariable @NotNull final Long id)
	{
		final Event event = eventService.get(id);

		if (Objects.isNull(event))
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		else
		{
			return ResponseEntity.ok(converter.fromEntity(event));
		}
	}

	@PostMapping
	public ResponseEntity<EventDto> create(@NotNull @RequestBody final EventDto dto)
	{
		final Event created = eventService.create(converter.toEntity(dto));
		return ResponseEntity.ok(converter.fromEntity(created));
	}

	@PutMapping
	public ResponseEntity<EventDto> update(@NotNull @RequestBody final EventDto dto)
	{
		final Event updated = eventService.update(converter.toEntity(dto));
		return ResponseEntity.ok(converter.fromEntity(updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @NotNull final Long id)
	{
		eventService.delete(id);
		return ResponseEntity.ok().build();
	}
}
