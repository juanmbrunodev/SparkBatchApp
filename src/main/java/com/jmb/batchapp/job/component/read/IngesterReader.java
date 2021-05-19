package com.jmb.batchapp.job.component.read;

import com.jmb.batchapp.configuration.SparkSessionProvider;
import com.jmb.batchapp.dictionary.IngestJobConfig;
import com.jmb.batchapp.job.context.IngesterJobContext;
import com.jmb.batchapp.parameter.ParameterName;
import com.jmb.batchapp.persistence.repository.ConfigRepository;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean includes logic to read from an input (File) as called by the {@link com.jmb.batchapp.job.implementation.IngesterJob}
 *
 * @author JuanMBruno
 */
@Component
public class IngesterReader implements Reader<Dataset<Row>> {

    private static Logger LOGGER = LoggerFactory
            .getLogger(IngesterReader.class);

    private static final String DOT = ".";

    private IngesterJobContext jobContext;
    private ConfigRepository configRepository;

    @Autowired
    public IngesterReader(IngesterJobContext jobContext,
                          ConfigRepository configRepository) {
        this.jobContext = jobContext;
        this.configRepository = configRepository;
    }

    @Override
    public Dataset<Row> read() {
        SparkSession sparkSession = SparkSessionProvider.provideSession(jobContext.getSparkMode());
        String fileReadFormat = jobContext.getParams().getParamValue(ParameterName.READ_FORMAT);
        String fileName = jobContext.getParams().getParamValue(ParameterName.FILE_NAME);
        String fullPath = readInputPath().concat(fileName).concat(DOT).concat(fileReadFormat);
        //Read using Spark
        DataFrameReader dfReader = sparkSession.read().format(fileReadFormat);
        LOGGER.info("Reading input file...");
        return getReaderForFormat(dfReader, fileReadFormat).load(fullPath);
    }

    private String readInputPath() {
        return configRepository
                .findTop1ByJobNameAndClientIdAndConfigName(jobContext.getParams().getParamValue(ParameterName.JOB_NAME),
                        jobContext.getParams().getParamValue(ParameterName.CLIENT_ID),
                        IngestJobConfig.INPUT_PATH.getConfigName()).getValue();
    }

    //Sets Spark DataFrameReader options according to the format passed
    private DataFrameReader getReaderForFormat(DataFrameReader reader, String format) {
        if (format.equals(IngesterJobContext.CSV_FORMAT)) {
            return reader.option("header", "true");
        } else if (format.equals(IngesterJobContext.JSON_FORMAT)) {
            return reader.option("multiline", "true");
            //Other possible formats below or extract to object with logic...
        } else {
            return reader;
        }
    }
}
