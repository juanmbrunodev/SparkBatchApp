package com.jmb.batchapp.configuration;

import org.apache.spark.sql.SparkSession;

import java.util.Objects;

/**
 * Class provides Jobs with a Spark Session. It implements a Singleton pattern to provide just one
 * session per job.
 *
 * @author JuanMBruno
 */
public final class SparkSessionProvider {

    private SparkSessionProvider(){}

    private static SparkSession sparkSession;

    public static SparkSession provideSession(String masterMode) {

        if(Objects.isNull(sparkSession)) {
            SparkSession.Builder sessionBuilder =  SparkSession.builder()
                    .appName("SparkBatchApp");
            if(Objects.nonNull(masterMode)) {
                sessionBuilder = sessionBuilder.master(masterMode);
            }
            sparkSession = sessionBuilder.getOrCreate();
        }
        return sparkSession;
    }

}
