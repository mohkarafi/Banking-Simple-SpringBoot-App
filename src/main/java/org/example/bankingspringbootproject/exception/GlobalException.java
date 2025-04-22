package org.example.bankingspringbootproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {
@ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorDetails> HandelErrorException(AccountException e , WebRequest request) {
     ErrorDetails errorDetails = new ErrorDetails(
             LocalDateTime.now() ,
             e.getMessage(),
             request.getDescription(false),
             "Account_not_found"
     );
     return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }



@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> HandleGenericException(Exception e , WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),e.getMessage() , request.getDescription(false),"Account_not_found"
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
