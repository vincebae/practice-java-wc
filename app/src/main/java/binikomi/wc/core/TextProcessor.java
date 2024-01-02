package binikomi.wc.core;

import binikomi.wc.domain.CountResult;
import binikomi.wc.domain.Options;
import java.nio.charset.StandardCharsets;

/** Class to process to count bytes, chars, words and/or lines from the text. */
public final class TextProcessor {

  public TextProcessor() {}

  public CountResult process(String filename, byte[] bytes, Options options) {
    final var resultBuilder = new CountResult.Builder().filename(filename);
    if (options.printBytes()) {
      resultBuilder.bytes(bytes.length);
    }
    if (options.printChars() || options.printWords() || options.printLines()) {
      final var str = new String(bytes, StandardCharsets.UTF_8);
      if (options.printChars()) {
        resultBuilder.chars(countChars(str));
      }
      if (options.printWords()) {
        resultBuilder.words(countWords(str));
      }
      if (options.printLines()) {
        resultBuilder.lines(countLines(str));
      }
    }
    return resultBuilder.build();
  }

  private static int countChars(String str) {
    return str.length();
  }

  private static int countWords(String str) {
    return str.lines().mapToInt(line -> line.trim().split("\s+").length).sum();
  }

  private static int countLines(String str) {
    return (int) str.lines().count();
  }
}
