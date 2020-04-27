package conf;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;

public class LoggerConfiguration {
    public Logger getLogger(Class<?> clazz){
        if(clazz == null)
            throw new NullPointerException("Missing or bad arguments passet to the method");
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        File loggerConfig = new File("E:\\Pulpit\\challenge\\src\\main\\resources\\log4j.xml");
        context.setConfigLocation(loggerConfig.toURI());
        return LogManager.getLogger(clazz);
    }
}
