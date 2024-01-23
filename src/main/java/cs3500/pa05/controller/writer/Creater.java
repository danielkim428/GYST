package cs3500.pa05.controller.writer;

import java.io.File;

/**
 * Creates a file
 */
public interface Creater {
  /**
   * Creates a file to the given filePath
   *
   * @param file  file location to create file to
   */
  void createFile(File file);
}
