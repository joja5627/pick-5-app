package io.pick5.wed.util;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import io.pick5.web.tags.UnitTest;

@UnitTest
public class LocalDateTimeExample {

	@Test
	public void createLocalDateTime() {
		// Current date time
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(dateTime);
		// Current date time from specified time-zone
		LocalDateTime dateTime2 = LocalDateTime.now(ZoneId.of("UTC"));
		System.out.println(dateTime2);
		// Current date time from specified clock
		LocalDateTime dateTime3 = LocalDateTime.now(Clock.systemUTC());
		System.out.println(dateTime3);
		// Specific date time
		LocalDateTime dateTime4 = LocalDateTime.of(2017, Month.JULY, 12, 10, 35, 55);
		System.out.println(dateTime4);

		Instant instant = Instant.now();
		DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		System.out.println(formatter.format(instant.atZone(ZoneId.systemDefault())));
		System.out.println(formatter.format(instant.atZone(ZoneId.of("America/Los_Angeles"))));

	}

	@Test
	public void dateTimeEpochs() {
		System.out.println("Epochs");
		System.out.println(Instant.now().toEpochMilli());

	}

	@Test
	public void timeComparisons() {
		LocalDate from = LocalDate.parse("2015-05-01");
		LocalDate to = LocalDate.parse("2015-05-07");

		ChronoUnit.DAYS.between(from, to);
		ChronoUnit.WEEKS.between(from, to);

//		System.out.println(days);
//		System.out.println(weeks);

	}
}