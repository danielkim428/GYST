package cs3500.pa05.viewer;

import cs3500.pa05.controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * a WeekView
 */
public class WeekView implements Viewer {
  private FXMLLoader loader;

  /**
   * constructs a new WeekView
   *
   * @param controller the journal controller
   */
  public WeekView(Controller controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("weekView.fxml"));
    this.loader.setController(controller);
  }

  /**
   * loads the Scene
   *
   * @return the loaded Scene
   * @throws IllegalStateException if the Scene cannot be loaded
   */
  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      exc.printStackTrace();
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
