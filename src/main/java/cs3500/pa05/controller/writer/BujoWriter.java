package cs3500.pa05.controller.writer;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * the BujoWriter
 */
public class BujoWriter implements Writer {
  /**
   * writes to a bujo file
   *
   * @param file the file to write to
   * @param jsonNode the json node to write on
   */
  @Override
  public void write(File file, JsonNode jsonNode) {
    Path path = file.toPath();
    byte[] data = jsonNode.toString().getBytes();
    try {
      Files.write(path, data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
