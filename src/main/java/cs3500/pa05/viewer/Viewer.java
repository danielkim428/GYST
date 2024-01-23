package cs3500.pa05.viewer;

import javafx.scene.Scene;

/**
 * a Viewer interface
 */
public interface Viewer {
  /**
   * loads the Scene
   *
   * @return the scene to load
   * @throws IllegalStateException if the scene cannot be loaded
   */
  Scene load() throws IllegalStateException;
}
