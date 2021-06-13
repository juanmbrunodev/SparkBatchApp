package com.jmb.batchapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SparkBatchApp {

	private static Logger LOGGER = LoggerFactory
			.getLogger(SparkBatchApp.class);

	public static void main(String[] args) {
		SpringApplication.run(SparkBatchApp.class, args);
		LOGGER.info("Starting Batch Application");
	}
}
