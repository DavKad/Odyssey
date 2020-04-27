import conf.LoggerConfiguration;
import core.EventAnalysis;
import core.EventPersistent;
import core.EventReader;
import database.DatabaseFactory;
import entity.Event;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class Main {
    private static final Logger logger = new LoggerConfiguration().getLogger(Main.class);
    public static void main(String[] args) {
        /* Develooping stage PATH */
        args = new String[1];
        args[0] = "E:\\Pulpit\\challenge\\src\\main\\resources\\logfile.json";
        EventReader reader = new EventReader(args);
        Map<String, List<Event>> events = reader.readLogs();
        EventAnalysis analysis = new EventAnalysis();
        List<Event> analyzedEvents = analysis.analyzeDuration(events);

        logger.info("Database stage");
        DatabaseFactory factory = new DatabaseFactory();
        EventPersistent persistent = new EventPersistent(factory);
        persistent.initializeEntity();
        analyzedEvents.forEach(persistent::saveEvent);
        persistent.closeEntity();
    }
}
