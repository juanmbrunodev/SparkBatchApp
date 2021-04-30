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
}
