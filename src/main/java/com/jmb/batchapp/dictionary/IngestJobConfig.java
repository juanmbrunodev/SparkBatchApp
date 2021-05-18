package com.jmb.batchapp.dictionary;

public enum IngestJobConfig {

    INPUT_PATH("inputPath");

    //Others to be added...

    private String configName;

    IngestJobConfig(String configName) {
        this.configName = configName;
    }

    public String getConfigName() {
        return configName;
    }
}
