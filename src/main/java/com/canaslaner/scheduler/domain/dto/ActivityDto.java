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
public class ActivityDto
{
	private Long id;

	private String name;

	private Integer duration;

	private String summary;

	private Long eventId;

	private Boolean isNetworking;

	private Instant createdTs;
}
