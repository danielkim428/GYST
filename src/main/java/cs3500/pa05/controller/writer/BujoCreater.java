package cs3500.pa05.controller.writer;

import java.io.File;
import java.io.IOException;

/**
 * Creates bujo files
 */
public class BujoCreater implements Creater {
  /**
   * Create a bujo File to the given file
   *
   * @param myObj  file location to create file to
   */
  @Override
  public void createFile(File myObj) {
    try {
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
