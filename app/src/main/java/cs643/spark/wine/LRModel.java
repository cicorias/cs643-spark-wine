package cs643.spark.wine;

import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class LRModel {

    SparkSession spark;
    Dataset<Row> trainingDf;
    Dataset<Row> validationDf;

    public LRModel() {
        this(null, null);
    }

    public LRModel(String master, String appName) {
        if (master == null)
            master = "local[*]";

        if (appName == null)
            appName = "LRModel";

        this.spark = SparkSession.builder()
                .appName(appName)
                .master(master)
                .getOrCreate();

    }

    public void setTrainingData(String trainDataPath) {
        trainingDf = FileHandler.getDataFrame(spark, trainDataPath);
    }

    public void setValidationData(String validationDataPath) {
        validationDf = FileHandler.getDataFrame(spark, validationDataPath);
    }

    public void evaluate() {

    }

}// https://spark.apache.org/docs/latest/ml-classification-regression.html#logistic-regression
