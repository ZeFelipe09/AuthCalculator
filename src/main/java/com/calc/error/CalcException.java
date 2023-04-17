package com.calc.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
@ResponseBody
public class CalcException extends RuntimeException {

    public CalcException(String message){
        super(message);
    }
}
