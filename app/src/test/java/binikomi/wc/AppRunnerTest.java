package binikomi.wc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import binikomi.wc.core.WordCounter;
import binikomi.wc.domain.Options;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import picocli.CommandLine;

/** Unit tests for {@code AppRunner}. */
class AppRunnerTest {

  private static final String FILENAME1 = "file_name_1";
  private static final String FILENAME2 = "file_name_2";

  private AppRunner appRunner;
  private WordCounter mockWordCounter;

  @BeforeEach
  void setup() {
    mockWordCounter = Mockito.mock(WordCounter.class);
    appRunner = new AppRunner(mockWordCounter);
  }

  @Test
  void runAppWithNoOptions() {
    doNothing().when(mockWordCounter).count(any(Options.class), anyList());

    new CommandLine(appRunner).execute();

    final var expectedOptions =
        new Options.Builder()
            .printBytes(false)
            .printChars(true)
            .printWords(true)
            .printLines(true)
            .build();
    final List<Path> expectedFiles = List.of();

    verify(mockWordCounter, times(1)).count(expectedOptions, expectedFiles);
  }

  @Test
  void runAppWithIncorrectOptions_appRunnerNotExecuted() {
    new CommandLine(appRunner).execute("--invalid");

    verifyNoInteractions(mockWordCounter);
  }

  @ParameterizedTest
  @MethodSource("runAppTestsDataSource")
  void runAppTests(String[] args, Options expectedOptions, List<Path> expectedFiles) {
    doNothing().when(mockWordCounter).count(any(Options.class), anyList());

    new CommandLine(appRunner).execute(args);

    verify(mockWordCounter, times(1)).count(expectedOptions, expectedFiles);
  }

  private static Stream<Arguments> runAppTestsDataSource() {
    return Stream.of(
        Arguments.of(
            new String[] {"-c", "-m", "-w", "-l", FILENAME1, FILENAME2},
            new Options.Builder()
                .printBytes(true)
                .printChars(true)
                .printWords(true)
                .printLines(true)
                .build(),
            List.of(Path.of(FILENAME1), Path.of(FILENAME2))),
        Arguments.of(
            new String[] {"--bytes", "--chars", "--words", "--lines", FILENAME1, FILENAME2},
            new Options.Builder()
                .printBytes(true)
                .printChars(true)
                .printWords(true)
                .printLines(true)
                .build(),
            List.of(Path.of(FILENAME1), Path.of(FILENAME2))),
        Arguments.of(
            new String[] {"-c", "--words", FILENAME1},
            new Options.Builder()
                .printBytes(true)
                .printChars(false)
                .printWords(true)
                .printLines(false)
                .build(),
            List.of(Path.of(FILENAME1))));
  }
}
