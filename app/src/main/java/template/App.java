/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package template;

import picocli.CommandLine;
import picocli.CommandLine.Option;

public class App implements Runnable {

  @Option(names = {"-h", "--help"})
  private boolean help;

  @Option(names = {"-c", "--bytes"})
  private boolean printBytes;

  @Option(names = {"-m", "--chars"})
  private boolean printChars;

  @Option(names = {"-w", "--words"})
  private boolean printWords;

  @Option(names = {"-l", "--lines"})
  private boolean printLines;


  @Override
  public void run() {
    if (help) {
      System.out.println(getHelp());
      return;
    }

    final var options = new OptionsBuilder()
      .printBytes(printBytes)
      .printChars(printChars)
      .printWords(printWords)
      .printLines(printLines)
      .build();


  }

  private String getHelp() {
    return "Help message";
  }

  public static void main(String[] args) {
    final var app = new App();
    final var commandLine = new CommandLine(app);
    commandLine.execute(args);
  }
}
