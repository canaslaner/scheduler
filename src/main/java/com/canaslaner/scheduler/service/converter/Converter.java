package com.canaslaner.scheduler.service.converter;

/**
 * @author caslaner
 * @since 12.6.2018
 */
public interface Converter<T, R>
{
	R fromEntity(T entity);

	T toEntity(R dto);
}
