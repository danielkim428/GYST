package cs3500.pa05.viewer;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.CreateEventHandler;
import cs3500.pa05.controller.CreateTaskHandler;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Theme;
import cs3500.pa05.model.Week;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * a dialog box for choosing between a new event or task
 */
public class ChooseNewDialog extends Dialog {
  private Day day;
  private Week week;
  private Controller controller;

  /**
   * constructs a new ChooseNewDialog
   *
   * @param day the given day
   * @param week the given week
   * @param controller the journal controller
   */
  public ChooseNewDialog(Day day, Week week, Controller controller) {
    super();
    this.setTitle("Choose Which One To Create");
    this.day = day;
    this.week = week;
    this.controller = controller;
    buildPrompt();
  }

  /**
   * builds the prompt for the user
   */
  private void buildPrompt() {
    Pane pane = buildDialog();
    getDialogPane().setContent(pane);

    getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
  }

  /**
   * builds the Dialog
   *
   * @return a Pane representing the choices
   */
  private Pane buildDialog() {

    GridPane grid = new GridPane();
    grid.setPrefSize(400, 200);
    grid.setHgap(10);
    grid.setVgap(25);
    grid.setAlignment(Pos.CENTER);
    VBox vertBox = new VBox(20);
    vertBox.setAlignment(Pos.CENTER);
    vertBox.setPrefSize(100, 50);
    String style = "-fx-border-radius: 50px; -fx-border-color: black;"
        + "-fx-background-radius: 50px; "
        + "-fx-background-color: transparent; "
        + "-fx-pref-height: 40; -fx-pref-width: 100;";
    Button newEvent = new Button("New Event");
    newEvent.setStyle(style);
    newEvent.setAlignment(Pos.CENTER);
    Button newTask = new Button("New Task");
    newTask.setStyle(style);
    newTask.setAlignment(Pos.CENTER);
    grid.add(newEvent, 1, 0);
    grid.add(newTask, 1, 1);
    newEvent.setOnAction(new CreateEventHandler(day.getEvents(), week, day, controller, this));
    newTask.setOnAction(new CreateTaskHandler(day.getTasks(), week, day, controller, this));
    Theme currentTheme = week.getTheme();
    getDialogPane().setStyle("-fx-background-color: #FFE2E4");

    VBox content = new VBox(10);

    content.getChildren().add(grid);

    return content;
  }
}

