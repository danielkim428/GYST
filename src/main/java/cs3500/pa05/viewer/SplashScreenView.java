package cs3500.pa05.viewer;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * SplashScreenView class dialog popup
 */
public class SplashScreenView extends Dialog {
  /**
   * Initialize the splash screen view;
   */
  public SplashScreenView() {
    super();
    this.setTitle("Welcome!");
    buildUi();
  }

  /**
   * build the User Interface for the splash screen
   */
  private void buildUi() {
    StackPane pane = new StackPane();
    pane.setPrefSize(600, 400);
    pane.setAlignment(Pos.TOP_CENTER);
    ImageView imageView = new ImageView();
    Image image = new Image("backgrounddd.png");
    imageView.setImage(image);
    imageView.setFitWidth(600);
    imageView.setFitHeight(400);
    Label names = new Label("Sunkwan, Brooklyn, Alidar");
    names.setStyle("-fx-font-family: Arial; -fx-font-size: 20px; -fx-font-weight: BOLD;");
    names.setPadding(new Insets(20));
    pane.getChildren().addAll(imageView, names);
    getDialogPane().setContent(pane);
    getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
    Button closeButton = (Button) getDialogPane().lookupButton(ButtonType.CANCEL);
    closeButton.setVisible(false);
    getDialogPane().setStyle("-fx-background-color: #FFE2E4");
    PauseTransition delay = new PauseTransition(Duration.seconds(2));
    delay.setOnFinished(event -> this.close());
    delay.play();
  }
}
