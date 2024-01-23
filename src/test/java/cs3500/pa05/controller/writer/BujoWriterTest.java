package cs3500.pa05.controller.writer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.TaskJson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BujoWriterTest {
  JsonNode jsonNode;
  Writer writer;
  ObjectMapper mapper;
  File file;

  @BeforeEach
  public void setup() throws IOException {
    mapper = new ObjectMapper();
    writer = new BujoWriter();
    TaskJson taskJson = new TaskJson("Test", "Desc", "MONDAY", false);
    jsonNode = mapper.convertValue(taskJson, JsonNode.class);
    file = new File("src/test/resources/writeTest.bujo");
    FileWriter myWriter = new FileWriter("src/test/resources/writeTest.bujo");
    myWriter.write("");
    myWriter.close();
  }

  @Test
  public void testWrite() throws IOException {
    assertEquals("", Files.readString(Path.of(file.toURI())));
    writer.write(file, jsonNode);
    assertEquals("{\"Name\":\"Test\",\"Description\":"
        + "\"Desc\",\"Day\":\"MONDAY\",\"Completed\":false}",
        Files.readString(Path.of(file.toURI())));
  }
}