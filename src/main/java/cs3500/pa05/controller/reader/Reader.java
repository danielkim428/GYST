package cs3500.pa05.controller.reader;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;

/**
 * the Reader
 */
public interface Reader {
  /**
   * @param file the file to read
   * @return the file converted as a JsonNode
   */
  JsonNode read(File file);
}
