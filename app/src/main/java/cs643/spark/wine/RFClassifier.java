package cs643.spark.wine;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.RandomForestClassifier;
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator;
import org.apache.spark.ml.feature.Normalizer;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.tuning.CrossValidator;
import org.apache.spark.ml.tuning.CrossValidatorModel;
import org.apache.spark.ml.tuning.ParamGridBuilder;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RFClassifier {
    static Logger logger = LoggerFactory.getLogger(RFClassifier.class);
    Pipeline pipeline;

    public Pipeline getPipeline() {
        return pipeline;
    }

    SparkSession spark;
    Dataset<Row> trainingDf;
    Dataset<Row> validationDf;
    String s3Bucket;

    public RFClassifier() {
        this(null, null, null);
    }

    public RFClassifier(String master, String appName, String s3bucket) {
        this.s3Bucket = s3bucket;
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
        if (null != this.s3Bucket) {
            trainDataPath = "s3a://" + this.s3Bucket + "/" + trainDataPath;
        }
        trainingDf = FileHandler.getDataFrame(spark, trainDataPath);
        // trainingDf.persist(StorageLevel.MEMORY_AND_DISK());
    }

    public void setValidationData(String validationDataPath) {
        // var rr = SparkFiles.get(validationDataPath);
        if (null != this.s3Bucket) {
            validationDataPath = "s3a://" + this.s3Bucket + "/" + validationDataPath;
        }
        validationDf = FileHandler.getDataFrame(spark, validationDataPath);
        // validationDf.persist(StorageLevel.MEMORY_AND_DISK());
    }

    public ClassifierResult evaluate() {
        RandomForestClassifier classifier = new RandomForestClassifier();
        // .setMaxIter(10)
        // .setRegParam(0.3)
        // .setElasticNetParam(0.8);

        VectorAssembler assembler = new VectorAssembler()
                .setInputCols(FileHandler.features)
                .setOutputCol("inputFeatures");

        Normalizer normalizer = new Normalizer()
                .setInputCol("inputFeatures")
                .setOutputCol("features");
        // .setP(1.0);

        this.pipeline = new Pipeline()
                .setStages(new PipelineStage[] { assembler, normalizer, classifier });

        ParamMap[] paramGrid = new ParamGridBuilder().build();

        MulticlassClassificationEvaluator evaluator = new MulticlassClassificationEvaluator()
                .setMetricName("f1");
        // .setLabelCol("label");
        // .setPredictionCol("prediction");

        CrossValidator cv = new CrossValidator()
                .setEstimator(this.pipeline)
                .setEvaluator(evaluator)
                .setEstimatorParamMaps(paramGrid)
                .setNumFolds(3);

        CrossValidatorModel cvModel = cv.fit(trainingDf);

        Dataset<Row> predictions = cvModel.transform(validationDf);

        double f1 = evaluator.evaluate(predictions);
        logger.info("F1 score: " + f1);

        ClassifierResult result = new ClassifierResult("Logistic Regression");
        result.setF1(f1);
        return result;
    }
}
