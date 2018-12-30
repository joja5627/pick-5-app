package io.pick5.exception.utils;

import java.util.stream.Stream;

import io.pick5.exceptions.ValidationException;

public class CheckUtil {

    private static final String[] INVALID_NAMES = {"admin", "管理员"};

    public static void checkName(String value) {
    	
        Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value))
                .findAny().ifPresent(name -> {
            throw new ValidationException("name", value);
        });
    }
    
   
    public static void validateEmail(String value) {
    	
        Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value))
                .findAny().ifPresent(name -> {
            throw new ValidationException("name", value);
        });
    }
}
