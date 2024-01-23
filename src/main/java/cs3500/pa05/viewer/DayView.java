package cs3500.pa05.viewer;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Events;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * a DayView
 */
public class DayView extends VBox {
  /**
   * constructs a DayView
   *
   * @param day the given Day
   * @param week the week
   * @param controller the journal controller
   */
  public DayView(Day day, Week week, Controller controller) {
    MaxView maxEventsView = new MaxView("Events: ", day.getEvents().size(),
        week.getMaxEventsStrProp());
    abstractDayView(day, week, controller, maxEventsView);
    MaxView maxTasksView = new MaxView("Tasks: ", day.getTasks().size(),
        week.getMaxTasksStrProp());
    VBox taskContainer = new VBox();
    for (Task t : day.getTasks()) {
      taskContainer.getChildren().add(new TaskView(t, week.getTaskQueue(),
          controller, day, maxTasksView));
    }
    this.getChildren().addAll(taskContainer,
        new ButtonView(day, week, controller), maxEventsView, maxTasksView);
  }

  /**
   * constructs a new DayView
   *
   * @param day the given day
   * @param query the query
   * @param week the given week
   * @param controller the journal controller
   */
  public DayView(Day day, String query, Week week, Controller controller) {
    MaxView maxEventsView = new MaxView("Events: ", day.getEvents().size(),
        week.getMaxEventsStrProp());
    abstractDayView(day, week, controller, maxEventsView);
    MaxView maxTasksView = new MaxView("Tasks: ", day.getTasks().size(),
        week.getMaxTasksStrProp());
    VBox taskContainer = new VBox();
    for (Task t : day.getTasks()) {
      if (t.getDescription().toLowerCase().contains(query)
          || t.getName().toLowerCase().contains(query)
          || t.getDay().toLowerCase().contains(query)) {
        taskContainer.getChildren().add(new TaskView(t, week.getTaskQueue(),
            controller, day, maxTasksView));
      }
    }
    this.getChildren().addAll(taskContainer,
        new ButtonView(day, week, controller), maxEventsView, maxTasksView);
  }

  /**
   * generates an abstract day view
   *
   * @param day the given day
   * @param week the given week
   * @param controller the journal controller
   * @param maxEventsView the max view
   */
  private void abstractDayView(Day day, Week week, Controller controller, MaxView maxEventsView) {
    this.setPadding(new Insets(10));
    this.setAlignment(Pos.TOP_CENTER);
    Label dayOfTheWeek = new Label(day.getDay());
    dayOfTheWeek.setPadding(new Insets(10));
    this.getChildren().add(dayOfTheWeek);
    VBox eventContainer = new VBox();
    for (Events e : day.getEvents()) {
      eventContainer.getChildren().add(new EventView(e, day, maxEventsView, controller));
    }
    this.getChildren().add(eventContainer);
  }
}
