package cs3500.pa05.model;

/**
 * Represents a task.
 */
public class Task {

  private String name;
  private String description;
  private DayEnum day;
  private Boolean complete;

  /**
   * Creates a task
   *
   * @param name        the name of the task
   * @param description the task description
   * @param day         the day the task is on
   * @param complete    whether the task is completed
   */
  public Task(String name, String description, DayEnum day, Boolean complete) {
    this.name = name;
    this.description = description;
    this.day = day;
    this.complete = complete;
  }

  /**
   * constructs a task
   *
   * @param name the name of the task
   * @param day  the day to place the task
   */
  public Task(String name, DayEnum day) {
    this.name = name;
    this.description = "";
    this.day = day;
    this.complete = false;
  }

  /**
   * converts a task to a String
   *
   * @return the Task as a String
   */
  @Override
  public String toString() {
    return this.name + ", Completed: " + this.complete;
  }

  /**
   * flips the complete boolean
   */
  public void changeComplete() {
    this.complete = !this.complete;
  }

  /**
   * gets the task's name
   *
   * @return the task's name
   */
  public String getName() {
    return this.name;
  }

  /**
   * gets the task's day
   *
   * @return the task's day
   */
  public String getDay() {
    return this.day.toString();
  }

  /**
   * gets the task's description
   *
   * @return the task's description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * gets the task's completion status
   *
   * @return the completion status of the task
   */
  public Boolean getComplete() {
    return this.complete;
  }

  /**
   * compares the equality of two objects
   *
   * @param obj the object to compare to
   * @return a boolean on whether this object is equal to the given object
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Task t)) {
      return false;
    }

    return this.name.equals(t.name) && this.description.equals(t.description)
        && this.getDay().equals(t.getDay()) && this.complete == t.complete;
  }

  /**
   * generates the hashcode of the task
   *
   * @return the hashcode of the task
   */
  @Override
  public int hashCode() {
    int boolComplete = 0;
    if (this.complete) {
      boolComplete = 1;
    }
    int dayNumber = 0;
    switch (this.day) {
      case SATURDAY -> dayNumber = 6;
      case FRIDAY -> dayNumber = 5;
      case THURSDAY -> dayNumber = 4;
      case WEDNESDAY -> dayNumber = 3;
      case TUESDAY -> dayNumber = 2;
      case MONDAY -> dayNumber = 1;
      default -> dayNumber = 0;
    }
    return this.name.hashCode() * 10000 + this.description.hashCode() * 1000
        + dayNumber * 10 + boolComplete;
  }
}
