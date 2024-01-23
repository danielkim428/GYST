package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Events;
import cs3500.pa05.viewer.MaxView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/**
 * handler for deleting Events
 */
public class EventDeleteHandler implements EventHandler {
  private Events events;
  private Day day;
  private Parent parent;
  private MaxView maxView;

  /**
   * constructs a new event delete handler
   *
   * @param events the given event
   * @param day the given day
   * @param parent the Parent
   * @param maxView the max amount of events view
   */
  public EventDeleteHandler(Events events, Day day, Parent parent, MaxView maxView) {
    this.events = events;
    this.day = day;
    this.parent = parent;
    this.maxView = maxView;
  }

  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    day.removeIfFound(events);
    ((VBox) this.parent.getParent()).getChildren().remove(this.parent);
    maxView.subtract();
  }
}
