package binikomi.wc.core;

import binikomi.wc.domain.Options;
import binikomi.wc.domain.Result;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Core class to process the file and print the results for wc. */
public final class WordCounter {

  private static final Logger LOGGER = LoggerFactory.getLogger(WordCounter.class);

  private TextProcessor textProcessor;
  private ResultPrinter resultPrinter;

  public WordCounter(TextProcessor textProcessor, ResultPrinter resultPrinter) {
    this.textProcessor = textProcessor;
    this.resultPrinter = resultPrinter;
  }

  public void count(Options options, List<Path> files) {
    LOGGER.info("Options: {}", options);
    LOGGER.info("Files: {}", files);

    final var results = files.stream().map(file -> count(options, file)).toList();
    resultPrinter.getOutputStrings(results).forEach(System.out::println);
  }

  private Result count(Options options, Path file) {
    if (!Files.exists(file)) {
    } else if (Files.isDirectory(file)) {
    } else {
    }
    return null;
  }
}
