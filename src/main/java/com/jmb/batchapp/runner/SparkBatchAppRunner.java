package com.jmb.batchapp.runner;

import com.jmb.batchapp.exception.ExceptionHandler;
import com.jmb.batchapp.job.Job;
import com.jmb.batchapp.parameter.CommonJobParameter;
import com.jmb.batchapp.parameter.JobsParameters;
import com.jmb.batchapp.parameter.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Optional;

@ConditionalOnProperty(
        prefix = "application.runner",
        value = "enabled",
        havingValue = "true",
        matchIfMissing = true)
@Component
public class SparkBatchAppRunner implements CommandLineRunner {

    private static Logger LOGGER = LoggerFactory.getLogger(SparkBatchAppRunner.class);

    @Autowired
    private ExceptionHandler exceptionHandler;

    @Autowired
    private Parameters parameters;

    @Autowired
    private ApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        try {
            LOGGER.info("Parsing common job arguments");
            loadCommonParams(args);
            LOGGER.info("Instantiating job");
            Job job = (Job) context.getBean(parameters.getParamValue(CommonJobParameter.JOB_NAME), CommonJobParameter.JOB_NAME);
            LOGGER.info("Loading Job Specific Arguments");
            loadJobParams(args);
            LOGGER.info("Executing job");
            job.execute();
            LOGGER.info("Batch Application Finished Executing");
        } catch (Exception be) {
            exceptionHandler.handleException(be);
            throw be;
        }
    }

    public void loadCommonParams(String[] args) {
        parameters.loadAllParams(args, CommonJobParameter.values());
    }

    public void loadJobParams(String[] args) {
        String jobName = parameters.getParamValue(CommonJobParameter.JOB_NAME);
        Optional<JobsParameters> paramsFound = JobsParameters.getParamsForJob(jobName);
        if(paramsFound.isPresent()) {
            parameters.loadAllParams(args, paramsFound.get().getParameters());
        }
    }
}
