package com.jmb.batchapp.parameter;

import com.jmb.batchapp.exception.model.BatchAppException;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum is a placeholder for constants that refer to parameters names as passed to the Jobs during
 * job submission.
 *
 * @author JuanMBruno
 */
public enum ParameterName {

    JOB_NAME("jobName"),
    READ_FORMAT("readFormat");

    //Others to be added...

    private String paramName;

    ParameterName(String paramName) {
        this.paramName = paramName;
    }

    static Optional<ParameterName> paramExists(String argName) {
        return Arrays.stream(values())
                .filter(parameterName -> parameterName.paramName.equals(argName))
                .findFirst();
    }
}
