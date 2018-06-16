package com.canaslaner.scheduler.service.builder;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.canaslaner.scheduler.domain.dto.ActivityDto;
import com.canaslaner.scheduler.domain.dto.ScheduleDto;
import com.canaslaner.scheduler.domain.enums.ScheduleSection;

/**
 * @author caslaner
 * @since 15.6.2018
 */
public final class ScheduleBuilder
{
	private final Map<ScheduleSection, LocalTime> startTimes = new HashMap<>();

	private final Map<ScheduleSection, LocalTime> finishTimes = new HashMap<>();

	private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

	private final String[] names;

	private final List<ActivityDto> activityList;

	private final List<ActivityDto> networkingActivityList;

	private ScheduleBuilder(final List<ActivityDto> activityList, final String... name)
	{
		names = name;

		networkingActivityList = getNetworkingActivityFromList(activityList);
		activityList.removeAll(networkingActivityList);

		activityList.sort(Comparator.comparing(ActivityDto::getDuration).reversed());
		this.activityList = activityList;
	}

	public static ScheduleBuilder get(final List<ActivityDto> activityList, final String... name)
	{
		return new ScheduleBuilder(activityList, name);
	}

	public List<ScheduleDto> build()
	{
		final List<ScheduleDto> result = new ArrayList<>();

		for (final String name : names)
		{
			resetTimes();

			final ScheduleDto dto = buildSingle(name);
			if (dto.getSchedule().size() > 1)
			{
				handleFreeTime(dto.getSchedule(), ScheduleSection.AM);
				handleFreeTime(dto.getSchedule(), ScheduleSection.PM);
				result.add(dto);
			}
		}

		return result;
	}

	private ScheduleDto buildSingle(final String name)
	{
		final Map<String, ActivityDto> schedule = new TreeMap<>();
		final ScheduleDto dto = createScheduleDto(name, schedule);

		handleLunchActivity(schedule);
		handleNetworkingActivity(schedule);

		final List<ActivityDto> usedActivities = new ArrayList<>();

		LocalTime beforeChangeAmTime;
		LocalTime beforeChangePmTime;

		//this while check is to be sure all the activities has been tried
		//if it was fit or not.
		do
		{
			beforeChangeAmTime = startTimes.get(ScheduleSection.AM);
			beforeChangePmTime = startTimes.get(ScheduleSection.PM);

			for (int j = 0; j < 2; j++)
			{
				for (final ActivityDto anActivityList : activityList)
				{
					final boolean result;

					if (j % 2 == 0)
					{
						result = addActivity(anActivityList, schedule, ScheduleSection.AM);
					}
					else
					{
						result = addActivity(anActivityList, schedule, ScheduleSection.PM);
					}

					if (result)
					{
						usedActivities.add(anActivityList);
					}
				}
				activityList.removeAll(usedActivities);
			}

		}
		while (!startTimes.get(ScheduleSection.AM).equals(beforeChangeAmTime)
				|| !startTimes.get(ScheduleSection.PM).equals(beforeChangePmTime));

		return dto;
	}

	private void handleFreeTime(final Map<String, ActivityDto> schedule, final ScheduleSection scheduleSection)
	{
		final long diff = ChronoUnit.MINUTES.between(startTimes.get(scheduleSection), finishTimes.get(scheduleSection));
		if (diff > 0)
		{
			final ActivityDto dto = new ActivityDto();
			dto.setName("EMPTY AREA");
			dto.setDuration(Integer.valueOf(Math.toIntExact(diff)));
			schedule.put(getAvailableTime(scheduleSection), dto);
		}
	}

	private ScheduleDto createScheduleDto(final String name, final Map<String, ActivityDto> schedule)
	{
		final ScheduleDto dto = new ScheduleDto();
		dto.setName(name);
		dto.setSchedule(schedule);

		return dto;
	}

	private boolean addActivity(final ActivityDto activityDto, final Map<String, ActivityDto> schedule,
			final ScheduleSection scheduleSection)
	{
		final String availableTime = getAvailableTime(scheduleSection);
		if (!calculateNextTime(activityDto, scheduleSection))
		{
			return false;
		}

		schedule.put(availableTime, activityDto);
		return true;
	}

	private boolean calculateNextTime(final ActivityDto activityDto, final ScheduleSection scheduleSection)
	{
		boolean result = false;

		final LocalTime newTime = startTimes.get(scheduleSection).plusMinutes(activityDto.getDuration().longValue());
		if (!newTime.isAfter(finishTimes.get(scheduleSection)))
		{
			startTimes.put(scheduleSection, newTime);
			result = true;
		}

		return result;
	}

	private String getAvailableTime(final ScheduleSection scheduleSection)
	{
		final LocalTime localTime = startTimes.get(scheduleSection);

		return Objects.nonNull(localTime) ? localTime.format(dtf) : "00:00";
	}

	private List<ActivityDto> getNetworkingActivityFromList(final List<ActivityDto> activityList)
	{
		return activityList
				.stream()
				.filter(activityDto -> activityDto.getIsNetworking().booleanValue())
				.collect(Collectors.toList());
	}

	private void handleNetworkingActivity(final Map<String, ActivityDto> schedule)
	{
		if (!networkingActivityList.isEmpty())
		{
			final ActivityDto activityDto = networkingActivityList.get(0);
			if (addActivity(activityDto, schedule, ScheduleSection.NETWORKING))
			{
				networkingActivityList.remove(activityDto);
				finishTimes.put(ScheduleSection.PM, finishTimes.get(ScheduleSection.NETWORKING).minusMinutes(60));
			}
		}
	}

	private void handleLunchActivity(final Map<String, ActivityDto> schedule)
	{
		final ActivityDto activityDto = new ActivityDto();
		activityDto.setName("Lunch");
		activityDto.setDuration(Integer.valueOf(60));

		addActivity(activityDto, schedule, ScheduleSection.LUNCH);
	}

	private void resetTimes()
	{
		startTimes.put(ScheduleSection.AM, LocalTime.parse("09:00"));
		finishTimes.put(ScheduleSection.AM, LocalTime.parse("12:00"));
		startTimes.put(ScheduleSection.LUNCH, LocalTime.parse("12:00"));
		finishTimes.put(ScheduleSection.LUNCH, LocalTime.parse("13:00"));
		startTimes.put(ScheduleSection.PM, LocalTime.parse("13:00"));
		finishTimes.put(ScheduleSection.PM, LocalTime.parse("17:00"));
		startTimes.put(ScheduleSection.NETWORKING, LocalTime.parse("16:00"));
		finishTimes.put(ScheduleSection.NETWORKING, LocalTime.parse("17:00"));
	}
}
