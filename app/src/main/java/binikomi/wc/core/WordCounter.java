package binikomi.wc.core;

import binikomi.wc.domain.Options;
import java.nio.file.Path;
import java.util.List;

/** Core class to process the file and print the results for wc. */
public final class WordCounter {

  public WordCounter() {}

  public void count(Options options, List<Path> files) {
    System.out.println("Options: " + options);
    System.out.println("Files: " + files);
  }
}
