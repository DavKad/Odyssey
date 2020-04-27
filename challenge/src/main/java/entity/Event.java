package entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import enums.EventState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @JsonCreator
    public Event(@JsonProperty(value = "id", required = true) String id,
                 @JsonProperty(value = "type") String type,
                 @JsonProperty(value = "state") EventState state,
                 @JsonProperty(value = "host") String hostname,
                 @JsonProperty(value = "timestamp", required = true) long timestamp) {
        this.id = id;
        this.type = type;
        this.state = state;
        this.hostname = hostname;
        this.timestamp = timestamp;
    }

    public Event(String id, String type, String hostname, EventAnalysisResult result) {
        this.id = id;
        this.type = type;
        this.hostname = hostname;
        this.result = result;
    }

    @Id
    private String id;

    @Transient
    private EventState state;
    private String type;
    private String hostname;

    @Transient
    private long timestamp;

    @Embedded
    private EventAnalysisResult result;

    public long eventDuration(Event event) {
        if (this.timestamp != event.timestamp)
            return (this.timestamp > event.timestamp) ?
                    this.timestamp - event.timestamp : (this.timestamp - event.timestamp) * -1;
        else
            return 0;
    }
}
