package binikomi.wc.core;

import binikomi.wc.domain.ErrorCode;
import binikomi.wc.domain.ErrorResult;
import binikomi.wc.domain.Options;
import binikomi.wc.domain.Result;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.io.ByteStreams;
import java.io.IOException;
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

  public void count(List<Path> files, Options options) {
    final var results =
        files.isEmpty()
            ? countFromStdin(options)
            : files.stream()
                .map(Path::toAbsolutePath)
                .map(file -> countFile(file, options))
                .toList();
    resultPrinter.getOutputStrings(results).forEach(System.out::println);
  }

  @VisibleForTesting
  Result countFile(Path file, Options options) {
    final var filename = file.getFileName().toString();
    if (!Files.exists(file)) {
      return new ErrorResult(filename, ErrorCode.FILE_NOT_FOUND);
    } else if (Files.isDirectory(file)) {
      return new ErrorResult(filename, ErrorCode.DIRECTORY);
    } else {
      try {
        return textProcessor.process(filename, Files.readAllBytes(file), options);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private List<Result> countFromStdin(Options options) {
    try {
      byte[] bytes = ByteStreams.toByteArray(System.in);
      return List.of(textProcessor.process("", bytes, options));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
