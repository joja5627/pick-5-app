package io.pick5.test.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.reactive.function.server.EntityResponse;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class HandlersHelper {
    @SuppressWarnings("unchecked")
    public static <T> T extractEntity(final ServerResponse response, final Class<T> type) {

        EntityResponse<Mono<T>> entityResponse = (EntityResponse<Mono<T>>) response;

        return type.cast(entityResponse.entity().block());
    }
    static <T> List<T> castList(Class<T> clazz, List<?> items) {
        return items.stream()
        		.filter(clazz::isInstance)
        			.map(clazz::cast)
        				.collect(Collectors.toList());
    }
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> extractEntities(final ServerResponse response, final Class<T> type) {

        EntityResponse<Flux<T>> entityResponse = (EntityResponse<Flux<T>>) response;
        
        List<T> listOfType = entityResponse.entity().collectList().block();
        
        return listOfType
        		.stream()
        			.map(el -> type.cast(el))
        				.collect(Collectors.toList());
 
    }
}
