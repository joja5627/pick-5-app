package io.pick5.web.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpClassCallback.callback;

import static org.mockserver.model.HttpRequest.request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.mockserver.MockServer;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.FileInputStream;

import org.xml.sax.SAXException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.pick5.web.test.*;
import io.pick5.wed.util.DefaultCallback;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import io.pick5.web.domain.BovadaResponse;
import io.pick5.web.tags.IntegrationTest;
import io.pick5.web.tags.UnitTest;

@IntegrationTest
@DisplayName("GameLinesServiceTest Unit Tests")
public class GameLinesServiceTest {

	private static final String mockResourceURL = "http://localhost:1080";
	private static final String okPath = "/ok-path";
	private static final String okBovadaPath = "/ok-bovada";
	private static final String okBovadaUrl = mockResourceURL + okBovadaPath;
	private final File bovadaResFile = new File(
			getClass().getClassLoader().getResource("file/bovada_response.json").getFile());
	private static final String OK_RESPONSE_BODY = "OK";

	private static ClientAndServer mockServer;

	private <T> T valueFromMono(Mono<T> mono) {
		return mono.block();
	}

	@Autowired
	private GameLinesServiceImpl gameLineService;

	@BeforeAll
	public static void setupMockServer() throws IOException {
		mockServer = startClientAndServer(1080);
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
	public void shouldGetGenericJsonArray() throws IOException {

		String expectedJsonString = FileUtils.readFileToString(bovadaResFile, "UTF-8");

		createExpectationForBovadaResponse();

//		Mono<JsonArray> res = gameLineService.getBookmakerLines(okBovadaUrl);

//		assertThat(res).isNotNull();
//
//		String jsonResponseString = valueFromMono(res).toString();
//
//		assertThat(expectedJsonString).isEqualTo(jsonResponseString);

	}

}
