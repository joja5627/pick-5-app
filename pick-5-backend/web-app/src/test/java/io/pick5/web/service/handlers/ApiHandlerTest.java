package io.pick5.web.service.handlers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import io.pick5.web.domain.BovadaResponse;
import io.pick5.web.domain.ErrorResponse;
import io.pick5.web.service.GameLinesService;
import io.pick5.web.service.handlers.ApiHandler;
import io.pick5.web.tags.UnitTest;
import io.pick5.web.test.HandlersHelper;
import io.pick5.wed.util.TestUtil;
import reactor.core.publisher.Mono;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.File;

@UnitTest
@DisplayName("ApiHandler Unit Tests")
class ApiHandlerTest {


	@Autowired
	private ApiHandler apiHandler;

	@SpyBean
	private GameLinesService gameLinesService;
//
//	private void verifyBovadaResponse(final BovadaResponse response) {
//		assertThat(response.getBovadaResponse(), is(new JsonArray()));
//	}
//
//	private void verifyServerResponse(final ServerResponse serverResponse) {
//
//		assertThat(serverResponse.statusCode(), is(HttpStatus.OK));
//		verifyBovadaResponse(HandlersHelper.extractEntity(serverResponse, BovadaResponse.class));
//	}

//	@Test
//	void serverResponseTest() {
//		Mono.just(new BovadaResponse(new JsonArray()))
//				.transform(apiHandler::serverResponse)
//					.subscribe(this::verifyServerResponse);
//	}
//	@Test
//	void buildBodavaResponseTest() {
//		Mono.just(new BovadaResponse(new JsonArray()))
//				.transform(apiHandler::serverResponse)
//					.subscribe(this::verifyServerResponse);
//	}
	@Test
	void castClientResToBovadaResponse() {
		Mono<BovadaResponse> res = 
				Mono.just(new JsonArray()).cast(BovadaResponse.class);
		assertNotNull(res);
	}
//
//
//	@Test
//	void getBovadaLinesTest() {
//		ServerRequest serverRequest = mock(ServerRequest.class);
//		final Mono<JsonArray> getBookMakerLinesRes = Mono.just(new JsonArray());
//        doReturn(getBookMakerLinesRes)
//        	.when(gameLinesService)
//        		.getBookmakerLines(any());
//        apiHandler.getBovadaLines(serverRequest).subscribe(this::verifyServerResponse);
//	}

//    @Test
//    void getLocationNotFoundTest() {
//    	
//    	ServerRequest serverRequest = mock(ServerRequest.class);
//    	
//		final Mono<JsonArray> getBookMakerLinesRes = Mono.just(new JsonArray());
//		
//        doReturn(getBookMakerLinesRes)
//        	.when(gameLinesService)
//        		.getBookmakerLines(any());
//        
//        apiHandler.getBovadaLines(serverRequest).subscribe(this::verifyServerResponse);
//
//        doReturn(LOCATION_NOT_FOUND).when(geoLocationService).fromAddress(any());
//        doReturn(SUNRISE_SUNSET).when(sunriseSunsetService).fromGeographicCoordinates(any());
//
//		doReturn(LOCATION_NOT_FOUND).when(geoLocationService).fromAddress(any());
//        ServerResponse serverResponse = apiHandler.getBovadaLines(serverRequest).block();
//
//        assertThat(serverResponse.statusCode(), is(HttpStatus.NOT_FOUND));
//
//        ErrorResponse error = HandlersHelper.extractEntity(serverResponse, ErrorResponse.class);
//
//        assertThat(error.getError(), is(NOT_FOUND));
//
//        reset(geoLocationService);
//        reset(sunriseSunsetService);
//    }
//
//    @Test
//    void getLocationErrorSunriseSunsetTest() {
//        ServerRequest serverRequest = mock(ServerRequest.class);
//        when(serverRequest.pathVariable(ADDRESS_VARIABLE)).thenReturn(GOOGLE_ADDRESS);
//
//        doReturn(GOOGLE_LOCATION).when(geoLocationService).fromAddress(any());
//        doReturn(SUNRISE_SUNSET_ERROR).when(sunriseSunsetService).fromGeographicCoordinates(any());
//
//        ServerResponse serverResponse = apiHandler.getLocation(serverRequest).block();
//
//        assertThat(serverResponse.statusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
//
//        ErrorResponse error = HandlersHelper.extractEntity(serverResponse, ErrorResponse.class);
//
//        assertThat(error.getError(), is(CANT_GET_SUNRISE_SUNSET));
//
//        reset(geoLocationService);
//        reset(sunriseSunsetService);
//    }
//
//    @Test
//    void getLocationBothServiceErrorTest() {
//        ServerRequest serverRequest = mock(ServerRequest.class);
//        when(serverRequest.pathVariable(ADDRESS_VARIABLE)).thenReturn(GOOGLE_ADDRESS);
//
//        doReturn(LOCATION_EXCEPTION).when(geoLocationService).fromAddress(any());
//        doReturn(SUNRISE_SUNSET_ERROR).when(sunriseSunsetService).fromGeographicCoordinates(any());
//
//        ServerResponse serverResponse = apiHandler.getLocation(serverRequest).block();
//
//        assertThat(serverResponse.statusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
//
//        ErrorResponse error = HandlersHelper.extractEntity(serverResponse, ErrorResponse.class);
//
//        assertThat(error.getError(), is(CANT_GET_LOCATION));
//
//        reset(geoLocationService);
//        reset(sunriseSunsetService);
//    }
}
