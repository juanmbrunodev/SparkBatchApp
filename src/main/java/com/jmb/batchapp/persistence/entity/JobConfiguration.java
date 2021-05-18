package com.jmb.batchapp.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "JOB_CONFIG")
public class JobConfiguration {

    public JobConfiguration(){};

    @Id @GeneratedValue
    private Integer id;
    private String clientId;
    private String jobName;
    private String configName;
    private String value;

    //Getters and Setters

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
