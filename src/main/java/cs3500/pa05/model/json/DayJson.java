package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a day in the week in JSON format.
 */
public record DayJson(
    @JsonProperty("Day") String day,
    @JsonProperty("Events") List<EventJson> events,
    @JsonProperty("Tasks") List<TaskJson> tasks
) {
}
