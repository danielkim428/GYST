package cs3500.pa05.viewer;

import cs3500.pa05.model.Theme;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * a CustomThemeDialog
 */
public class CustomThemeDialog extends Dialog<Theme> {

  private Theme theme;
  private ColorPicker backgroundColorPicker;
  private ColorPicker fontColorPicker;
  private TextField fontFamilyTextField;
  private ListView<File> imageListView;

  /**
   * constructs a new CustomThemeDialog
   */
  public CustomThemeDialog() {
    super();
    this.setTitle("Customize Theme");

    buildUi();
    setResultConverter();
  }

  /**
   * builds the CustomTheme UI
   */
  private void buildUi() {

    backgroundColorPicker = new ColorPicker();
    fontColorPicker = new ColorPicker();
    fontFamilyTextField = new TextField();
    imageListView = new ListView<>();

    VBox content = new VBox(10);

    content.getChildren().addAll(
        createColorPickerItem("Background Color:", backgroundColorPicker),
        createColorPickerItem("Font Color:", fontColorPicker),
        createTextFieldItem("Font Family:", fontFamilyTextField),
        createImagePickerItem("Image Bottom Right:", imageListView),
        createImagePickerItem("Image Quotes and Notes:", imageListView),
        createImagePickerItem("Image Top Left:", imageListView)
    );

    getDialogPane().setContent(content);

    getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
  }

  /**
   * creates the Custom Color picker
   *
   * @param label the label of the picker
   * @param colorPicker the color picker
   * @return a VBox containing the color picker
   */
  private VBox createColorPickerItem(String label, ColorPicker colorPicker) {
    VBox item = new VBox(5);

    item.getChildren().addAll(
        new javafx.scene.control.Label(label),
        colorPicker
    );

    return item;
  }

  /**
   * creates the text fields item
   *
   * @param label the title of the text field
   * @param textField the text field
   * @return a VBox containing the textfield
   */
  private VBox createTextFieldItem(String label, TextField textField) {
    VBox item = new VBox(5);

    item.getChildren().addAll(
        new javafx.scene.control.Label(label),
        textField
    );

    return item;
  }

  /**
   * creates the image picker
   *
   * @param label the title of the label
   * @param listView the list view of different images
   * @return a VBox containing the image picker
   */
  private VBox createImagePickerItem(String label, ListView<File> listView) {
    VBox item = new VBox(5);
    item.setPrefHeight(120);

    item.getChildren().addAll(
        new javafx.scene.control.Label(label),
        listView
    );

    Button importButton = new Button("Import");
    importButton.setOnAction(event -> {
      Stage fileChooserStage = (Stage) getDialogPane().getScene().getWindow();

      FileChooser fileChooser = new FileChooser();
      fileChooser.getExtensionFilters().addAll(
          new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
          new ExtensionFilter("All Files", "*.*")
      );

      List<File> selectedFiles = fileChooser.showOpenMultipleDialog(fileChooserStage);
      if (selectedFiles != null) {
        ObservableList<File> fileList = FXCollections.observableArrayList(selectedFiles);
        listView.getItems().add(fileList.get(0));
      }
    });

    item.getChildren().add(importButton);

    return item;
  }

  /**
   * sets the result converter
   */
  private void setResultConverter() {
    setResultConverter(buttonType -> {
      if (buttonType == ButtonType.OK) {
        Theme theme = new Theme.ThemeBuilder()
            .setBackgroundColor(backgroundColorPicker.getValue())
            .setFontColor(fontColorPicker.getValue())
            .setFontFamily(fontFamilyTextField.getText())
            .setImages(convertFilesToImages(imageListView.getItems())).build();
        System.out.println(theme);
        return theme;
      } else {
        return null;
      }
    });
  }

  /**
   * converts a list of files into a list of images
   *
   * @param files the list of files to convert to images
   * @return a List of images
   */
  public List<String> convertFilesToImages(List<File> files) {
    List<String> images = new ArrayList<>();

    for (File file : files) {
      try {
        String image = file.toURI().toString();
        images.add(image);
      } catch (Exception e) {
        // Handle any exceptions that may occur during image loading
        // For example, you can log the error or display an error message
        e.printStackTrace();
      }
    }

    return images;
  }
}
