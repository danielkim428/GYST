package cs3500.pa05.controller.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.TaskJson;
import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the BujoReader class.
 */
public class BujoReaderTest {
  File file;
  Reader reader;

  @BeforeEach
  public void setup() {
    file = new File("src/test/resources/test.bujo");
    reader = new BujoReader();
  }

  @Test
  public void testRead() {
    TaskJson taskJson = new TaskJson("Test", "Desc", "MONDAY", false);
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonNode = mapper.convertValue(taskJson, JsonNode.class);
    JsonNode node = reader.read(file);
    Assertions.assertEquals(jsonNode, node);
  }
}
