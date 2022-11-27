package cs643.spark.wine;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator;
import org.apache.spark.ml.tuning.CrossValidatorModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Predict {
    private static Logger logger = LoggerFactory.getLogger(Predict.class);

    String testFile;
    SparkSession spark;

    public Predict(String master, String appName, String testFile) {
        if (master == null)
            master = "local[*]";

        if (appName == null)
            appName = "Predict";

        this.testFile = testFile;
        this.spark = SparkSession.builder()
                .appName(appName)
                .master(master)
                .getOrCreate();
    }

    public void Run() {

        CrossValidatorModel cvModel = CrossValidatorModel.load("./model/cvModel/");

        Dataset<Row> testDf = FileHandler.getDataFrame(spark, testFile);

        Dataset<Row> predictionDF = cvModel.transform(testDf);

        predictionDF.show();

        predictionDF.select("label", "prediction").show();
        printMertics(predictionDF);

    }


    void writeOutput(StringBuilder sb) {
        String pattern = "yyyy-MM-dd-HH-mm-ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        String fileName = "output-" + date + ".txt";

        try (OutputStream os = new FileOutputStream(fileName)) {
            logger.info("output written to file {}", fileName);
            os.write(sb.toString().getBytes(), 0, sb.toString().length());
            os.write(System.lineSeparator().getBytes(), 0, System.lineSeparator().length());
            os.flush();

        } catch (Exception e) {
            logger.error("error writing output", e);
        }

    }
    
    void printMertics(Dataset<Row> predictions) {
        StringBuilder sb = new StringBuilder();
        
        MulticlassClassificationEvaluator evaluator = new MulticlassClassificationEvaluator();
        evaluator.setMetricName("accuracy");

        sb.append("The accuracy of the model is " + evaluator.evaluate(predictions) +  System.lineSeparator());

        evaluator.setMetricName("f1");
        double f1 = evaluator.evaluate(predictions);

        sb.append("F1: " + f1 +  System.lineSeparator());

        writeOutput(sb);
    }
}
