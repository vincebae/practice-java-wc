package template;

import org.immutables.builder.Builder;

public record Options(
    boolean printBytes, boolean printChars, boolean printWords, boolean printLines) {

  @Builder.Constructor
  public Options {}

  public boolean printAll() {
    return printBytes == printChars && printBytes == printWords && printBytes == printLines;
  }
}
