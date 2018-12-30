package io.pick5.token.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({ SignatureException.class, MalformedJwtException.class, JwtException.class })
//    public Mono<JwtResponse> exception(Mono<Exception> e) {
//        JwtResponse response = new JwtResponse();
//        	response.setStatus(JwtResponse.Status.ERROR);
//        	response.setMessage(e.getMessage());
//        	response.setExceptionType(e.getClass().getName());
//        e.transform(exception -> )
//        return response;
//    }
//    
//    Mono<ServerResponse> throwableError(final Throwable error) {
//        logger.error(ERROR_RAISED, error);
//        return Mono.just(error).transform(this::getResponse);
//    }
//
//    <T extends Throwable> Mono<ServerResponse> getResponse(final Mono<T> monoError) {
//        return monoError.transform(ThrowableTranslator::translate)
//                .flatMap(translation -> ServerResponse
//                        .status(translation.getHttpStatus())
//                        .body(Mono.just(new ErrorResponse(translation.getMessage())), ErrorResponse.class));
//    }
//    
//    
    
}