package cs3500.pa05.controller;

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
import cs3500.pa05.model.json.JsonUtils;
import cs3500.pa05.model.json.TaskJson;
import cs3500.pa05.model.json.ThemeJson;
import cs3500.pa05.model.json.WeekJson;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * a JsonConverter
 */
public class JsonConverter {
  /**
   * converts a JsonNode into a Week
   *
   * @param jsonNode the JsonNode received
   * @return a Week
   */
  public static Week convertJsonToWeek(JsonNode jsonNode) {
    ObjectMapper mapper = new ObjectMapper();
    WeekJson weekJson = mapper.convertValue(jsonNode, WeekJson.class);

    Day[] days = new Day[7];
    for (int i = 0; i < 7; i++) {
      DayJson dayJson = mapper.convertValue(weekJson.days().get(i), DayJson.class);

      List<Events> events = new ArrayList<>();
      for (int j = 0; j < dayJson.events().size(); j++) {
        EventJson event = mapper.convertValue(dayJson.events().get(j), EventJson.class);
        events.add(new Events(event.name(), event.description(), DayEnum.valueOf(event.day()),
            LocalTime.parse(event.startTime()), LocalTime.parse(event.duration())));
      }
      List<Task> tasks = new ArrayList<>();
      for (int j = 0; j < dayJson.tasks().size(); j++) {
        TaskJson task = mapper.convertValue(dayJson.tasks().get(j), TaskJson.class);
        tasks.add(new Task(task.name(), task.description(), DayEnum.valueOf(task.day()),
            task.completed())); // fix this
      }

      Day day = new Day(DayEnum.valueOf(dayJson.day()), events, tasks);
      days[i] = day;
    }

    List<Theme> themes = new ArrayList<>();
    for (int j = 0; j < weekJson.themes().size(); j++) {
      ThemeJson themeJson = mapper.convertValue(weekJson.themes().get(j), ThemeJson.class);
      themes.add(new Theme.ThemeBuilder()
              .setBackgroundColor(Color.web(themeJson.backgroundColor()))
          .setFontColor(Color.web(themeJson.fontColor()))
          .setFontFamily(themeJson.fontFamily())
          .setImages(themeJson.images()).build());
    }
    return new Week(weekJson.title(), days, themes, weekJson.currentTheme(), weekJson.notes(),
        weekJson.maxEvents(), weekJson.maxTasks());
  }

  /**
   * converts a week into a JsonNode
   *
   * @param week the week to convert
   * @return a Week represented as a JsonNode
   */
  public static JsonNode convertWeekToJson(Week week) {
    List<DayJson> dayJson = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      Day day = week.getDay(i);
      List<EventJson> events = new ArrayList<>();
      for (int j = 0; j < day.getEvents().size(); j++) {
        Events e = day.getEvents().get(j);
        events.add(new EventJson(e.getName(), e.getDescription(), e.getDay(),
            e.getStartTime().toString(), e.getDuration().toString()));
      }
      List<TaskJson> tasks = new ArrayList<>();
      for (int j = 0; j < day.getTasks().size(); j++) {
        Task t = day.getTasks().get(j);
        tasks.add(new TaskJson(t.getName(), t.getDescription(), t.getDay(), t.getComplete()));
      }
      dayJson.add(new DayJson(day.getDay(), events, tasks));
    }

    List<TaskJson> taskQueueJson = new ArrayList<>();
    for (int i = 0; i < week.getTaskQueue().size(); i++) {
      Task t = week.getTaskQueue().get(i);
      taskQueueJson.add(new TaskJson(t.getName(), t.getDescription(), t.getDay(), t.getComplete()));
    }

    List<ThemeJson> themeJson = new ArrayList<>();
    for (int i = 0; i < week.getThemes().size(); i++) {
      Theme theme = week.getThemes().get(i);
      themeJson.add(new ThemeJson(theme.getBackgroundColor().toString(),
          theme.getFontColor().toString(),
          theme.getFontFamily(), theme.getImages()));
    }

    return JsonUtils.serializeRecord(new WeekJson(week.getTitle(), dayJson, taskQueueJson,
        themeJson, week.getCurrentTheme(),
        week.getNotes(), week.getMaxEvents(), week.getMaxTasks()));
  }
}
