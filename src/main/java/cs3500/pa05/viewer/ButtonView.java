package cs3500.pa05.viewer;

import cs3500.pa05.controller.ChooseNewHandler;
import cs3500.pa05.controller.Controller;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Week;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * the view for buttons
 */
public class ButtonView extends BlockView {
  /**
   * constructs a button view
   *
   * @param day the day in the week
   * @param week the given week
   * @param controller the journal controller
   */
  public ButtonView(Day day, Week week, Controller controller) {
    super();
    HBox addEventContainer = new HBox();
    addEventContainer.setAlignment(Pos.TOP_CENTER);
    addEventContainer.setMinWidth(110);
    addEventContainer.setMinHeight(110);

    Button addEvent = new Button("+");
    addEvent.setAlignment(Pos.CENTER);
    addEvent.setMaxHeight(Double.MAX_VALUE);
    addEvent.setMaxWidth(Double.MAX_VALUE);
    addEvent.setOnAction(new ChooseNewHandler(day, week, controller));

    addEventContainer.getChildren().add(addEvent);
    HBox.setHgrow(addEvent, Priority.ALWAYS);
    this.getChildren().add(addEventContainer);
  }
}
