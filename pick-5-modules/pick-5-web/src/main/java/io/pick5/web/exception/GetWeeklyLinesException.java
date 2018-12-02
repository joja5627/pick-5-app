package io.pick5.web.exception;

public class GetWeeklyLinesException extends Exception {
    public GetWeeklyLinesException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public GetWeeklyLinesException(final String message) {
        super(message);
    }
}
