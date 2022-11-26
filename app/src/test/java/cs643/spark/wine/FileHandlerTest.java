package cs643.spark.wine;

import org.apache.spark.sql.SparkSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    private SparkSession spark;

    @BeforeEach
    public void setUp() throws Exception {
        spark = SparkSession.builder()
        .appName("unit_test")
        .master("local[*]")
        // .config("spark.executor.memory", "2147480000")
        // .config("spark.driver.memory", "2147480000")
        // .config("spark.testing.memory", "2147480000")
        .getOrCreate();
    }

    @AfterEach
    public void tearDown() throws Exception {
        spark.stop();
        spark.close();
    }


    @Test
    void testGetDataFrame() {

        var workingDir = System.getProperty("user.dir");
        var actual = FileHandler.getDataFrame(this.spark, workingDir + "/bin/test/td.csv");

        actual.show(5);
        assertEquals(5, actual.count());
    }

}
