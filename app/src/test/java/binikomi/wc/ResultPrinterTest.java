package binikomi.wc;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/** Unit tests for {@code ResultPrinter}. */
class ResultPrinterTest {

  @ParameterizedTest
  @MethodSource("getOutputStringsTestsDataSource")
  void getOutputStringsTests(List<Result> input, List<String> expected) {
    assertThat(ResultPrinter.getOutputStrings(input)).containsExactlyElementsOf(expected);
  }

  private static Stream<Arguments> getOutputStringsTestsDataSource() {
    return Stream.of(
        Arguments.of(List.of(), List.of()),
        Arguments.of(
          List.of(
            new ResultBuilder()
            .bytes(123)
            .build()),
          List.of("123"))

        );
  }
}
