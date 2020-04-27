package core;

import conf.LoggerConfiguration;
import entity.Event;
import entity.EventAnalysisResult;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventAnalysis {
    private final Logger logger = new LoggerConfiguration().getLogger(EventAnalysis.class);

    public List<Event> analyzeDuration(Map<String, List<Event>> events) {
        List<Event> analyzedEvents = new ArrayList<>();
        events.forEach((id, matchedEvents) -> {
            logger.debug("Current event: " + id);
            if (matchedEvents.size() == 2) {
                if (!matchedEvents.get(0).getState().equals(matchedEvents.get(1).getState())) {
                    long duration = matchedEvents.get(0).eventDuration(matchedEvents.get(1));
                    logger.debug("Event duration: " + duration);
                    if (duration != 0) {
                        boolean alert = duration > 4;
                        analyzedEvents.add(new Event(id, matchedEvents.get(0).getType(), matchedEvents.get(0).getHostname(),
                                new EventAnalysisResult(alert, duration)));
                    } else
                        logger.debug("Analyzed event probably has invalid timestamp or was not executed properly");
                } else
                    logger.debug("Aggregated events with the same state. Cannot evaluate event duration");
            } else {
                logger.debug("Found invalid amount of events with the same ID related with event: " + id);
            }
        });
        logger.debug("All events were analyzed.");
        return analyzedEvents;
    }
}
