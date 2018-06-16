package com.canaslaner.scheduler.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class Activity extends BaseModel
{
	@NotNull
	@Size(max = 255)
	@Column(unique = true)
	private String name;

	@NotNull
	private Integer duration;

	@Size(max = 255)
	private String summary;

	@NotNull
	private Boolean isNetworking = Boolean.FALSE;

	@OneToOne
	@NotNull
	@JoinColumn(name = "event_id")
	private Event event;
}
