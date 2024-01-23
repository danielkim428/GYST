package cs3500.pa05.viewer;

import cs3500.pa05.model.DayEnum;
import cs3500.pa05.model.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * a CreateTaskDialog
 */
public class CreateTaskDialog extends Dialog {
  private DayEnum dayEnum;
  private TextField title;
  private TextField description;

  /**
   * constructs a new CreateTaskDialog
   *
   * @param dayEnum the day to add the task to
   */
  public CreateTaskDialog(DayEnum dayEnum) {
    super();
    this.setTitle("Create a New Task");
    this.dayEnum = dayEnum;
    buildUi();
    setResultConverter();
  }

  /**
   * builds the UI
   */
  private void buildUi() {
    Pane pane = buildDialog();
    getDialogPane().setContent(pane);

    getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);

    Button button = (Button) getDialogPane().lookupButton(ButtonType.FINISH);
    button.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

      /**
       * handles the event when the user inputs texts into the dialog
       *
       * @param event the event which occurred
       */
      @Override
      public void handle(ActionEvent event) {
        if (validate()) {
          event.consume();
        }
      }

      /**
       * @return validates that the title text box isn't empty
       */
      private boolean validate() {
        return (title.getText().isEmpty());
      }
    });

  }

  /**
   * generates the dialog pane
   *
   * @return the dialog pane
   */
  private Pane buildDialog() {

    Label titleLabel = new Label("Title: ");
    Label descriptionLabel = new Label("Desc: ");

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(5);
    grid.add(titleLabel, 0, 0);
    grid.add(descriptionLabel, 0, 1);

    title = new TextField();
    description = new TextField();

    grid.add(title, 1, 0);
    GridPane.setHgrow(this.title, Priority.ALWAYS);
    grid.add(description, 1, 1);
    GridPane.setHgrow(this.description, Priority.ALWAYS);

    VBox content = new VBox(10);

    content.getChildren().add(grid);

    return content;
  }

  /**
   * returns the Task made
   */
  public void setResultConverter() {
    Callback<ButtonType, Task> taskResult = new Callback<ButtonType, Task>() {
      @Override
      public Task call(ButtonType param) {
        if (param == ButtonType.FINISH) {
          return new Task(title.getText(), description.getText(), dayEnum, false);
        } else {
          return null;
        }
      }
    };
    setResultConverter(taskResult);
  }
}
