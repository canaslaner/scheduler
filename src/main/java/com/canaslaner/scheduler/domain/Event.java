package com.canaslaner.scheduler.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * @author caslaner
 * @since 12.6.2018
 */
@Entity
@Getter
@Setter
public class Event extends BaseModel
{
	@NotNull
	@Size(max = 255)
	@Column(unique = true)
	private String name;

	@NotNull
	private Instant date;

	@Size(max = 255)
	private String summary;
}
