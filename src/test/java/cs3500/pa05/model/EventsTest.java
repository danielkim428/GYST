package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.DayEnum;
import cs3500.pa05.model.Events;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests for the Events class.
 */
public class EventsTest {

  private Events event;

  @BeforeEach
  void setUp() {
    event = new Events("Meeting", "Team meeting", DayEnum.MONDAY,
        LocalTime.of(9, 0), LocalTime.of(1, 0));
  }

  @Test
  void getName() {
    // Test getting the event's name
    assertEquals("Meeting", event.getName());
  }

  @Test
  void getDay() {
    // Test getting the event's day
    assertEquals("MONDAY", event.getDay());
  }

  @Test
  void getDescription() {
    // Test getting the event's description
    assertEquals("Team meeting", event.getDescription());
  }

  @Test
  void getStartTime() {
    // Test getting the event's start time
    assertEquals(LocalTime.of(9, 0), event.getStartTime());
  }

  @Test
  void getDuration() {
    // Test getting the event's duration
    assertEquals(LocalTime.of(1, 0), event.getDuration());
  }
}
