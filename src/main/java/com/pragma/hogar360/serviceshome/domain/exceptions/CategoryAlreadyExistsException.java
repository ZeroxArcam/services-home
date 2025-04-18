package com.pragma.hogar360.serviceshome.domain.exceptions;

import org.aspectj.bridge.IMessage;

public class CategoryAlreadyExistsException extends BusinessException {
    public CategoryAlreadyExistsException() {
        super("Message");
    }
}
