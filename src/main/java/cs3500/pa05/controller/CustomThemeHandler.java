package cs3500.pa05.controller;

import cs3500.pa05.model.Theme;
import cs3500.pa05.viewer.CustomThemeDialog;
import java.util.List;
import java.util.Optional;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Dialog;

/**
 * the Custom Theme handler
 */
public class CustomThemeHandler implements EventHandler {
  private List<Theme> themes;

  /**
   * constructs a new Custom Theme Builder
   *
   * @param themes the list of themes
   */
  public CustomThemeHandler(List<Theme> themes) {
    this.themes = themes;
  }

  /**
   * handles when the Custom Theme button is chosen
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    Dialog popup = new CustomThemeDialog();

    Optional<Theme> result = popup.showAndWait();

    result.ifPresent((Theme theme) -> {
      themes.add(theme);
    });

  }
}
