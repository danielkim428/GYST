package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a theme in JSON format.
 */
public record ThemeJson(
    @JsonProperty("Background Color") String backgroundColor,
    @JsonProperty("Font Color") String fontColor,
    @JsonProperty("Font Family") String fontFamily,
    @JsonProperty("Images") List<String> images
) {
}
