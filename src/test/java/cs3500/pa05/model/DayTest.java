package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayEnum;
import cs3500.pa05.model.Events;
import cs3500.pa05.model.Task;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * Tests for the Day class.
 */
public class DayTest {

  private Day day;

  @BeforeEach
  void setUp() {
    day = new Day(DayEnum.MONDAY);
  }

  @Test
  void addEvent() {
    // Test adding an event
    Events
        event = new Events("Meeting", "Team meeting",
        DayEnum.MONDAY, LocalTime.of(9, 0), LocalTime.of(1, 0));
    day.addEvent(event);

    List<Events> events = day.getEvents();
    assertTrue(events.contains(event));
  }

  @Test
  void addTask() {
    // Test adding a task
    Task task = new Task("Homework", "Complete assignment", DayEnum.MONDAY, false);
    day.addTask(task);

    List<Task> tasks = day.getTasks();
    assertTrue(tasks.contains(task));
  }

  @Test
  void getDay() {
    // Test getting the day
    assertEquals("MONDAY", day.getDay());
  }

  @Test
  void getEvents() {
    // Test getting events when there are no events
    List<Events> events = day.getEvents();
    assertNotNull(events);
    assertTrue(events.isEmpty());
  }

  @Test
  void getTasks() {
    // Test getting tasks when there are no tasks
    List<Task> tasks = day.getTasks();
    assertNotNull(tasks);
    assertTrue(tasks.isEmpty());
  }

  @Test
  void removeIfFound_TaskFound_RemovesTask() {
    // Test removing a task that exists
    Task task1 = new Task("Task 1", DayEnum.MONDAY);
    Task task2 = new Task("Task 2", DayEnum.MONDAY);
    Task task3 = new Task("Task 3", DayEnum.MONDAY);

    day.addTask(task1);
    day.addTask(task2);
    day.addTask(task3);

    day.removeIfFound(task2);

    List<Task> tasks = day.getTasks();
    assertEquals(2, tasks.size());
    assertTrue(tasks.contains(task1));
    assertFalse(tasks.contains(task2));
    assertTrue(tasks.contains(task3));
  }

  @Test
  void removeIfFound_TaskNotFound_NoChanges() {
    // Test removing a task that does not exist
    Task task1 = new Task("Task 1", DayEnum.MONDAY);
    Task task2 = new Task("Task 2", DayEnum.MONDAY);
    Task task3 = new Task("Task 3", DayEnum.MONDAY);

    day.addTask(task1);
    day.addTask(task3);

    day.removeIfFound(task2);

    List<Task> tasks = day.getTasks();
    assertEquals(2, tasks.size());
    assertTrue(tasks.contains(task1));
    assertFalse(tasks.contains(task2));
    assertTrue(tasks.contains(task3));
  }

  @Test
  void removeIfFound_EventFound_RemovesEvent() {
    // Test removing an event that exists
    Events event1 = new Events("Event 1", "Description 1",
        DayEnum.MONDAY, LocalTime.of(9, 0), LocalTime.of(1, 0));
    Events event2 = new Events("Event 2", "Description 2",
        DayEnum.MONDAY, LocalTime.of(10, 0), LocalTime.of(2, 0));
    Events event3 = new Events("Event 3", "Description 3",
        DayEnum.MONDAY, LocalTime.of(13, 0), LocalTime.of(1, 0));

    day.addEvent(event1);
    day.addEvent(event2);
    day.addEvent(event3);

    day.removeIfFound(event2);

    List<Events> events = day.getEvents();
    assertEquals(2, events.size());
    assertTrue(events.contains(event1));
    assertFalse(events.contains(event2));
    assertTrue(events.contains(event3));
  }

  @Test
  void removeIfFound_EventNotFound_NoChanges() {
    // Test removing an event that does not exist
    Events event1 = new Events("Event 1", "Description 1",
        DayEnum.MONDAY, LocalTime.of(9, 0), LocalTime.of(1, 0));
    Events event2 = new Events("Event 2", "Description 2",
        DayEnum.MONDAY, LocalTime.of(10, 0), LocalTime.of(2, 0));
    Events event3 = new Events("Event 3", "Description 3",
        DayEnum.MONDAY, LocalTime.of(13, 0), LocalTime.of(1, 0));

    day.addEvent(event1);
    day.addEvent(event3);

    day.removeIfFound(event2);

    List<Events> events = day.getEvents();
    assertEquals(2, events.size());
    assertTrue(events.contains(event1));
    assertFalse(events.contains(event2));
    assertTrue(events.contains(event3));
  }

  @Test
  public void testDayConstructor() {
    // Create sample data for the constructor
    DayEnum dayEnum = DayEnum.MONDAY;
    List<Events> events = new ArrayList<>();
    List<Task> tasks = new ArrayList<>();

    // Create a new Day instance using the constructor
    Day day = new Day(dayEnum, events, tasks);

    // Verify that the day, events, and tasks are set correctly
    assertEquals(dayEnum.toString(), day.getDay().toString());
    assertEquals(events, day.getEvents());
    assertEquals(tasks, day.getTasks());
  }



  @Test
  public void testEquals_SameObject() {
    Day day1 = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());

    assertTrue(day1.equals(day1));
  }

  @Test
  public void testEquals_NullObject() {
    Day day1 = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());

    assertFalse(day1.equals(null));
  }

  @Test
  public void testEquals_DifferentClass() {
    Day day1 = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());
    String day2 = "Monday";

    assertFalse(day1.equals(day2));
  }

  @Test
  public void testEquals_DifferentDayEnum() {
    Day day1 = new Day(DayEnum.MONDAY, new ArrayList<>(), new ArrayList<>());
    Day day2 = new Day(DayEnum.TUESDAY, new ArrayList<>(), new ArrayList<>());

    assertFalse(day1.equals(day2));
  }

  @Test
  public void testEquals_DifferentEvents() {
    Events event1 = new Events("Event 1", "Description 1",
        DayEnum.MONDAY, LocalTime.of(9, 0), LocalTime.of(1, 0));
    Events event2 = new Events("Event 2", "Description 2",
        DayEnum.MONDAY, LocalTime.of(10, 0), LocalTime.of(2, 0));
    Events event3 = new Events("Event 3", "Description 3",
        DayEnum.MONDAY, LocalTime.of(13, 0), LocalTime.of(1, 0));
    List<Events> events1 = new ArrayList<>();
    events1.add(event1);
    Day day1 = new Day(DayEnum.MONDAY, events1, new ArrayList<>());
    List<Events> events2 = new ArrayList<>();
    Day day2 = new Day(DayEnum.MONDAY, events2, new ArrayList<>());

    assertFalse(day1.equals(day2));
  }

  @Test
  public void testEquals_DifferentTasks() {
    List<Task> tasks1 = new ArrayList<>();
    Task task1 = new Task("Task 1", DayEnum.MONDAY);
    Task task2 = new Task("Task 2", DayEnum.MONDAY);
    Task task3 = new Task("Task 3", DayEnum.MONDAY);
    tasks1.add(task1);
    Day day1 = new Day(DayEnum.MONDAY, new ArrayList<>(), tasks1);

    List<Task> tasks2 = new ArrayList<>();
    tasks2.add(task2);
    Day day2 = new Day(DayEnum.MONDAY, new ArrayList<>(), tasks2);

    assertFalse(day1.equals(day2));
  }

  @Test
  public void testEquals_EqualObjects() {
    Events event1 = new Events("Event 1", "Description 1",
        DayEnum.MONDAY, LocalTime.of(9, 0), LocalTime.of(1, 0));
    Events event2 = new Events("Event 2", "Description 2",
        DayEnum.MONDAY, LocalTime.of(10, 0), LocalTime.of(2, 0));
    Events event3 = new Events("Event 3", "Description 3",
        DayEnum.MONDAY, LocalTime.of(13, 0), LocalTime.of(1, 0));
    Task task1 = new Task("Task 1", DayEnum.MONDAY);
    Task task2 = new Task("Task 2", DayEnum.MONDAY);
    Task task3 = new Task("Task 3", DayEnum.MONDAY);
    List<Events> events1 = new ArrayList<>();
    events1.add(event1);
    List<Task> tasks1 = new ArrayList<>();
    tasks1.add(task1);
    Day day1 = new Day(DayEnum.MONDAY, events1, tasks1);

    List<Events> events2 = new ArrayList<>();
    events2.add(event1);
    List<Task> tasks2 = new ArrayList<>();
    tasks2.add(task1);
    Day day2 = new Day(DayEnum.MONDAY, events2, tasks2);

    assertTrue(day1.equals(day2));
  }

}
