package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayEnum;
import cs3500.pa05.model.Events;
import cs3500.pa05.model.Week;
import cs3500.pa05.viewer.CreateEventDialog;
import java.util.List;
import java.util.Optional;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Dialog;

/**
 * the event handler
 */
public class CreateEventHandler implements EventHandler {
  private List<Events> events;
  private Day day;
  private Week week;
  private Dialog dialog;
  private Controller controller;

  /**
   * constructs a new CreateEventHandler
   *
   * @param events the list of current events
   * @param week the week
   * @param day the day to place the event in
   * @param controller the journal controller
   * @param dialog the dialog box
   */
  public CreateEventHandler(List<Events> events, Week week, Day day,
                            Controller controller, Dialog dialog) {
    this.events = events;
    this.week = week;
    this.day = day;
    this.dialog = dialog;
    this.controller = controller;
  }

  /**
   * handles when the new Event button is clicked
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {

    Dialog popup = new CreateEventDialog(DayEnum.valueOf(day.getDay()));
    Optional<Events> result = popup.showAndWait();

    result.ifPresent((Events e) -> {
      events.add(e);
      controller.initialize();
      dialog.close();
    });
  }
}
