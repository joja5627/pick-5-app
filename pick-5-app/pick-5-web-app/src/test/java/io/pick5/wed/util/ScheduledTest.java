package io.pick5.wed.util;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.support.CronSequenceGenerator;

import io.pick5.web.tags.UnitTest;

@UnitTest
public class ScheduledTest {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTest.class);

	private static final String DATE_CURRENT_2018_01_01 = "2018-01-01";
	private static final String SCHEDULER_TWO_MIN_PERIOD = "2 0/2 * * * *";
	private static final String SCHEDULER_QUARTER_SEASON_PERIOD = "0 0 20 1-7 1,4,7,10 FRI";

	@Test
	public void cronSchedulerGenerator_0() {
		cronSchedulerGenerator(SCHEDULER_QUARTER_SEASON_PERIOD, 100);
	}

	@Test
	public void cronSchedulerGenerator_1() {
		cronSchedulerGenerator(SCHEDULER_TWO_MIN_PERIOD, 200);
	}

	public void cronSchedulerGenerator(String paramScheduler, int index) {
		CronSequenceGenerator cronGen = new CronSequenceGenerator(paramScheduler);
		java.util.Date date = java.sql.Date.valueOf(DATE_CURRENT_2018_01_01);

		for (int i = 0; i < index; i++) {
			date = cronGen.next(date);
			logger.info(new java.text.SimpleDateFormat("EEE, MMM d, yyyy 'at' hh:mm:ss a").format(date));
		}

	}

}
