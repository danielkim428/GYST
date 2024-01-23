package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.JournalController;
import cs3500.pa05.model.Week;
import cs3500.pa05.viewer.Viewer;
import cs3500.pa05.viewer.WeekView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Represents a driver for the journal.
 */
public class Driver extends Application {
  /**
   * starts the program
   *
   * @param stage the primary stage for this application, onto which
   *              the application scene can be set.
   *              Applications may create other stages, if needed, but they will not be
   *              primary stages.
   */
  @Override
  public void start(Stage stage) {
    Week week = new Week("", 0);
    Controller controller = new JournalController(week);
    Viewer viewer = new WeekView(controller);

    try {
      stage.setScene(viewer.load());
      stage.setTitle("journal");
      stage.setResizable(false);
      controller.initialize();
      stage.show();
    } catch (IllegalStateException e) {
      e.printStackTrace();
      System.err.println("Unable to load GUI.");
    }
  }

  /**
   * Main Method
   *
   * @param args cmd-line arguments
   */
  public static void main(String[] args) {
    launch();
  }
}
