package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a week in JSON format.
 */
public record WeekJson(
    @JsonProperty("title") String title,
    @JsonProperty("days") List<DayJson> days,
    @JsonProperty("taskQueue") List<TaskJson> taskQueue,
    @JsonProperty("themes") List<ThemeJson> themes,
    @JsonProperty("currentTheme") int currentTheme,
    @JsonProperty("notes") String notes,
    @JsonProperty("maxEvents") int maxEvents,
    @JsonProperty("maxTasks") int maxTasks
) {
}
