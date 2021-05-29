package com.jmb.batchapp.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT_CONFIG")
public class ClientConfiguration {

    public ClientConfiguration(){};

    @Id @GeneratedValue
    private Integer id;
    private String clientId;
    private String configName;
    private String value;
}
