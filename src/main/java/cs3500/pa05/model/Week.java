package cs3500.pa05.model;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a week.
 */
public class Week {

  private String title;
  private Day[] days;
  private ObservableList<Task> taskQueue;
  private ObservableList<Theme> themes;
  private int currentTheme;
  private String notes;
  private StringProperty maxEvents;
  private StringProperty maxTasks;
  private StringProperty weeklyOverview;

  /**
   * constructs a week
   *
   * @param title the week title
   * @param days an array of all the days
   * @param themes a list of the themes
   * @param theme the set theme
   * @param notes the displayed message in quotes and notes
   * @param maxEvents the max number of events
   * @param maxTasks the max number of tasks
   */
  public Week(String title, Day[] days, List<Theme> themes,
              int theme, String notes, int maxEvents, int maxTasks) {
    this.title = title;
    this.days = days;
    this.taskQueue = FXCollections.observableArrayList();
    this.themes = FXCollections.observableList(themes);
    this.currentTheme = theme;
    this.notes = notes;
    this.maxEvents = new SimpleStringProperty(String.valueOf(maxEvents));
    this.maxTasks = new SimpleStringProperty(String.valueOf(maxTasks));

    for (Day day : days) {
      List<Task> tasks = day.getTasks();
      for (Task t : tasks) {
        addTaskQueue(t);
      }
    }
  }

  /**
   * Generates an empty week
   *
   * @param title the title of the week
   * @param theme the displayed default theme
   */
  public Week(String title, int theme) {
    this.title = title;
    this.days = initDays();
    this.taskQueue = FXCollections.observableArrayList();
    this.themes = FXCollections.observableArrayList(new Theme.ThemeBuilder().defaultTheme());
    this.currentTheme = theme;
    this.notes = "";
    this.maxEvents = new SimpleStringProperty("0");
    this.maxTasks = new SimpleStringProperty("0");
  }

  /**
   * sets the week's title
   *
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * gets the week's title
   *
   * @return the week's title
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * initializes the days in a week
   *
   * @return an array of all the days
   */
  private Day[] initDays() {
    Day[] days = new Day[7];
    DayEnum[] dayEnums = new DayEnum[] {DayEnum.SUNDAY, DayEnum.MONDAY, DayEnum.TUESDAY,
        DayEnum.WEDNESDAY, DayEnum.THURSDAY, DayEnum.FRIDAY, DayEnum.SATURDAY};
    for (int i = 0; i < dayEnums.length; i++) {
      days[i] = new Day(dayEnums[i]);
    }
    return days;
  }

  /**
   * adds a task to the task queue
   *
   * @param task the task to add to the task queue
   */
  public void addTaskQueue(Task task) {
    this.taskQueue.add(task);
  }

  /**
   * gets the week's task queue
   *
   * @return the task queue
   */
  public ObservableList<Task> getTaskQueue() {
    return this.taskQueue;
  }

  /**
   * gets the notes in a week
   *
   * @return the message displayed in quotes and notes
   */
  public String getNotes() {
    return this.notes;
  }

  /**
   * updates the quotes and notes
   *
   * @param notes the message to update the Notes to
   */
  public void updateNotes(String notes) {
    this.notes = notes;
  }

  /**
   * Gets the day
   *
   * @param day the day of the week
   * @return the day
   */
  public Day getDay(int day) {
    return this.days[day];
  }

  /**
   * Gets the Theme
   *
   * @return the theme of the week
   */
  public Theme getTheme() {
    return this.themes.get(currentTheme);
  }

  /**
   * gets the max events in a week
   *
   * @return the max number of events in a day
   */
  public int getMaxEvents() {
    return Integer.parseInt(this.maxEvents.get());
  }

  /**
   * gets the max number of tasks
   *
   * @return the max number of tasks in a day
   */
  public int getMaxTasks() {
    return Integer.parseInt(this.maxTasks.get());
  }

  /**
   * gets the max events as a string property
   *
   * @return the max events as a string property
   */
  public StringProperty getMaxEventsStrProp() {
    return this.maxEvents;
  }

  /**
   * gets the max tasks as a string property
   *
   * @return the max tasks as a string property
   */
  public StringProperty getMaxTasksStrProp() {
    return this.maxTasks;
  }

  /**
   * updates this week's theme
   *
   * @param theme the theme to be updated to
   */
  public void updateTheme(int theme) {
    this.currentTheme = theme;
  }

  /**
   * gets this week's current theme
   *
   * @return this week's current theme
   */
  public int getCurrentTheme() {
    return this.currentTheme;
  }

  /**
   * gets the list of themes in a week
   *
   * @return the list of themes in a week
   */
  public ObservableList<Theme> getThemes() {
    return this.themes;
  }

  /**
   * updates the week to a new one
   *
   * @param newWeek the new week to be updated to
   */
  public void update(Week newWeek) {
    this.title = newWeek.getTitle();
    for (int i = 0; i < 7; i++) {
      this.days[i] = newWeek.getDay(i);
    }
    this.taskQueue = FXCollections.observableList(newWeek.getTaskQueue());
    this.themes = newWeek.getThemes();
    this.currentTheme = newWeek.getCurrentTheme();
    this.notes = newWeek.getNotes();
    this.maxEvents = new SimpleStringProperty(String.valueOf(newWeek.getMaxEvents()));
    this.maxTasks = new SimpleStringProperty(String.valueOf(newWeek.getMaxTasks()));
  }

  /**
   * gets the weekly overview stats of a week
   *
   * @return the weekly overview of a week
   */
  public StringProperty getWeeklyOverview() {
    int totalEvents = 0;
    int totalTasks = 0;
    int completedTasks = 0;

    for (Day d : days) {
      totalEvents = totalEvents + d.getEvents().size();
      totalTasks = totalTasks + d.getTasks().size();
    }

    for (Day day : days) {
      List<Task> allTasks = day.getTasks();
      for (Task t : allTasks) {
        if (t.getComplete()) {
          completedTasks++;
        }
      }
    }

    double percentDone = (double) completedTasks / totalTasks;
    percentDone = percentDone * 100;
    Math.floor(percentDone);
    int percentage = (int) percentDone;

    String overview = "Total Events: " + totalEvents + "\n"
        + "Total Tasks: " + totalTasks + "\n"
        + "Completion: " + percentage + "%";

    weeklyOverview = new SimpleStringProperty(overview);

    return weeklyOverview;
  }

  /**
   * sets the max events
   *
   * @param newMax the new max events to be set to
   */
  public void setMaxEvents(int newMax) {
    this.maxEvents.set(String.valueOf(newMax));
  }

  /**
   * sets the max tasks
   *
   * @param newMax the new max tasks to be set to
   */
  public void setMaxTasks(int newMax) {
    this.maxTasks.set(String.valueOf(newMax));
  }

  /**
   * Resets every task and event and notes
   */
  public void setTemplate() {
    days = initDays();
    this.taskQueue = FXCollections.observableArrayList();
    this.notes = "";
  }
}
