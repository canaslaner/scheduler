package com.canaslaner.scheduler.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.canaslaner.scheduler.domain.Event;

/**
 * @author caslaner
 * @since 12.6.2018
 */
public interface EventRepository extends JpaRepository<Event, Long>
{
	Optional<Event> findById(Long id);
}
