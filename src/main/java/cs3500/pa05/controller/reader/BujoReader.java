package cs3500.pa05.controller.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * a BujoReader
 */
public class BujoReader implements Reader {
  /**
   * converts a Bujo file into a JsonNode
   *
   * @param file the file to read
   * @return a JsonNode
   */
  @Override
  public JsonNode read(File file) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    StringBuilder content = new StringBuilder();
    while (sc.hasNextLine()) {
      content.append(sc.nextLine()).append("\n");
    }

    try {
      return new ObjectMapper().readTree(content.toString());
    } catch (IOException e) {
      throw new IllegalArgumentException("Something went wrong with converting the file");
    }
  }
}
