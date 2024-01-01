package binikomi.wc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/** Unit tests for {@code CountResult}. */
class CountResultTest {

  private static final String FILENAME = "filename";

  @Test
  void buildWithFilenameOnly() {
    final var actual = new CountResult.Builder().filename(FILENAME).build();

    assertThat(actual.filename()).hasValue(FILENAME);
    assertThat(actual.bytes()).isEmpty();
    assertThat(actual.chars()).isEmpty();
    assertThat(actual.words()).isEmpty();
    assertThat(actual.lines()).isEmpty();
  }

  @Test
  void buildWithBytesOnly() {
    final var actual = new CountResult.Builder().bytes(123).build();

    assertThat(actual.filename()).isEmpty();
    assertThat(actual.bytes()).hasValue(123);
    assertThat(actual.chars()).isEmpty();
    assertThat(actual.words()).isEmpty();
    assertThat(actual.lines()).isEmpty();
  }

  @Test
  void buildWithCharsOnly() {
    final var actual = new CountResult.Builder().chars(123).build();

    assertThat(actual.filename()).isEmpty();
    assertThat(actual.chars()).hasValue(123);
    assertThat(actual.bytes()).isEmpty();
    assertThat(actual.words()).isEmpty();
    assertThat(actual.lines()).isEmpty();
  }

  @Test
  void buildWithWordsOnly() {
    final var actual = new CountResult.Builder().words(123).build();

    assertThat(actual.filename()).isEmpty();
    assertThat(actual.words()).hasValue(123);
    assertThat(actual.bytes()).isEmpty();
    assertThat(actual.chars()).isEmpty();
    assertThat(actual.lines()).isEmpty();
  }

  @Test
  void buildWithLinesOnly() {
    final var actual = new CountResult.Builder().lines(123).build();

    assertThat(actual.filename()).isEmpty();
    assertThat(actual.lines()).hasValue(123);
    assertThat(actual.bytes()).isEmpty();
    assertThat(actual.chars()).isEmpty();
    assertThat(actual.words()).isEmpty();
  }

  @Test
  void buildWithAll() {
    final var actual =
        new CountResult.Builder().filename(FILENAME).bytes(1234).chars(123).words(12).lines(1).build();

    assertThat(actual.filename()).hasValue(FILENAME);
    assertThat(actual.bytes()).hasValue(1234);
    assertThat(actual.chars()).hasValue(123);
    assertThat(actual.words()).hasValue(12);
    assertThat(actual.lines()).hasValue(1);
  }
}
