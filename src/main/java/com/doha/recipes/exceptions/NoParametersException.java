package com.doha.recipes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NoParametersException extends RuntimeException {

    public NoParametersException() {
        super("Please provide one parameter: category or name");
    }

}
