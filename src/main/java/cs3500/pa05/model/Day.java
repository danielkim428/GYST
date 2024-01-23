package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a day in the week.
 */
public class Day {

  private DayEnum day;
  private List<Events> events;
  private List<Task> tasks;

  /**
   * Constructs a day.
   *
   * @param day    the day of the week
   * @param events the list of events
   * @param tasks  the list of tasks
   */
  public Day(DayEnum day, List<Events> events, List<Task> tasks) {
    this.day = day;
    this.events = events;
    this.tasks = tasks;
  }

  /**
   * constructs a new Day with an empty list of tasks and events on the specificed day
   *
   * @param day the specificed day in the week
   */
  public Day(DayEnum day) {
    this.day = day;
    this.events = new ArrayList<>();
    this.tasks = new ArrayList<>();
  }

  /**
   * adds an event
   *
   * @param events the event to add
   */
  public void addEvent(Events events) {
    this.events.add(events);
  }

  /**
   * adds a task to the list of tasks.
   *
   * @param task the task to add
   */
  public void addTask(Task task) {
    this.tasks.add(task);
  }

  /**
   * gets the Day
   *
   * @return the day
   */
  public String getDay() {
    return this.day.toString();
  }

  /**
   * get Events
   *
   * @return the list of events
   */
  public List<Events> getEvents() {
    return this.events;
  }

  /**
   * get Tasks
   *
   * @return the list of tasks
   */
  public List<Task> getTasks() {
    return this.tasks;
  }

  /**
   * removes a task if it's found
   *
   * @param t the Task to remove
   */
  public void removeIfFound(Task t) {
    this.tasks.removeIf(task -> task == t);
  }

  /**
   * removes an Event if found
   *
   * @param e the Events to remove
   */
  public void removeIfFound(Events e) {
    this.events.removeIf(ev -> ev == e);
  }

  /**
   * compares a day and an object to see if they are equal
   *
   * @param obj the object to compare to
   * @return boolean on whether two days are equal
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Day day)) {
      return false;
    }
    return day.getDay().equals(this.getDay())
        && day.getEvents().equals(this.getEvents())
        && day.getTasks().equals(this.getTasks());
  }
}
