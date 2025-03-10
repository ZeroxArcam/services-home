package com.pragma.hogar360.serviceshome.infrastructure.exceptionshandler;

import java.time.LocalDateTime;

/**
 * Record representing the structure of an exception response.
 *
 * @param message   The error message.
 * @param timeStamp The timestamp when the exception occurred.
 */
public record ExceptionResponse(String message, LocalDateTime timeStamp) {
}