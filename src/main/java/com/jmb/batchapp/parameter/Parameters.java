package com.jmb.batchapp.parameter;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * This class is initialized in any {@link com.jmb.batchapp.job.Job} implementation, after said class
 * is created and registered as a Spring Bean.
 */
@Component
public class Parameters {

    private static final String ARG_SEPARATOR = "=";
    private static final int KEY = 0;
    private static final int VALUE = 1;

    private Map<Parameter, String> jobParams = new HashMap<>();

    public void loadAllParams(String[] appArgs, Parameter[] parameters) {
        Map<String, String> keyVals = new HashMap<>();
        Arrays.stream(appArgs).forEach(arg -> {
            String[] keyVal = arg.split(ARG_SEPARATOR);
            keyVals.put(keyVal[KEY], keyVal[VALUE]);
        });
        Arrays.stream(parameters).forEach(parameter -> {
            String value = keyVals.get(parameter.getParamName());
            if(Objects.nonNull(value)) {
                jobParams.put(parameter, value);
            }
        });
    }

    public String getParamValue(Parameter paramName) {
        return jobParams.get(paramName);
    }
}
