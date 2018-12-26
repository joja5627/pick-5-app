package io.pick5.web.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import io.pick5.web.domain.BovadaResponse;
import io.pick5.web.tags.UnitTest;
import io.pick5.wed.util.TestUtil;
import reactor.core.publisher.Mono;

@UnitTest
public class BovadaResponseRespositoryTest {

	// Save Response
	// Find Response By Id
	// Find Response By Date
	// Evict by Date

	private Gson gson = new Gson();

	@Autowired
	private BovadaResponseRepository bovadaResponseRepository;

	private JsonArray bovadaResponse;

	private TestUtil testUtil = new TestUtil();

	@Before
	public void before() throws IOException {

	}

	@Test
	void shouldInitializeBovadaResponse() throws IOException {

		BovadaResponse res = BovadaResponse.builder().id(UUID.randomUUID().toString())
				.response(testUtil.getBovadaResJsonArray().toString()).timeStamp(LocalDateTime.now(ZoneId.of("UTC")))
				.build();
		assertThat(res).isNotNull();

	}

	@Test
	void shouldSaveBovadaResponse() {

		BovadaResponse res = BovadaResponse.builder().id(UUID.randomUUID().toString())
				.response(testUtil.getBovadaResJsonArray().toString()).timeStamp(LocalDateTime.now(ZoneId.of("UTC")))
				.build();

		Mono<BovadaResponse> saveRes = bovadaResponseRepository.save(res);

		BovadaResponse bovadaResponse = testUtil.valueFromMono(saveRes);

		assertThat(bovadaResponse).isEqualTo(res);

	}

	@Test
	void shouldDeleteBovadaResponseByDate() {

		LocalDateTime date = LocalDateTime.now(ZoneId.of("UTC"));

		BovadaResponse res = BovadaResponse.builder().id(UUID.randomUUID().toString())
				.response(testUtil.getBovadaResJsonArray().toString()).timeStamp(date).build();

		Mono<BovadaResponse> saveRes = bovadaResponseRepository.save(res);

		BovadaResponse bovadaResponse = testUtil.valueFromMono(saveRes);

		assertThat(bovadaResponse).isEqualTo(res);

		Mono<Void> deleteRes = bovadaResponseRepository.deleteByDate(date);

		assertThat(deleteRes).isNotNull();

	}

	@Test
	void shouldDeleteBovadaResponseWithinDateRange() {

		LocalDateTime date = LocalDateTime.now(ZoneId.of("UTC"));

		BovadaResponse res = BovadaResponse.builder().id(UUID.randomUUID().toString())
				.response(testUtil.getBovadaResJsonArray().toString()).timeStamp(date).build();

		Mono<BovadaResponse> saveRes = bovadaResponseRepository.save(res);

		BovadaResponse bovadaResponse = testUtil.valueFromMono(saveRes);

		assertThat(bovadaResponse).isEqualTo(res);

		Mono<Void> deleteRes = bovadaResponseRepository.deleteByDate(date);

		assertThat(deleteRes).isNotNull();

	}

}
