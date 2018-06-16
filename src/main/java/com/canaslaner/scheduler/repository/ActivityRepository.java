package com.canaslaner.scheduler.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.canaslaner.scheduler.domain.Activity;

/**
 * @author caslaner
 * @since 12.6.2018
 */
public interface ActivityRepository extends JpaRepository<Activity, Long>
{
	Optional<Activity> findById(final Long id);

	List<Activity> findByEvent_Id(final Long id);
}
