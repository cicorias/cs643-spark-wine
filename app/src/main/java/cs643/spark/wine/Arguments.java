package cs643.spark.wine;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class Arguments implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!value.equalsIgnoreCase("training") && !value.equalsIgnoreCase("prediction")) {
            throw new ParameterException("Parameter " + name + " should be either 'training' or 'prediction' (found " + value + ")");
        }
    }   
}
