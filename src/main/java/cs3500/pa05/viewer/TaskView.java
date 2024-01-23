package cs3500.pa05.viewer;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.TaskDeleteHandler;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Task;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * a TaskView
 */
public class TaskView extends BlockView {
  /**
   * constructs a new TaskView
   *
   * @param task the task to display
   * @param taskQueue the task queue
   * @param controller the journal controller
   * @param day the given day
   * @param maxView the max view
   */
  public TaskView(Task task, ObservableList<Task> taskQueue, Controller controller,
                  Day day, MaxView maxView) {
    super();
    this.setAlignment(Pos.CENTER);
    Label taskLabel = new Label("Task");
    taskLabel.setPadding(new Insets(0, 0, 3, 0));
    Label name = new Label(task.getName());
    name.setWrapText(true);
    name.setMaxWidth(100);
    name.setAlignment(Pos.CENTER);
    name.setPadding(new Insets(0, 0, 3, 0));
    HBox horiBox = new HBox();
    horiBox.setAlignment(Pos.CENTER);
    CheckBox complete = new CheckBox();
    complete.setTooltip(new Tooltip("Mark as Complete?"));
    complete.setSelected(task.getComplete());
    complete.setPadding(new Insets(0, 0, 0, 3));
    complete.setOnAction(e -> {
      task.changeComplete();
      List<Task> temp = new ArrayList<>(taskQueue);
      taskQueue.clear();
      taskQueue.addAll(temp);
      controller.initialize();
    });
    Label completed = new Label("Completed?");
    horiBox.getChildren().addAll(completed, complete);
    this.getChildren().addAll(taskLabel, name, horiBox);
    if (!task.getDescription().equals("")) {
      Label description = new Label(task.getDescription());
      description.setAlignment(Pos.CENTER);
      description.setPadding(new Insets(0, 0, 3, 0));
      description.setWrapText(true);
      description.setMaxWidth(100);
      this.getChildren().add(description);
    }
    Button delete = new Button("Delete");
    delete.setOnAction(e -> {
      new TaskDeleteHandler(task, taskQueue, day, this, maxView).handle(e);
      controller.initialize();
    });
    this.getChildren().add(delete);
  }
}
