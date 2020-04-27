package core;

import org.junit.Test;

public class EventReaderTest {
    private EventReader reader;
    private final String[] paths = new String[1];

    @Test(expected = IllegalStateException.class)
    public void readLogsExpectsIllegalStateExceptionWhenNull(){
        reader = new EventReader(null);
        reader.readLogs();
    }

    @Test(expected = IllegalStateException.class)
    public void readLogsExpectsIllegalStateExceptionWhenArgsEmpty(){
        reader =new EventReader(new String[0]);
        reader.readLogs();
    }

    @Test(expected = IllegalStateException.class)
    public void readLogsExpectsIllegalStateExceptionWhenJSONFormatIsInvalid(){
        paths[0] = "E:\\Pulpit\\challenge\\src\\test\\resources\\eventReader\\logfileWithInvalidStructure.json";
        reader = new EventReader(paths);
        reader.readLogs();
    }

    @Test(expected = IllegalStateException.class)
    public void readLogsExpectsIllegalStateExceptionWhenJSONObjectDoesNotHaveID(){
        paths[0] = "E:\\Pulpit\\challenge\\src\\test\\resources\\eventReader\\logfileWithInvalidIdInJSONObject.json";
        reader = new EventReader(paths);
        reader.readLogs();
    }

    @Test(expected = IllegalStateException.class)
    public void readLogsExpectsIllegalStateExceptionWhenJSONObjectDoesNotHaveTimestamp(){
        paths[0] = "E:\\Pulpit\\challenge\\src\\test\\resources\\eventReader\\logfileWithInvalidTimestampInJSONObject.json";
        reader = new EventReader(paths);
        reader.readLogs();
    }
}