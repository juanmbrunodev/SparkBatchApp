package com.jmb.batchapp.schema;

import org.apache.spark.sql.types.*;

/**
 * Enum holds the schema of a Spark Row as produced by the {@link com.jmb.batchapp.job.component.process.SalesSummaryProcessor}
 *
 * @author JuanMBruno.
 */
public enum SalesSummarySchema {

    SELLER_ID("SELLER_ID", DataTypes.StringType, true, null),
    SALES_DATE("SALES_DATE", DataTypes.DateType, true, null),
    PRODUCT("PRODUCT", DataTypes.StringType, true, null),
    QUANTITY("QUANTITY", DataTypes.IntegerType, true, null);

    private String name;
    private DataType type;
    private boolean nullable;
    private Metadata metadata;

    SalesSummarySchema(String name, DataType stringType, boolean nullable, Metadata metadata) {
        this.name = name;
        this.type = stringType;
        this.nullable = nullable;
        this.metadata = metadata;
    }

    public static StructType getSparkSchema() {
        int schemaSize = values().length;
        StructField[] fields = new StructField[schemaSize];
        SalesSummarySchema[] schemaFields = values();

        for (int i = 0; i < schemaSize; i++) {
            fields[i] = new StructField(schemaFields[i].name, schemaFields[i].type, schemaFields[i].nullable, schemaFields[i].metadata);
        }

        return new StructType(fields);
    }
}
