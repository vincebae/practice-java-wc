package template;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/** Unit tests for {@code Options}. */
class OptionsTest {

  @Test
  void printAll_whenAllFieldsAreFalse_returnsTrue() {
    assertThat(new Options(false, false, false, false).printAll()).isTrue();
  }

  @Test
  void printAll_whenAllFieldsAreTrue_returnsTrue() {
    assertThat(new Options(true, true, true, true).printAll()).isTrue();
  }

  @Test
  void printAll_whenNotAllFieldsAreSame_returnsFalse() {
    assertThat(new Options(true, false, false, false).printAll()).isFalse();
    assertThat(new Options(false, true, false, false).printAll()).isFalse();
    assertThat(new Options(false, false, true, false).printAll()).isFalse();
    assertThat(new Options(false, false, false, true).printAll()).isFalse();

    assertThat(new Options(true, true, false, false).printAll()).isFalse();
    assertThat(new Options(false, false, true, true).printAll()).isFalse();

    assertThat(new Options(true, true, true, false).printAll()).isFalse();
    assertThat(new Options(false, true, true, true).printAll()).isFalse();
  }

  @Test
  void builderTest() {
    final var actual =
        new OptionsBuilder()
            .printBytes(true)
            .printChars(false)
            .printWords(true)
            .printLines(false)
            .build();

    assertThat(actual.printBytes()).isTrue();
    assertThat(actual.printChars()).isFalse();
    assertThat(actual.printWords()).isTrue();
    assertThat(actual.printLines()).isFalse();
    assertThat(actual.printAll()).isFalse();
  }
}
