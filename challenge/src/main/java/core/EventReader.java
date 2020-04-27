package core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import conf.LoggerConfiguration;
import entity.Event;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventReader {
    private final Logger logger = new LoggerConfiguration().getLogger(EventReader.class);
    private final String[] path;

    public EventReader(String[] path) {
        this.path = path;
    }

    public Map<String, List<Event>> readLogs() {
        if (path == null || path.length == 0) {
            logger.debug("No path to file provided");
            throw new IllegalStateException("No path to file provided");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            logger.debug("Collecting data from the log file");
            List<Event> events = mapper.readValue(new File(path[0]), new TypeReference<List<Event>>() {
            });

            logger.debug("Amount of events collected: " + events.size());
            logger.debug("Grouping events by ID");
            Map<String, List<Event>> groupEvents = events.stream().collect(Collectors.groupingBy(Event::getId));
            logger.debug("Aggregated: " + groupEvents.entrySet().size() + " pair of events.");
            return groupEvents;
        } catch (IOException e) {
            logger.debug("Cannot load or parse given file");
            throw new IllegalStateException("Cannot parse event logs: ", e);
        }
    }
}
