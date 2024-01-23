package cs3500.pa05.viewer;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * ReCaptcha for Lock Privacy
 */
public class ReCaptcha extends Dialog {
  private TextField textField;

  /**
   * Instantiates ReCaptcha class
   */
  public ReCaptcha() {
    super();
    this.setTitle("ReCaptcha");
    buildUi();
    setResultConverter();
  }

  /**
   * Build visuals
   */
  private void buildUi() {
    VBox vertBox = new VBox();
    vertBox.setPrefSize(400, 200);
    ImageView captchaView = new ImageView();
    Image captcha = new Image("marckhdietcoke.png");
    captchaView.setImage(captcha);
    captchaView.setFitWidth(400);
    captchaView.setFitHeight(100);
    HBox horiBox = new HBox(10);
    horiBox.setPadding(new Insets(10));
    textField = new TextField();
    textField.setPromptText("Type the two words:");
    ImageView imageView = new ImageView();
    Image image = new Image("recaptcha.png");
    imageView.setImage(image);
    imageView.setFitWidth(200);
    imageView.setFitHeight(80);
    ImageView mark = new ImageView();
    Image markFontenot = new Image("marck.png");
    mark.setImage(markFontenot);
    ImageView mark2 = new ImageView(markFontenot);
    horiBox.getChildren().addAll(textField, imageView);
    vertBox.getChildren().addAll(captchaView, horiBox, mark, mark2);
    getDialogPane().setContent(vertBox);
    getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);
    getDialogPane().setStyle("-fx-background-color: #DBDADA");
  }

  /**
   * sets the result converter to the resulting file
   */
  public void setResultConverter() {
    Callback<ButtonType, String> fileResult = new Callback<ButtonType, String>() {
      @Override
      public String call(ButtonType param) {
        if (param == ButtonType.FINISH) {
          return textField.getText();
        } else {
          return null;
        }
      }
    };
    setResultConverter(fileResult);
  }
}
