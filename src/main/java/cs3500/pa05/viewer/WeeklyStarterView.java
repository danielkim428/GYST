package cs3500.pa05.viewer;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.controller.JsonConverter;
import cs3500.pa05.controller.reader.BujoReader;
import cs3500.pa05.controller.writer.BujoCreater;
import cs3500.pa05.controller.writer.BujoWriter;
import cs3500.pa05.model.Week;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;

/**
 * Weekly Starter View to pick template
 */
public class WeeklyStarterView extends Dialog {
  Scene scene;
  File resultFile;

  /**
   * Instantitates the WeeklyStarterView
   *
   * @param scene Scene to display file picker
   */
  public WeeklyStarterView(Scene scene) {
    super();
    this.scene = scene;
    buildUi();
    setResultConverter();
  }

  /**
   * Build visualization
   */
  private void buildUi() {
    Pane pane = new Pane();
    pane.setPrefSize(500, 200);
    VBox vertBox = new VBox(20);
    vertBox.setPrefSize(500, 200);
    vertBox.setAlignment(Pos.CENTER);
    TextField weekTitle = new TextField();
    weekTitle.setPromptText("Name of the week:");
    weekTitle.setMaxWidth(200);
    String style = "-fx-border-radius: 50px; -fx-border-color: black;"
        + "-fx-background-radius: 50px; "
        + "-fx-background-color: transparent; "
        + "-fx-pref-height: 40; -fx-pref-width: 100;";
    Button openBtn = new Button("Open Journal");
    openBtn.setStyle(style);
    openBtn.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      FileChooser.ExtensionFilter bujoFilter =
          new FileChooser.ExtensionFilter("Bujo Files", "*.bujo");
      fileChooser.getExtensionFilters().add(bujoFilter);
      File file = fileChooser.showOpenDialog(scene.getWindow());
      if (file != null) {
        File newFile = setTemplate(file, weekTitle.getText());
        resultFile = newFile;
      }
    });
    vertBox.getChildren().addAll(weekTitle, openBtn);
    pane.getChildren().add(vertBox);
    getDialogPane().setContent(pane);
    getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
    getDialogPane().setStyle("-fx-background-color: #FFE2E4");
  }

  /**
   * Set template file
   *
   * @param file  file to overwrite
   * @param title new title to give
   * @return  newly created file
   */
  private File setTemplate(File file, String title) {
    JsonNode jsonNode = new BujoReader().read(file);
    File dir = file.getParentFile();
    File result = new File(dir, title + ".bujo");
    new BujoCreater().createFile(result);
    Week week = JsonConverter.convertJsonToWeek(jsonNode);
    week.setTitle(title);
    week.setTemplate();
    JsonNode newJsonNode = JsonConverter.convertWeekToJson(week);
    new BujoWriter().write(result, newJsonNode);

    return result;
  }

  /**
   * sets the result converter to the resulting file
   */
  public void setResultConverter() {
    Callback<ButtonType, File> fileResult = param -> {
      if (param == ButtonType.APPLY) {
        return resultFile;
      } else {
        return null;
      }
    };
    setResultConverter(fileResult);
  }
}
