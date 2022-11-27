package cs643.spark.wine;

public class ClassifierResult {
    public String classifierName;
    public double accuracy;
    public double precision;
    public double recall;
    public double f1;
    public double areaUnderROC;
    public double areaUnderPR;
    public double trainingTime;
    public double validationTime;
    public double predictionTime;
    public double totalExecutionTime;

    public String getClassifierName() {
        return classifierName;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getRecall() {
        return recall;
    }

    public void setRecall(double recall) {
        this.recall = recall;
    }

    public double getF1() {
        return f1;
    }

    public void setF1(double f1) {
        this.f1 = f1;
    }

    public double getAreaUnderROC() {
        return areaUnderROC;
    }

    public void setAreaUnderROC(double areaUnderROC) {
        this.areaUnderROC = areaUnderROC;
    }

    public double getAreaUnderPR() {
        return areaUnderPR;
    }

    public void setAreaUnderPR(double areaUnderPR) {
        this.areaUnderPR = areaUnderPR;
    }

    public double getTrainingTime() {
        return trainingTime;
    }

    public void setTrainingTime(double trainingTime) {
        this.trainingTime = trainingTime;
    }

    public double getValidationTime() {
        return validationTime;
    }

    public void setValidationTime(double validationTime) {
        this.validationTime = validationTime;
    }

    public double getPredictionTime() {
        return predictionTime;
    }

    public void setPredictionTime(double predictionTime) {
        this.predictionTime = predictionTime;
    }

    public double getTotalExecutionTime() {
        return totalExecutionTime;
    }

    public void setTotalExecutionTime(double totalExecutionTime) {
        this.totalExecutionTime = totalExecutionTime;
    }

    public ClassifierResult(String classifierName) {
        this.classifierName = classifierName;
    }

    public String toString() {
        return String.format("%s: Accuracy: %f, Precision: %f, Recall: %f, F1: %f, AUC: %f, AUPR: %f, Training Time: %f, Validation Time: %f, Prediction Time: %f, Total Execution Time: %f",
                classifierName, accuracy, precision, recall, f1, areaUnderROC, areaUnderPR, trainingTime, validationTime, predictionTime, totalExecutionTime);
    }
}
