package binikomi.wc.core;

import static org.assertj.core.api.Assertions.assertThat;

import binikomi.wc.domain.CountResult;
import binikomi.wc.domain.ErrorCode;
import binikomi.wc.domain.ErrorResult;
import binikomi.wc.domain.Result;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/** Unit tests for {@code ResultPrinter}. */
class ResultPrinterTest {

  private static final String FILENAME1 = "file_name_1";
  private static final String FILENAME2 = "file_name_2";
  private static final String FILENAME3 = "file_name_3";

  @ParameterizedTest
  @MethodSource("getOutputStringsTestsDataSource")
  void getOutputStringsTests(List<Result> input, List<String> expected) {
    final var resultPrinter = new ResultPrinter();
    assertThat(resultPrinter.getOutputStrings(input)).containsExactlyElementsOf(expected);
  }

  private static Stream<Arguments> getOutputStringsTestsDataSource() {
    return Stream.of(
        // No result
        Arguments.of(List.of(), List.of()),
        // A single CountResult with a single field in it.
        Arguments.of(List.of(new CountResult.Builder().bytes(1).build()), List.of(" 1")),
        Arguments.of(
            List.of(new CountResult.Builder().filename(FILENAME1).lines(1).build()),
            List.of(" 1 file_name_1")),
        Arguments.of(
            List.of(new CountResult.Builder().filename(FILENAME1).words(12).build()),
            List.of(" 12 file_name_1")),
        Arguments.of(
            List.of(new CountResult.Builder().filename(FILENAME1).chars(123).build()),
            List.of(" 123 file_name_1")),
        Arguments.of(
            List.of(new CountResult.Builder().filename(FILENAME1).bytes(1234).build()),
            List.of(" 1234 file_name_1")),
        // A single CountResult with all fields in it.
        Arguments.of(
            List.of(new CountResult.Builder().lines(1).words(12).chars(123).bytes(1234).build()),
            List.of("    1   12  123 1234")),
        Arguments.of(
            List.of(
                new CountResult.Builder()
                    .filename(FILENAME1)
                    .lines(1)
                    .words(12)
                    .chars(123)
                    .bytes(1234)
                    .build()),
            List.of("    1   12  123 1234 file_name_1")),
        // A single ErrorResult.
        Arguments.of(
            List.of(new ErrorResult(FILENAME1, ErrorCode.FILE_NOT_FOUND)),
            List.of("file_name_1: No such file or directory")),
        Arguments.of(
            List.of(new ErrorResult(FILENAME1, ErrorCode.DIRECTORY)),
            List.of("file_name_1: Is a directory")),
        // Multiple CountResults with a single field in them.
        Arguments.of(
            List.of(
                new CountResult.Builder().filename(FILENAME1).bytes(1).build(),
                new CountResult.Builder().filename(FILENAME2).bytes(10).build(),
                new CountResult.Builder().filename(FILENAME3).bytes(100).build()),
            List.of("   1 file_name_1", "  10 file_name_2", " 100 file_name_3", " 111 total")),
        // Multiple CountResults with all fields in them.
        Arguments.of(
            List.of(
                new CountResult.Builder()
                    .filename(FILENAME1)
                    .lines(1)
                    .words(10)
                    .chars(100)
                    .bytes(1000)
                    .build(),
                new CountResult.Builder()
                    .filename(FILENAME2)
                    .lines(2)
                    .words(20)
                    .chars(200)
                    .bytes(2000)
                    .build(),
                new CountResult.Builder()
                    .filename(FILENAME3)
                    .lines(8)
                    .words(80)
                    .chars(800)
                    .bytes(8000)
                    .build()),
            List.of(
                "     1    10   100  1000 file_name_1",
                "     2    20   200  2000 file_name_2",
                "     8    80   800  8000 file_name_3",
                "    11   110  1100 11000 total")),
        // Mixed results
        Arguments.of(
            List.of(
                new CountResult.Builder().filename(FILENAME1).lines(1).words(10).chars(100).build(),
                new ErrorResult("invalid.txt", ErrorCode.FILE_NOT_FOUND),
                new CountResult.Builder().filename(FILENAME2).lines(2).words(20).chars(200).build(),
                new ErrorResult("directory", ErrorCode.DIRECTORY)),
            List.of(
                "   1  10 100 file_name_1",
                "invalid.txt: No such file or directory",
                "   2  20 200 file_name_2",
                "directory: Is a directory",
                "   3  30 300 total")));
  }
}
