package org.example.bankingspringbootproject.exception;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ErrorDetails(LocalDateTime TimeTamp ,
                           String message ,
                           String ErrorCode ,
                           String Details){}