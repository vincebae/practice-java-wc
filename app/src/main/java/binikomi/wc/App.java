/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package binikomi.wc;

import binikomi.wc.core.WordCounter;
import picocli.CommandLine;

public final class App {

  public static void main(String[] args) {
    final var appRunner = new AppRunner(new WordCounter());
    final var commandLine = new CommandLine(appRunner);
    commandLine.execute(args);
  }
}