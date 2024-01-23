package cs3500.pa05.controller.writer;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;

/**
 * the Writer interface
 */
public interface Writer {
  /**
   * writes to a file
   *
   * @param file the file to write to
   * @param jsonNode the JsonNode to write
   */
  void write(File file, JsonNode jsonNode);
}
