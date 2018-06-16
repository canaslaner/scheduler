package com.canaslaner.scheduler.domain;

import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * @author caslaner
 * @since 12.6.2018
 */
@Getter
@Setter
@MappedSuperclass
public class BaseModel
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Instant createdTs = Instant.now();
}
