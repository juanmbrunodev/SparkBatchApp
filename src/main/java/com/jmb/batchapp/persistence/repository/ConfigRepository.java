package com.jmb.batchapp.persistence.repository;

import com.jmb.batchapp.persistence.entity.JobConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<JobConfiguration, Integer> {
}
