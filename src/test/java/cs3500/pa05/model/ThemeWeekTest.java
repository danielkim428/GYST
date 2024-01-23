package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Theme class.
 */
public class ThemeWeekTest {

  private Theme theme;

  private Week week2;
  private Task task1;
  private Theme theme1;
  private Theme theme2;

  /**
   * creates the days
   *
   * @return an Array of days
   */
  private Day[] createDays() {
    Day[] days = new Day[7];
    DayEnum[] dayEnums = new DayEnum[] {DayEnum.SUNDAY, DayEnum.MONDAY, DayEnum.TUESDAY,
        DayEnum.WEDNESDAY, DayEnum.THURSDAY, DayEnum.FRIDAY, DayEnum.SATURDAY};
    for (int i = 0; i < dayEnums.length; i++) {
      days[i] = new Day(dayEnums[i]);
    }
    return days;
  }

  /**
   * setup before each test
   */
  @BeforeEach
  public void setup() {
    Day[] days = createDays();
    task1 = new Task("Finish Testing", DayEnum.SUNDAY);
    days[0].addTask(task1);
    theme1 = new Theme(Color.AQUA, Color.GREEN, "Times New Roman", new ArrayList<>());
    theme2 = new Theme(Color.RED, Color.GREEN, "Arial", new ArrayList<>());
    week2 = new Week("Worst Week", days, new ArrayList<>(Arrays.asList(theme1, theme2)),
        0, "hello", 1, 1);
    Color backgroundColor = Color.rgb(255, 255, 255);
    Color fontColor = Color.rgb(0, 0, 0);
    String fontFamily = "Arial";
    List<String> images = new ArrayList<>();
    theme = new Theme(backgroundColor, fontColor, fontFamily, images);
  }

  /**
   * tests setting the title
   */
  @Test
  public void testSetTitle() {

    String newTitle = "new!";
    assertNotEquals(week2.getTitle(), newTitle);
    week2.setTitle(newTitle);
    assertEquals(week2.getTitle(), newTitle);
  }

  /**
   * tests getting the title
   */
  @Test
  public void testGetTitle() {
    assertEquals(week2.getTitle(), "Worst Week");
  }

  /**
   * tests init days
   */
  @Test
  public void testInitDays() {
    assertDoesNotThrow(() -> new Week("Test", 4));
  }

  /**
   * tests adding to a task queue
   */
  @Test
  public void testAddTaskQueue() {
    Task newTask = new Task("PA5", DayEnum.MONDAY);
    week2.addTaskQueue(newTask);
    ObservableList<Task> taskQueueTwo = FXCollections.observableArrayList();
    taskQueueTwo.add(task1);
    taskQueueTwo.add(newTask);
    assertEquals(week2.getTaskQueue(), taskQueueTwo);
  }

  /**
   * gets the task queue
   */
  @Test
  public void testGetTaskQueue() {
    Task newTask = new Task("PA5", DayEnum.MONDAY);
    week2.addTaskQueue(newTask);
    ObservableList<Task> taskQueueTwo = FXCollections.observableArrayList();
    taskQueueTwo.add(task1);
    taskQueueTwo.add(newTask);
    assertEquals(week2.getTaskQueue(), taskQueueTwo);
  }

  /**
   * test for getting the notes
   */
  @Test
  public void testGetNotes() {
    assertEquals(week2.getNotes(), "hello");
  }

  /**
   * tests updating the notes
   */
  @Test
  public void testUpdateNotes() {
    assertEquals(week2.getNotes(), "hello");
    week2.updateNotes("Fontenot");
    assertEquals(week2.getNotes(), "Fontenot");
  }

  /**
   * tests getting the day
   */
  @Test
  public void testGetDay() {
    Day sunday = new Day(DayEnum.SUNDAY);
    sunday.addTask(task1);
    assertEquals(week2.getDay(0), sunday);
    assertEquals(week2.getDay(1), new Day(DayEnum.MONDAY));
    assertEquals(week2.getDay(2), new Day(DayEnum.TUESDAY));
    assertEquals(week2.getDay(3), new Day(DayEnum.WEDNESDAY));
    assertEquals(week2.getDay(4), new Day(DayEnum.THURSDAY));
    assertEquals(week2.getDay(5), new Day(DayEnum.FRIDAY));
    assertEquals(week2.getDay(6), new Day(DayEnum.SATURDAY));
  }

  /**
   * tests getting the theme
   */
  @Test
  public void testGetTheme() {
    assertEquals(week2.getTheme(), theme1);
  }

  /**
   * tests getting the max events
   */
  @Test
  public void testGetMaxEvents() {
    assertEquals(week2.getMaxEvents(), 1);
  }

  /**
   * tests getting the max tasks
   */
  @Test
  public void testGetMaxTasks() {
    assertEquals(week2.getMaxTasks(), 1);
  }

  /**
   * tests getting the themes
   */
  @Test
  public void testGetThemes() {
    assertEquals(week2.getThemes(), new ArrayList<>(Arrays.asList(theme1, theme2)));
  }

  /**
   * tests getting the max events string property
   */
  @Test
  public void testMaxEventsStringProperty() {
    StringProperty stringProp = new SimpleStringProperty("1");
    assertEquals(week2.getMaxEventsStrProp().toString(), stringProp.toString());
  }

  /**
   * test for getting the max tasks string property
   */
  @Test
  public void testMaxTasksStringProperty() {
    StringProperty stringProp = new SimpleStringProperty("1");
    assertEquals(week2.getMaxTasksStrProp().toString(), stringProp.toString());
  }

  /**
   * tests updating the theme
   */
  @Test
  public void testUpdateTheme() {
    assertEquals(week2.getTheme(), theme1);
    week2.updateTheme(1);
    assertEquals(week2.getTheme(), theme2);
  }

  /**
   * gets the current theme
   */
  @Test
  public void testGetCurrentTheme() {
    assertEquals(week2.getCurrentTheme(), 0);
    week2.updateTheme(1);
    assertEquals(week2.getCurrentTheme(), 1);
  }

  /**
   * tests updating the week
   */
  @Test
  public void testUpdateWeek() {
    Day[] newDays = createDays();
    Week week3 = new Week("ITS LIT", newDays,
        new ArrayList<>(Collections.singletonList(theme)), 0, "NEW WEEK", 15, 15);
    week2.update(week3);
    System.out.println(week2.getTitle());
    assertEquals(week2.getTitle(), "ITS LIT");
    assertEquals(week2.getDay(0), newDays[0]);
    assertEquals(week2.getDay(1), newDays[1]);
    assertEquals(week2.getDay(2), newDays[2]);
    assertEquals(week2.getDay(3), newDays[3]);
    assertEquals(week2.getDay(4), newDays[4]);
    assertEquals(week2.getDay(5), newDays[5]);
    assertEquals(week2.getDay(6), newDays[6]);
    assertEquals(week2.getThemes(), week3.getThemes());
    assertEquals(week2.getCurrentTheme(), week3.getCurrentTheme());
    assertEquals(week2.getNotes(), week3.getNotes());
    assertEquals(week2.getMaxTasks(), week3.getMaxTasks());
    assertEquals(week2.getMaxEvents(), week3.getMaxEvents());
  }

  /**
   * tests weekly overview
   */
  @Test
  public void testWeeklyOverview() {
    String overview = "Total Events: 0\nTotal Tasks: 1\nCompletion: 0%";
    StringProperty weeklyOverview = new SimpleStringProperty(overview);
    assertEquals(week2.getWeeklyOverview().toString(), weeklyOverview.toString());
    List<Task> listOfTasks = week2.getDay(0).getTasks();
    Task t = listOfTasks.get(0);
    t.changeComplete();

    String overview2 = "Total Events: 0\nTotal Tasks: 1\nCompletion: 100%";
    StringProperty weeklyOverview2 = new SimpleStringProperty(overview2);
    assertEquals(week2.getWeeklyOverview().toString(), weeklyOverview2.toString());

  }

  /**
   * test for setting the max events
   */
  @Test
  public void setMaxEvents() {
    assertEquals(week2.getMaxEvents(), 1);
    week2.setMaxEvents(5);
    assertEquals(week2.getMaxEvents(), 5);
  }

  /**
   * sets the max tasks
   */
  @Test
  public void setMaxTasks() {
    assertEquals(week2.getMaxTasks(), 1);
    week2.setMaxTasks(100);
    assertEquals(week2.getMaxTasks(), 100);
  }

  /**
   * test for setting the template
   */
  @Test
  public void testSetTemplate() {
    assertEquals(week2.getTaskQueue().size(), 1);
    assertEquals(week2.getNotes(), "hello");
    Day sunday = new Day(DayEnum.SUNDAY);
    sunday.addTask(task1);
    assertEquals(week2.getDay(0), sunday);
    week2.setTemplate();
    assertEquals(week2.getTaskQueue().size(), 0);
    assertEquals(week2.getNotes(), "");
    assertEquals(week2.getDay(0), new Day(DayEnum.SUNDAY));
  }

  /**
   * Test to get bg color
   */
  @Test
  public void testGetBackgroundColor() {
    Color backgroundColor = theme.getBackgroundColor();
    assertEquals(Color.rgb(255, 255, 255), backgroundColor);
  }

  /**
   * Test to get font color
   */
  @Test
  public void testGetFontColor() {
    Color fontColor = theme.getFontColor();
    assertEquals(Color.rgb(0, 0, 0), fontColor);
  }

  /**
   * Tests for font family
   */
  @Test
  public void testGetFontFamily() {
    String fontFamily = theme.getFontFamily();
    assertEquals("Arial", fontFamily);
  }

  /**
   * Test for theme 1
   */
  @Test
  public void testTheme1() {
    Theme.ThemeBuilder builder = new Theme.ThemeBuilder();
    Theme theme = builder.theme1().build();

    Assertions.assertEquals(Color.rgb(255, 255, 255), theme.getBackgroundColor());
    Assertions.assertEquals(Color.rgb(0, 0, 0), theme.getFontColor());
    Assertions.assertEquals("Arial", theme.getFontFamily());
    //Assertions.assertEquals(3, theme.getImages().size());
    // Add more assertions if needed
  }

  /**
   * Test for theme 2
   */
  @Test
  public void testTheme2() {
    Theme.ThemeBuilder builder = new Theme.ThemeBuilder();
    Theme theme = builder.theme2().build();

    Assertions.assertEquals(Color.rgb(250, 218, 221), theme.getBackgroundColor());
    Assertions.assertEquals(Color.rgb(255, 0, 0), theme.getFontColor());
    Assertions.assertEquals("Lucida Calligraphy", theme.getFontFamily());
    //Assertions.assertEquals(3, theme.getImages().size());
    // Add more assertions if needed
  }

  /**
   * Test for theme 3
   */
  @Test
  public void testTheme3() {
    Theme.ThemeBuilder builder = new Theme.ThemeBuilder();
    Theme theme = builder.theme3().build();

    Assertions.assertEquals(Color.rgb(0, 0, 0), theme.getBackgroundColor());
    Assertions.assertEquals(Color.rgb(255, 255, 255), theme.getFontColor());
    Assertions.assertEquals("Jokerman", theme.getFontFamily());
    //Assertions.assertEquals(3, theme.getImages().size());
    // Add more assertions if needed
  }

  /**
   * Test to see if it returns default theme
   */
  @Test
  public void testDefaultTheme() {
    Theme.ThemeBuilder builder = new Theme.ThemeBuilder();
    List<Theme> themes = builder.defaultTheme();

    Assertions.assertEquals(3, themes.size());
    // Add assertions to verify the properties of each theme
  }

  /**
   * Test if setting background color works
   */
  @Test
  public void testSetBackgroundColor() {
    Theme.ThemeBuilder builder = new Theme.ThemeBuilder();
    Color backgroundColor = Color.rgb(123, 45, 67);
    Theme theme = builder.setBackgroundColor(backgroundColor).build();

    Assertions.assertEquals(backgroundColor, theme.getBackgroundColor());
  }

  /**
   * Test if set font color works
   */
  @Test
  public void testSetFontColor() {
    Theme.ThemeBuilder builder = new Theme.ThemeBuilder();
    Color fontColor = Color.rgb(12, 34, 56);
    Theme theme = builder.setFontColor(fontColor).build();

    Assertions.assertEquals(fontColor, theme.getFontColor());
  }

  /**
   * Test if set font family works
   */
  @Test
  public void testSetFontFamily() {
    Theme.ThemeBuilder builder = new Theme.ThemeBuilder();
    String fontFamily = "Verdana";
    Theme theme = builder.setFontFamily(fontFamily).build();

    Assertions.assertEquals(fontFamily, theme.getFontFamily());
  }

  /**
   * Test if get images work
   */
  @Test
  public void testGetImages() {
    // Create a list of images
    List<String> images = new ArrayList<>();
    images.add("L.png");
    images.add("Loveball.png");

    // Create a theme with the list of images
    Theme theme = new Theme(Color.WHITE, Color.BLACK, "Arial", images);

    // Retrieve the images from the theme
    List<String> retrievedImages = theme.getImages();

    // Assert that the retrieved images match the original images
    Assertions.assertEquals(images, retrievedImages);
  }

  /**
   * Test if we can get null images
   */
  @Test
  public void testGetImagesNull() {
    // Create a theme with a null list of images
    Theme theme = new Theme(Color.WHITE, Color.BLACK, "Arial", null);

    // Retrieve the images from the theme
    List<String> retrievedImages = theme.getImages();

    // Assert that the retrieved images are an empty list
    Assertions.assertEquals(Collections.emptyList(), retrievedImages);
  }

  /**
   * Test the theme builder
   */
  @Test
  public void testSetImagesThemeBuilder() {
    // Create a list of images
    List<String> images = new ArrayList<>();
    images.add("L.png");
    images.add("Loveball.png");

    // Create a theme using the ThemeBuilder
    Theme theme = new Theme.ThemeBuilder()
        .setImages(images)
        .build();

    // Retrieve the images from the theme
    List<String> retrievedImages = theme.getImages();

    // Assert that the retrieved images match the original images
    Assertions.assertEquals(images, retrievedImages);
  }
}
