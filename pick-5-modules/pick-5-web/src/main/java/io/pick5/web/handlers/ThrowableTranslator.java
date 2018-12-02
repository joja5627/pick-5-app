package io.pick5.web.handlers;

import org.springframework.http.HttpStatus;

import io.pick5.web.exception.WeeklyLinesNotFoundException;
import io.pick5.web.exception.GetWeeklyLinesException;
import io.pick5.web.exception.InvalidParametersException;
import io.pick5.web.exception.PathNotFoundException;
import reactor.core.publisher.Mono;

class ThrowableTranslator {

    private final HttpStatus httpStatus;
    private final String message;

    private ThrowableTranslator(final Throwable throwable) {
        this.httpStatus = getStatus(throwable);
        this.message = throwable.getMessage();
    }

    private HttpStatus getStatus(final Throwable error) {
        if (error instanceof InvalidParametersException) {
            return HttpStatus.BAD_REQUEST;
        } else if (error instanceof PathNotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if (error instanceof WeeklyLinesNotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if (error instanceof GetWeeklyLinesException) {
            if (error.getCause() instanceof InvalidParametersException)
                return HttpStatus.BAD_REQUEST;
            else
                return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    HttpStatus getHttpStatus() {
        return httpStatus;
    }

    String getMessage() {
        return message;
    }

    static <T extends Throwable> Mono<ThrowableTranslator> translate(final Mono<T> throwable) {
        return throwable.flatMap(error -> Mono.just(new ThrowableTranslator(error)));
    }
}
