package cs3500.pa05.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayEnum;
import cs3500.pa05.model.Events;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Theme;
import cs3500.pa05.model.Week;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import cs3500.pa05.model.json.ThemeJson;
import cs3500.pa05.model.json.WeekJson;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonConverterTest {
  JsonNode expectedJsonNode;
  JsonNode actualJsonNode;
  Week expectedWeek;
  Week actualWeek;

  @BeforeEach
  public void setup() {
    Day[] days = new Day[] {new Day(DayEnum.SUNDAY,
        new ArrayList<>(List.of(new Events("event", "event",
        DayEnum.SUNDAY, LocalTime.parse("10:10", DateTimeFormatter.ofPattern("HH:mm")),
        LocalTime.parse("10:20", DateTimeFormatter.ofPattern("HH:mm"))))),
        new ArrayList<>(List.of(new Task("task", "task",
            DayEnum.SUNDAY, false)))), new Day(DayEnum.MONDAY),
        new Day(DayEnum.TUESDAY), new Day(DayEnum.WEDNESDAY), new Day(DayEnum.THURSDAY),
        new Day(DayEnum.FRIDAY), new Day(DayEnum.SATURDAY)};
    List<Theme> themes = new ArrayList<>(List.of(new Theme.ThemeBuilder()
        .setBackgroundColor(Color.rgb(255, 255, 255))
        .setFontColor(Color.rgb(0, 0, 0))
        .setFontFamily("Arial")
        .setImages(new ArrayList<>(List.of("test.png"))).build()));
    expectedWeek = new Week("Test", days, themes, 0, "notes", 3, 3);

    List<EventJson> eventJsons = new ArrayList<>(List.of(new EventJson("event",
        "event", "SUNDAY", "10:10", "10:20")));
    List<TaskJson> taskJsons = new ArrayList<>(List.of(new TaskJson("task",
        "task", "SUNDAY", false)));
    List<DayJson> dayJsons = new ArrayList<>();
    dayJsons.add(new DayJson("SUNDAY", eventJsons, taskJsons));
    dayJsons.add(new DayJson("MONDAY", new ArrayList<>(), new ArrayList<>()));
    dayJsons.add(new DayJson("TUESDAY", new ArrayList<>(), new ArrayList<>()));
    dayJsons.add(new DayJson("WEDNESDAY", new ArrayList<>(), new ArrayList<>()));
    dayJsons.add(new DayJson("THURSDAY", new ArrayList<>(), new ArrayList<>()));
    dayJsons.add(new DayJson("FRIDAY", new ArrayList<>(), new ArrayList<>()));
    dayJsons.add(new DayJson("SATURDAY", new ArrayList<>(), new ArrayList<>()));
    List<TaskJson> taskQueue = new ArrayList<>(List.of(new TaskJson("task",
        "task", "SUNDAY", false)));
    List<ThemeJson> themesJson = new ArrayList<>();
    themesJson.add(new ThemeJson("0xffffffff", "0x000000ff", "Arial",
        new ArrayList<>(List.of("test.png"))));
    WeekJson weekJson = new WeekJson("Test", dayJsons, taskQueue, themesJson, 0, "notes", 3, 3);
    ObjectMapper mapper = new ObjectMapper();
    expectedJsonNode = mapper.convertValue(weekJson, JsonNode.class);
  }

  @Test
  public void testJsonToWeek() {
    actualWeek = JsonConverter.convertJsonToWeek(expectedJsonNode);
    assertEquals(expectedWeek.getTitle(), actualWeek.getTitle());
    assertEquals(expectedWeek.getNotes(), actualWeek.getNotes());
    assertEquals(expectedWeek.getMaxEvents(), actualWeek.getMaxEvents());
    assertEquals(expectedWeek.getMaxTasks(), actualWeek.getMaxTasks());
  }

  @Test
  public void testWeekToJson() {
    actualJsonNode = JsonConverter.convertWeekToJson(expectedWeek);
    assertEquals(expectedJsonNode, actualJsonNode);
  }
}