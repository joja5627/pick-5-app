package io.pick5.wed.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import io.pick5.web.service.GameLinesServiceImpl;
import io.pick5.web.tags.UnitTest;
import reactor.core.publisher.Mono;

@UnitTest
public class MockWebServerTests {
	private static final String resourceUrl = "http://localhost:1080";

	private static final String okPath = "/ok-path";
	private static final String okBovadaPath = "/ok-bovada";
	private static final String bovadaUrl = resourceUrl + okBovadaPath;

	private final File bovadaResFile = new File(
			getClass().getClassLoader().getResource("file/bovada_response.json").getPath());

	private static final String OK_RESPONSE_BODY = "OK";

	private static ClientAndServer mockServer;

	@BeforeAll
	public static void setupMockServer() throws IOException {

		mockServer = startClientAndServer(1080);

		mockServer.when(HttpRequest.request().withPath(okPath))
				.respond(HttpResponse.response().withStatusCode(200).withBody(OK_RESPONSE_BODY));
	}

	private void createExpectationForBovadaResponse() throws IOException {
		mockServer.when(HttpRequest.request().withPath(okBovadaPath)).respond(HttpResponse.response()
				.withStatusCode(200).withBody(FileUtils.readFileToString(bovadaResFile, "UTF-8")));
	}

	@AfterAll
	public static void after() {
		mockServer.stop();
	}

	@Test
	public void mockServerNotNull() {
		assertThat(mockServer).isNotNull();
	}

	@Test
	public void canResponseWithString() throws IOException {
		createExpectationForBovadaResponse();
		assertThat(mockServer.retrieveExistingExpectations(HttpRequest.request().withPath(okBovadaPath))).isNotNull();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(bovadaUrl, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

}
