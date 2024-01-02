package binikomi.wc.core;

import static org.assertj.core.api.Assertions.assertThat;

import binikomi.wc.domain.CountResult;
import binikomi.wc.domain.Options;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/** Unit tests for {@code TextCounter}. */
class TextCounterTest {

  private static final String FILENAME = "filename";
  private static final Options DEFAULT_OPTIONS =
      new Options.Builder().printChars(true).printWords(true).printLines(true).build();
  private static final Options ALL_OPTIONS =
      new Options.Builder()
          .printBytes(true)
          .printChars(true)
          .printWords(true)
          .printLines(true)
          .build();

  private TextProcessor subject;

  @BeforeEach
  void setup() {
    subject = new TextProcessor();
  }

  @ParameterizedTest
  @MethodSource("processTestsDataSource")
  void processTests(String filename, String input, Options options, CountResult expected) {
    final var bytes = input.getBytes(StandardCharsets.UTF_8);
    assertThat(subject.process(filename, bytes, options)).isEqualTo(expected);
  }

  private static Stream<Arguments> processTestsDataSource() {
    return Stream.of(
        Arguments.of(
            FILENAME,
            "abc\n",
            DEFAULT_OPTIONS,
            new CountResult.Builder().filename(FILENAME).chars(4).words(1).lines(1).build()),
        Arguments.of(
            FILENAME,
            "abc\n",
            ALL_OPTIONS,
            new CountResult.Builder()
                .filename(FILENAME)
                .bytes(4)
                .chars(4)
                .words(1)
                .lines(1)
                .build()),
        Arguments.of(
            FILENAME,
            "abc def 1234\nabcde\n123 456 7890\n",
            DEFAULT_OPTIONS,
            new CountResult.Builder().filename(FILENAME).chars(32).words(7).lines(3).build()),
        Arguments.of(
            FILENAME,
            "abc def 1234\nabcde\n123 456 7890\n",
            ALL_OPTIONS,
            new CountResult.Builder()
                .filename(FILENAME)
                .bytes(32)
                .chars(32)
                .words(7)
                .lines(3)
                .build()),
        // Check bytes and chars for non-ascii characters.
        Arguments.of(
            FILENAME,
            "안녕, 세계\n",
            new Options.Builder().printBytes(true).printChars(true).build(),
            new CountResult.Builder().filename(FILENAME).bytes(15).chars(7).build()));
  }
}
