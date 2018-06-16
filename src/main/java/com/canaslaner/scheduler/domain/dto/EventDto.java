package com.canaslaner.scheduler.domain.dto;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

/**
 * @author caslaner
 * @since 12.6.2018
 */
@Getter
@Setter
public class EventDto
{
	private Long id;

	private String name;

	private String summary;

	private Instant date;

	private Instant createdTs;
}
