package core;

import entity.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventAnalysisTest {
    private EventAnalysis analysis;
    private String[] paths;
    private EventReader reader;

    @Before
    public void setAnalysis() {
        paths = new String[1];
        this.reader = new EventReader(paths);
        this.analysis = new EventAnalysis();
    }

    @Test
    public void analyzeDurationReturnedSmallerListCauseOfBadState() {
        paths[0] = "E:\\Pulpit\\challenge\\src\\test\\resources\\eventAnalysis\\logfileWithInvalidStateInJSONObject.json";
        List<Event> events = analysis.analyzeDuration(reader.readLogs());
        assertEquals(2, events.size());
    }

    @Test
    public void analyzeDurationReturnedSmallerListCauseOfWrongOfTheSameEvent() {
        paths[0] = "E:\\Pulpit\\challenge\\src\\test\\resources\\eventAnalysis\\logfileWithInvalidAmountOfTheSameEvent.json";
        List<Event> events = analysis.analyzeDuration(reader.readLogs());
        assertEquals(3, events.size());
    }

    @Test
    public void analyzeDurationWhenTimestampOfTheEventIsTheSame() {
        paths[0] = "E:\\Pulpit\\challenge\\src\\test\\resources\\eventAnalysis\\logfileWithTheSameTimestampInTheSameEvent.json";
        List<Event> events = analysis.analyzeDuration(reader.readLogs());
        assertEquals(1, events.size());
    }

}