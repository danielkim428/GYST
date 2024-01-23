package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Week;
import cs3500.pa05.viewer.ChooseNewDialog;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Dialog;

/**
 * a handler for choosing either a new event or task
 */
public class ChooseNewHandler implements EventHandler {
  Day day;
  Week week;
  Controller controller;

  /**
   * constructs a new ChooseNewHandler
   *
   * @param day the day of the week
   * @param week the week
   * @param controller the week controller
   */
  public ChooseNewHandler(Day day, Week week, Controller controller) {
    this.day = day;
    this.week = week;
    this.controller = controller;
  }

  /**
   * creates a new popup dialog for a user to choose between two options
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    Dialog popup = new ChooseNewDialog(day, week, controller);
    popup.show();
  }
}
