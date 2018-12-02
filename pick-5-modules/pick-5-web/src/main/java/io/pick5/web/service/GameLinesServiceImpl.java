package io.pick5.web.service;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import io.pick5.web.exception.GetWeeklyLinesException;
import reactor.core.publisher.Mono;

public class GameLinesServiceImpl implements GameLinesService   {
	 	
		WebClient webClient;
		
	    public GameLinesServiceImpl(){
	        this.webClient = WebClient.create();
	    }
	    @Override
		public Mono<String> getBookmakerLines(final Mono<String> urlMono)  {
	        return urlMono
	        		.flatMap(url -> webClient
	                .get()
	                .uri(url)
	                .accept(MediaType.APPLICATION_JSON)
	                .exchange()
	                .flatMap(clientResponse -> clientResponse.bodyToMono(String.class)));
	    }
	    @Override
		public Mono<String> getBookmakerLines(String endpoint) {
			// TODO Auto-generated method stub
			return null;
		}
//        return location
//                .transform(this::buildUrl)
//                .transform(this::get)
//                .onErrorResume(throwable -> Mono.error(new GetSunriseSunsetException(ERROR_GETTING_DATA, throwable)))
//                .transform(this::createResult);
//	    @Override
//	    public Mono<String> getBookmakerLines(final Mono<String> endpoint) {
//		   
//	        return webClient
//	                	.get()
//	                	.uri(this::get)
//	                	.accept(MediaType.ALL)
//	                	.exchange()
//	                	.flatMap(clientResponse -> clientResponse.bodyToMono(String.class));
//	    }
//	    @Override
//	    public Mono<String> getBookmakerLines(final String endpoint) {
//		   
//	        return webClient
//	                	.get()
//	                	.uri(endpoint)
//	                	.accept(MediaType.ALL)
//	                	.exchange()
//	                	.flatMap(clientResponse -> clientResponse.bodyToMono(String.class));
//	    }
//	    Mono<String> createResult(final Mono<String> bookmakerLines) {
//	    	
//	        return bookmakerLines.flatMap(geoTimesResponse -> {
//	            if ((geoTimesResponse.getStatus() != null) && (geoTimesResponse.getStatus().equals(STATUS_OK))) {
//	                return Mono.just(new SunriseSunset(geoTimesResponse.getResults().getSunrise(),
//	                        geoTimesResponse.getResults().getSunset()));
//	            } else {
//	                return Mono.error(new GetSunriseSunsetException(SUNRISE_RESULT_NOT_OK));
//	            }
//	        });
//	    }
		
	  
}
