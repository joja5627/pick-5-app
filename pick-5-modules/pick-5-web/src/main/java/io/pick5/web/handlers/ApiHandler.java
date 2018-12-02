package io.pick5.web.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pick5.web.service.GameLinesService;
import reactor.core.publisher.Mono;

public class ApiHandler {

  private static final String ADDRESS = "address";
  private static final String EMPTY_STRING = "";

  private final ErrorHandler errorHandler;
  private final GameLinesService gameLinesService;

//  private final GeoLocationService geoLocationService;
//  private final SunriseSunsetService sunriseSunsetService;
//final GeoLocationService geoLocationService, final SunriseSunsetService sunriseSunsetService,
  public ApiHandler(final ErrorHandler errorHandler, GameLinesService gameLinesService) {
    this.errorHandler = errorHandler;
    this.gameLinesService = gameLinesService;
//    this.geoLocationService = geoLocationService;
//    this.sunriseSunsetService = sunriseSunsetService;
  }

//  public Mono<ServerResponse> postLocation(final ServerRequest request) {
//    return request.bodyToMono(LocationRequest.class)
//        .map(LocationRequest::getAddress)
//        .onErrorResume(throwable -> Mono.just(EMPTY_STRING))
//        .transform(this::buildResponse)
//        .onErrorResume(errorHandler::throwableError);
//  }

//  public Mono<ServerResponse> getLocation(final ServerRequest request) {
//    return Mono.just(request.pathVariable(ADDRESS))
//        .transform(this::buildResponse)
//        .onErrorResume(errorHandler::throwableError);
//  }

//  Mono<ServerResponse> buildResponse(final Mono<String> address) {
//    return address
//        .transform(gameLinesService::getBookmakerLines)
//        .zipWhen(this::sunriseSunset, LocationResponse::new)
//        .transform(this::serverResponse);
//  }

//  private Mono<SunriseSunset> sunriseSunset(GeographicCoordinates geographicCoordinates) {
//    return Mono.just(geographicCoordinates).transform(sunriseSunsetService::fromGeographicCoordinates);
//  }

//  Mono<ServerResponse> serverResponse(Mono<LocationResponse> locationResponseMono) {
//    return locationResponseMono.flatMap(locationResponse ->
//        ServerResponse.ok().body(Mono.just(locationResponse), LocationResponse.class));
//  }
}