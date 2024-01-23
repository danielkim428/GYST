package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayEnum;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import cs3500.pa05.viewer.CreateTaskDialog;
import java.util.List;
import java.util.Optional;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Dialog;

/**
 * the event handler for creating tasks
 */
public class CreateTaskHandler implements EventHandler {
  private List<Task> taskList;
  private Day day;
  private Week week;
  private Dialog dialog;
  private Controller controller;

  /**
   * @param taskList the list of current tasks
   * @param week the given week
   * @param day the Day in a week
   * @param controller the journal controller
   * @param dialog the dialog to be opened
   */
  public CreateTaskHandler(List<Task> taskList, Week week, Day day,
                           Controller controller, Dialog dialog) {
    this.taskList = taskList;
    this.week = week;
    this.day = day;
    this.dialog = dialog;
    this.controller = controller;
  }


  /**
   * handles when the new Task dialog is clicked
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    CreateTaskDialog popup = new CreateTaskDialog(DayEnum.valueOf(day.getDay()));
    Optional<Task> result = popup.showAndWait();

    result.ifPresent((Task task) -> {
      taskList.add(task);
      week.addTaskQueue(task);
      controller.initialize();
      dialog.close();
    });
  }
}


