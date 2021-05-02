package com.jmb.batchapp.exception;

import com.jmb.batchapp.exception.model.BatchAppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    public void handleException(Exception exceptionThrow) {
        LOGGER.error("Non Business Exception Occurred : " + exceptionThrow.getMessage());
    }

    //Handles Custom/Expected business Exceptions
    public void handleBusinessException(BatchAppException batchAppException) {
        LOGGER.error("Business Exception Occurred : " + batchAppException.getMessage());
    }
}
