package cs3500.pa05.viewer;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

/**
 * the max view
 */
public class MaxView extends Label {
  private String prefix;
  private int current;
  private StringProperty max;

  /**
   * constructs a new max view
   *
   * @param prefix the String prefix
   * @param current the amount of currently done tasks or events
   * @param max the max amount of tasks or events
   */
  public MaxView(String prefix, int current, StringProperty max) {
    this.prefix = prefix;
    this.current = current;
    this.max = max;
    this.setText(show(max.get()));
    max.addListener((obs, old, newVal) -> {
      this.setText(show(max.get()));
    });
  }

  /**
   * subtracts from the current amount of tasks
   */
  public void subtract() {
    this.current -= 1;
    this.setText(show(max.get()));
  }

  /**
   * formats the number of tasks and events
   *
   * @param max the number of max events
   * @return a String detailing the current amount of tasks done and the max
   */
  private String show(String max) {
    if (current > Integer.parseInt(max)) {
      return prefix + current + "/" + max + " Warning!";
    } else {
      return prefix + current + "/" + max;
    }
  }
}
