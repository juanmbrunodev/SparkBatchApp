package com.jmb.batchapp.job.component.write;

import com.jmb.batchapp.job.component.transformation.mapper.IngesterSalesToDbMapper;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit Test class for {@link IngesterWriter}.
 *
 * @author JuanMBruno.
 */
public class IngesterWriterTest {

    private IngesterWriter writerUnderTest;

    @BeforeEach
    public void init() {
        writerUnderTest = new IngesterWriter();
    }

    @DisplayName("Test Writer write method, successful case makes necessary method invocations")
    @Test
    public void testWriterHappyPath() {
        Dataset<Row> mockedDataset = Mockito.mock(Dataset.class);
        Dataset<Row> triggeredMockedDf = setUpMockAndBehaviour(mockedDataset);
        writerUnderTest.write(mockedDataset);
        verify(mockedDataset, times(1)).mapPartitions(any(IngesterSalesToDbMapper.class), any(ExpressionEncoder.class));
        verify(triggeredMockedDf, times(1)).count();
    }

    private Dataset<Row> setUpMockAndBehaviour(Dataset<Row> mockedDatset) {
        Dataset<Row> returnMock = Mockito.mock(Dataset.class);
        when(mockedDatset.mapPartitions(any(IngesterSalesToDbMapper.class), any(ExpressionEncoder.class)))
                .thenReturn(returnMock);
        return returnMock;
    }

    @AfterEach
    public void tearDown() {
        writerUnderTest = null;
    }


}
