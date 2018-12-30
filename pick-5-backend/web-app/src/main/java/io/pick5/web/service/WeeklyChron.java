package io.pick5.web.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeeklyChron {

	private static final Logger logger = LoggerFactory.getLogger(WeeklyChron.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Scheduled(cron = "0 0 * * MON")
	public void scheduleTaskWithCronExpression() {
		logger.info("Cron Task Monday :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
	}
}