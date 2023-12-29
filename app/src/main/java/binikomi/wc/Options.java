package binikomi.wc;

import org.immutables.builder.Builder;

record Options(boolean printBytes, boolean printChars, boolean printWords, boolean printLines) {

  static final Options DEFAULT_OPTIONS =
      new OptionsBuilder()
          .printBytes(false)
          .printChars(true)
          .printWords(true)
          .printLines(true)
          .build();

  @Builder.Constructor
  Options {}
}
