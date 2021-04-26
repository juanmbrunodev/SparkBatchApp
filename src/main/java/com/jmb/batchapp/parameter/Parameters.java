package com.jmb.batchapp.parameter;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This class is initialized in any {@link com.jmb.batchapp.job.Job} implementation, after said class
 * is created and registered as a Spring Bean.
 */
@Component
public class Parameters {

    private static final String ARG_SEPARATOR = "=";
    private static final int KEY = 0;
    private static final int VALUE = 1;

    private Map<ParameterName, String> jobParams = new HashMap<>();

    public void setJobParams(String[] args) {
        Arrays.stream(args).forEach(arg -> {
            String[] keyVal = arg.split(ARG_SEPARATOR);
            Optional<ParameterName> searchedParam = ParameterName.paramExists(keyVal[KEY]);
            if(searchedParam.isPresent()){
                jobParams.put(searchedParam.get(), keyVal[VALUE]);
            }
        });
    }

    public String getParamValue(ParameterName paramName) {
        return jobParams.get(paramName);
    }
}
