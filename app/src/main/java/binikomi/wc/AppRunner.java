/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package binikomi.wc;

import binikomi.wc.core.WordCounter;
import binikomi.wc.domain.Options;
import java.nio.file.Path;
import java.util.List;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(mixinStandardHelpOptions = true)
final class AppRunner implements Runnable {

  @Option(names = {"-c", "--bytes"})
  private boolean printBytes;

  @Option(names = {"-m", "--chars"})
  private boolean printChars;

  @Option(names = {"-w", "--words"})
  private boolean printWords;

  @Option(names = {"-l", "--lines"})
  private boolean printLines;

  @Parameters private List<Path> files;

  private final WordCounter wordCounter;

  AppRunner(WordCounter wordCounter) {
    this.wordCounter = wordCounter;
  }

  @Override
  public void run() {
    wordCounter.count(files == null ? List.of() : files, getOptions());
  }

  private Options getOptions() {
    // when no options are provided, return default option.
    final boolean hasAnyOptions = printBytes || printChars || printWords || printLines;
    return hasAnyOptions
        ? new Options.Builder()
            .printBytes(printBytes)
            .printChars(printChars)
            .printWords(printWords)
            .printLines(printLines)
            .build()
        : new Options.Builder()
            .printBytes(false)
            .printChars(true)
            .printWords(true)
            .printLines(true)
            .build();
  }
}
