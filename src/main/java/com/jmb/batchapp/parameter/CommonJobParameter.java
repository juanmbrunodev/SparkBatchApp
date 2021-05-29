package com.jmb.batchapp.parameter;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum is a placeholder for constants that refer to parameters names as passed to the Jobs during
 * job submission.
 *
 * @author JuanMBruno
 */
public enum CommonJobParameter implements Parameter<CommonJobParameter>{

    JOB_NAME("jobName"),
    CLIENT_ID("clientId");

    //Others to be added...

    private String paramName;

    CommonJobParameter(String paramName) {
        this.paramName = paramName;
    }

    public Optional<CommonJobParameter> paramExists(String argName) {
        return Arrays.stream(values())
                .filter(commonJobParameter -> commonJobParameter.paramName.equals(argName))
                .findFirst();
    }

    @Override
    public String getParamName() {
        return paramName;
    }
}
