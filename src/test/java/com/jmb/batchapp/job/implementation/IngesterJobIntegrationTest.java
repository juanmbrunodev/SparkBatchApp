package com.jmb.batchapp.job.implementation;

import com.jmb.batchapp.job.context.IngesterJobContext;
import com.jmb.batchapp.parameter.CommonJobParameter;
import com.jmb.batchapp.parameter.IngesterJobParams;
import com.jmb.batchapp.persistence.entity.Sale;
import com.jmb.batchapp.persistence.repository.SalesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {"command.line.runner.enabled=false", "application.runner.enabled=false" })
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class IngesterJobIntegrationTest {

    @Autowired
    private IngesterJob ingesterJob;

    @Autowired
    private IngesterJobContext ingesterJobContext;

    @Autowired
    private SalesRepository salesRepository;

    @Test
    public void testAndAssertIngesterJob() {
        //Build and load parameters for job
        String[] args = {"jobName=ingesterJob", "clientId=client1", "readFormat=json", "fileName=salestest"};
        loadParamsToContext(args);
        //Execute Job
        ingesterJob.execute();
        //Assert Job business logic applied successfully
        assertSalesIngested();
    }

    private void loadParamsToContext(String[] args) {
        ingesterJobContext.getParams().loadAllParams(args, CommonJobParameter.values());
        ingesterJobContext.getParams().loadAllParams(args, IngesterJobParams.values());
    }

    private void assertSalesIngested() {
        List<Sale> sales = salesRepository.findAll();
        assertEquals(11, sales.size());
    }

}
