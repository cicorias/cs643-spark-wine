package cs643.spark.wine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructType;

public class FileHandler {
    private static Logger logger = LoggerFactory.getLogger(FileHandler.class);
    static String[] columns = new String[] {
        "fixed_acidity",
        "volatile_acidity",
        "citric_acid",
        "residual_sugar",
        "chlorides",
        "free_sulfur_dioxide",
        "total_sulfur_dioxide",
        "density",
        "pH",
        "sulphates",
        "alcohol",
        "label"};

    // TODO: does this need to be in reverse order.
    static String[] features = Arrays.asList(columns).subList(0, columns.length - 1).toArray(new String[0]);

    public static Dataset<Row> getDataFrame(SparkSession spark, String name) {
        StructType schema = buildSchema(columns);

        Dataset<Row> validationDf = spark.read().format("csv")
                .option("header", "true")
                .option("multiline", true)
                .option("sep", ";")
                .schema(schema)
                .load(name);

        Dataset<Row> lblFeatureDf = validationDf.select("label", features);

        //lblFeatureDf = lblFeatureDf.na().drop().cache();

        VectorAssembler assembler = new VectorAssembler()
                .setInputCols(features)
                .setOutputCol("features");

        //if (transform)
        lblFeatureDf = assembler.transform(lblFeatureDf).select("label", "features");

        return lblFeatureDf;
    }


    static StructType buildSchema(String[] columns) {
        StructType schema = new StructType();
        for (String col : columns) {
            schema = schema.add(col, DataTypes.DoubleType, false, Metadata.empty());
        }
        return schema;
    }
}
