package com.pragma.hogar360.serviceshome.infrastructure.exceptionshandler;
import java.time.LocalDateTime;

public record ExceptionResponse(String message, LocalDateTime timeStamp) {
}