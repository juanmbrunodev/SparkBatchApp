package com.jmb.batchapp.persistence.repository;

import com.jmb.batchapp.persistence.entity.JobConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends CrudRepository<JobConfiguration, Integer> {
    JobConfiguration findTop1ByJobNameAndClientIdAndConfigName(String jobName, String clientId, String configName);
}
