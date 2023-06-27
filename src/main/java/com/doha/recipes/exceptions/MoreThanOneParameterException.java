package com.doha.recipes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MoreThanOneParameterException extends RuntimeException{
      public MoreThanOneParameterException() {
        super("Please provide only one parameter: name or category");
    }

}
