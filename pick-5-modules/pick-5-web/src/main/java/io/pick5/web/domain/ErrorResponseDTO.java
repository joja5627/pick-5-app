package io.pick5.web.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponseDTO {

    private final String error;

    @JsonCreator
    public ErrorResponseDTO(@JsonProperty("error") final String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
