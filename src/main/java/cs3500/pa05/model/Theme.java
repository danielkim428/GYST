package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 * Represents a theme.
 */
public class Theme {
  /**
   * the Theme Builder
   */
  public static class ThemeBuilder {

    private Color backgroundColor;
    private Color fontColor;
    private String fontFamily;
    private List<String> images = new ArrayList<>();

    /**
     * creates the first theme
     *
     * @return the first ThemeBuilder
     */
    public ThemeBuilder theme1() {
      this.backgroundColor = Color.rgb(255, 255, 255);
      this.fontColor = Color.rgb(0, 0, 0);
      this.fontFamily = "Arial";
      this.images.add("newjeansbunny.png");
      this.images.add("rose.png"); // replace
      this.images.add("newjeans.png");
      return this;
    }

    /**
     * creates the second theme
     *
     * @return the second ThemeBuilder
     */
    public ThemeBuilder theme2() {
      this.backgroundColor = Color.rgb(250, 218, 221);
      this.fontColor = Color.rgb(255, 0, 0);
      this.fontFamily = "Lucida Calligraphy";
      this.images.add("ClefairyAmongus.png");
      this.images.add("Loveball.png");
      this.images.add("Sylveon.png");
      return this;
    }

    /**
     * creates the third theme
     *
     * @return the third ThemeBuilder
     */
    public ThemeBuilder theme3() {
      this.backgroundColor = Color.rgb(0, 0, 0);
      this.fontColor = Color.rgb(255, 255, 255);
      this.fontFamily = "Jokerman";
      this.images.add("tomie.png");
      this.images.add("junji_ito.png");
      this.images.add("L.png");
      return this;
    }

    /**
     * generates the default list of themes
     *
     * @return a list of default themes
     */
    public List<Theme> defaultTheme() {
      return List.of(new ThemeBuilder().theme1().build(), new ThemeBuilder().theme2().build(),
          new ThemeBuilder().theme3().build());
    }

    /**
     * sets the background color
     *
     * @param color the given color
     * @return the ThemeBuilder
     */
    public ThemeBuilder setBackgroundColor(Color color) {
      this.backgroundColor = color;
      return this;
    }

    /**
     * sets the font's color
     *
     * @param color the given color
     * @return the ThemeBuilder
     */
    public ThemeBuilder setFontColor(Color color) {
      this.fontColor = color;
      return this;
    }

    /**
     * sets the FontFamily
     *
     * @param fontFamily the given font family
     * @return the ThemeBuilder
     */
    public ThemeBuilder setFontFamily(String fontFamily) {
      this.fontFamily = fontFamily;
      return this;
    }

    /**
     * sets the list of images
     *
     * @param images the list of images to set
     * @return the ThemeBuilder
     */
    public ThemeBuilder setImages(List<String> images) {
      this.images = images;
      return this;
    }

    /**
     * builds the Theme
     *
     * @return the Theme
     */
    public Theme build() {
      return new Theme(backgroundColor, fontColor, fontFamily, images);
    }
  }

  private final Color backgroundColor;
  private final Color fontColor;
  private final String fontFamily;
  private final List<String> images;


  /**
   * Constructs a theme.
   *
   * @param backgroundColor the background color of the theme
   * @param fontColor       the font color of the theme
   * @param fontFamily      the font family of the theme
   * @param images          the images for the theme
   */
  public Theme(Color backgroundColor, Color fontColor, String fontFamily, List<String> images) {
    this.backgroundColor = backgroundColor;
    this.fontColor = fontColor;
    this.fontFamily = fontFamily;
    this.images = images;
  }

  /**
   * get the background color of the theme.
   *
   * @return the background color of the theme
   */
  public Color getBackgroundColor() {
    return this.backgroundColor;
  }

  /**
   * get the font color of the theme.
   *
   * @return the font color of the theme
   */
  public Color getFontColor() {
    return this.fontColor;
  }

  /**
   * get the font family of the theme.
   *
   * @return the font family of the theme
   */
  public String getFontFamily() {
    return this.fontFamily;
  }

  /**
   * get the images of the theme.
   *
   * @return the images of the theme
   */
  public List<String> getImages() {
    // if images is null, then return an empty list
    if (this.images == null) {
      return new ArrayList<>();
    } else {
      return this.images;
    }
  }
}
