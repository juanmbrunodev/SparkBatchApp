package com.jmb.batchapp;

import com.jmb.batchapp.exception.ExceptionHandler;
import com.jmb.batchapp.job.Job;
import com.jmb.batchapp.parameter.CommonJobParameter;
import com.jmb.batchapp.parameter.JobsParameters;
import com.jmb.batchapp.parameter.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class SparkBatchApp implements CommandLineRunner {

	private static Logger LOGGER = LoggerFactory
			.getLogger(SparkBatchApp.class);

	@Autowired
	private ExceptionHandler exceptionHandler;

	@Autowired
	private Parameters parameters;

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		LOGGER.info("Starting Batch Application");
		SpringApplication.run(SparkBatchApp.class, args);
		LOGGER.info("Batch Application Finished");
	}

	@Override
	public void run(String... args) {
		try {
			LOGGER.info("Parsing common job arguments");
			loadCommonParams(args);
			LOGGER.info("Instantiating job");
			Job job = (Job) context.getBean(parameters.getParamValue(CommonJobParameter.JOB_NAME), CommonJobParameter.JOB_NAME);
			LOGGER.info("Loading Job Specific Arguments");
			loadJobParams(args);
			LOGGER.info("Executing job");
			job.execute();
		} catch (Exception be) {
			exceptionHandler.handleException(be);
			throw be;
		}
	}

	private void loadCommonParams(String[] args) {
		parameters.loadAllParams(args, CommonJobParameter.values());
	}

	private void loadJobParams(String[] args) {
		String jobName = parameters.getParamValue(CommonJobParameter.JOB_NAME);
		Optional<JobsParameters> paramsFound = JobsParameters.getParamsForJob(jobName);
		if(paramsFound.isPresent()) {
			parameters.loadAllParams(args, paramsFound.get().getParameters());
		}
	}
}
