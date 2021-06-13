package com.jmb.batchapp.persistence.repository;

import com.jmb.batchapp.persistence.entity.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends CrudRepository<Sale, Integer> {
    List<Sale> findAll();
}
