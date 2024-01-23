package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a JSON object for an event.
 */
public record EventJson(
    @JsonProperty("Name") String name,
    @JsonProperty("Description") String description,
    @JsonProperty("Day") String day,
    @JsonProperty("Start Time") String startTime,
    @JsonProperty("Duration") String duration
) {
}
