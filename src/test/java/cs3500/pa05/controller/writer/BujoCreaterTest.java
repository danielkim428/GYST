package cs3500.pa05.controller.writer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BujoCreaterTest {
  File file;
  Creater creater;

  @BeforeEach
  public void setup() {
    file = new File("src/test/resources/newFile.bujo");
    creater = new BujoCreater();
    file.delete();
  }

  @Test
  public void testCreate() {
    assertFalse(file.exists());
    creater.createFile(file);
    assertTrue(file.exists());
    creater.createFile(file);
    assertTrue(file.exists());
  }
}