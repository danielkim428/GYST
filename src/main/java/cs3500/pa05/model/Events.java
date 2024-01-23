package cs3500.pa05.model;

import java.time.LocalTime;

/**
 * Represents an event.
 */
public class Events {

  private String name;
  private String description;
  private DayEnum day;
  private LocalTime startTime;
  private LocalTime duration;

  /**
   * Constructs an event.
   *
   * @param name        the name of the event
   * @param description the description of the event
   * @param day         the day of the event
   * @param startTime   the start time of the event
   * @param duration    the duration of the event
   */
  public Events(String name, String description, DayEnum day, LocalTime startTime,
                LocalTime duration) {
    this.name = name;
    this.description = description;
    this.day = day;
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * gets the event's name
   *
   * @return the event's name
   */
  public String getName() {
    return this.name;
  }

  /**
   * gets the event's day
   *
   * @return the event's day
   */
  public String getDay() {
    return this.day.toString();
  }

  /**
   * gets the event's description
   *
   * @return the event's description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * gets the event's start time
   *
   * @return the event's start time
   */
  public LocalTime getStartTime() {
    return this.startTime;
  }

  /**
   * gets the event's duration time
   *
   * @return the event's duration
   */
  public LocalTime getDuration() {
    return this.duration;
  }
}
