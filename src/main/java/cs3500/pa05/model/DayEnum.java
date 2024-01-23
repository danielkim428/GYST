package cs3500.pa05.model;

/**
 * Represents the days of the week.
 */
public enum DayEnum {
  /**
   * Monday
   */
  MONDAY("Monday"),
  /**
   * Tuesday
   */
  TUESDAY("Tuesday"),
  /**
   * Wednesday
   */
  WEDNESDAY("Wednesday"),
  /**
   * Thursday
   */
  THURSDAY("Thursday"),
  /**
   * Friday
   */
  FRIDAY("Friday"),
  /**
   * Saturday
   */
  SATURDAY("Saturday"),
  /**
   * Sunday
   */
  SUNDAY("Sunday");

  /**
   * the label representing the day as String
   */
  public final String label;

  /**
   * @param label the String representing the Day
   */
  DayEnum(String label) {
    this.label = label;
  }
}
