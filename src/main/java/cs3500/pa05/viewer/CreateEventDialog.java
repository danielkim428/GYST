package cs3500.pa05.viewer;

import cs3500.pa05.model.DayEnum;
import cs3500.pa05.model.Events;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
 * a CreateEventDialog
 */
public class CreateEventDialog extends Dialog {
  private DayEnum dayEnum;
  private TextField title = new TextField();
  private TextField description = new TextField();
  private TextField start = new TextField();
  private TextField duration = new TextField();
  private Label warning = new Label("Invalid date format!");

  /**
   * constructs a new CreateEventDialog
   *
   * @param dayEnum the given day to create the event on
   */
  public CreateEventDialog(DayEnum dayEnum) {
    super();
    this.setTitle("Create a New Event");
    this.dayEnum = dayEnum;

    buildUi();
    setResultConverter();
  }

  /**
   * builds the Dialog box for creating a new Event
   */
  private void buildUi() {
    Pane pane = buildDialog();
    getDialogPane().setContent(pane);

    getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);

    Button button = (Button) getDialogPane().lookupButton(ButtonType.FINISH);
    button.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

      /**
       * handles event when user input is in the Dialog
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
       * validates that the fields aren't empty and the times are valid
       *
       * @return boolean on whether the text fields are empty
       */
      private boolean validate() {
        warning.setVisible(false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        boolean validTime = false;
        try {
          LocalTime.parse(start.getText(), formatter);
          LocalTime.parse(duration.getText(), formatter);
        } catch (DateTimeParseException e) {
          validTime = true;
          warning.setVisible(true);
        }

        return title.getText().isEmpty() || start.getText().isEmpty()
            || duration.getText().isEmpty() || validTime;
      }
    });

  }

  /**
   * builds the dialog
   *
   * @return the basic dialog pane
   */
  private Pane buildDialog() {

    warning.setVisible(false);

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(5);
    Label titleLabel = new Label("Title: ");
    grid.add(titleLabel, 0, 0);
    Label descriptionLabel = new Label("Desc: ");
    grid.add(descriptionLabel, 0, 1);
    Label startLabel = new Label("Start Time: ");
    grid.add(startLabel, 0, 2);
    Label durationLabel = new Label("Duration: ");
    grid.add(durationLabel, 0, 3);
    grid.add(warning, 0, 4);

    grid.add(title, 1, 0);
    GridPane.setHgrow(this.title, Priority.ALWAYS);
    grid.add(description, 1, 1);
    GridPane.setHgrow(this.description, Priority.ALWAYS);
    start.setPromptText("Enter as: HH:mm");
    grid.add(start, 1, 2);
    GridPane.setHgrow(this.start, Priority.ALWAYS);
    duration.setPromptText("Enter as: HH:mm");
    grid.add(duration, 1, 3);
    GridPane.setHgrow(this.duration, Priority.ALWAYS);

    VBox content = new VBox(10);

    content.getChildren().add(grid);

    return content;

  }

  /**
   * returns the Event made
   */
  public void setResultConverter() {
    Callback<ButtonType, Events> eventsResult = new Callback<ButtonType, Events>() {
      @Override
      public Events call(ButtonType param) {
        if (param == ButtonType.FINISH) {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
          LocalTime startTime = LocalTime.parse(start.getText(), formatter);
          LocalTime durationTime = LocalTime.parse(duration.getText(), formatter);

          return new Events(title.getText(), description.getText(), dayEnum,
              startTime, durationTime);
        } else {
          return null;
        }
      }
    };
    setResultConverter(eventsResult);
  }


}
