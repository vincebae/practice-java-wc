package binikomi.wc.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import binikomi.wc.domain.CountResult;
import binikomi.wc.domain.ErrorCode;
import binikomi.wc.domain.ErrorResult;
import binikomi.wc.domain.Options;
import binikomi.wc.domain.Result;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for {@code WordCounter}. */
class WordCounterTest {

  private static final String HOME_DIRECTORY = "/home/binikomi";
  private static final String FILENAME1 = "file_name_1";
  private static final String FILENAME2 = "file_name_2";
  private static final String FILENAME_NOT_FOUND = "not_found";
  private static final String DIRECTORY_NAME = "directory";

  private static final String FILE_CONTENT_1 = "file_content_1";
  private static final String FILE_CONTENT_2 = "file_content_2";

  private static final Options DEFAULT_OPTIONS =
      new Options.Builder().printChars(true).printWords(true).printLines(true).build();

  private static final CountResult FAKE_COUNT_RESULT_1 =
      new CountResult.Builder().filename(FILENAME1).chars(100).words(10).lines(1).build();

  private static final CountResult FAKE_COUNT_RESULT_2 =
      new CountResult.Builder().filename(FILENAME1).chars(200).words(20).lines(2).build();

  private FileSystem fileSystem;
  private Path homeDir;

  private TextProcessor mockTextProcessor = mock(TextProcessor.class);
  private ResultPrinter mockResultPrinter = mock(ResultPrinter.class);
  private WordCounter subject;

  @BeforeEach
  void setup() {
    // Set up in-memory file system.
    fileSystem =
        Jimfs.newFileSystem(
            Configuration.unix().toBuilder().setWorkingDirectory(HOME_DIRECTORY).build());
    homeDir = fileSystem.getPath(HOME_DIRECTORY);
    createFile(FILENAME1, FILE_CONTENT_1);
    createFile(FILENAME2, FILE_CONTENT_2);
    createDirectory(DIRECTORY_NAME);

    subject = new WordCounter(mockTextProcessor, mockResultPrinter);
  }

  @Test
  void countSuccessfully() {
    when(mockTextProcessor.process(anyString(), any(byte[].class), any(Options.class)))
        .thenReturn(FAKE_COUNT_RESULT_1);
    final var file = homeDir.resolve(FILENAME1);

    final var actual = subject.countFile(file, DEFAULT_OPTIONS);

    assertThat(actual).isEqualTo(FAKE_COUNT_RESULT_1);
    final var bytes = FILE_CONTENT_1.getBytes(StandardCharsets.UTF_8);
    verify(mockTextProcessor, times(1)).process(FILENAME1, bytes, DEFAULT_OPTIONS);
  }

  @Test
  void countFileNotFound() {
    final var file = homeDir.resolve(FILENAME_NOT_FOUND);

    final var actual = subject.countFile(file, DEFAULT_OPTIONS);

    final var expected = new ErrorResult(FILENAME_NOT_FOUND, ErrorCode.FILE_NOT_FOUND);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void countDirectory() {
    final var file = homeDir.resolve(DIRECTORY_NAME);

    final var actual = subject.countFile(file, DEFAULT_OPTIONS);

    final var expected = new ErrorResult(DIRECTORY_NAME, ErrorCode.DIRECTORY);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void countVerifyInteraction() {
    when(mockTextProcessor.process(anyString(), any(byte[].class), any(Options.class)))
        .thenReturn(FAKE_COUNT_RESULT_1, FAKE_COUNT_RESULT_2);
    when(mockResultPrinter.getOutputStrings(anyList())).thenReturn(List.of("fake result"));
    final var file1 = homeDir.resolve(FILENAME1);
    final var file2 = homeDir.resolve(FILENAME2);
    final var fileNotFound = homeDir.resolve(FILENAME_NOT_FOUND);
    final var directory = homeDir.resolve(DIRECTORY_NAME);
    final var files = List.of(file1, file2, fileNotFound, directory);

    subject.count(files, DEFAULT_OPTIONS);

    final var bytes1 = FILE_CONTENT_1.getBytes(StandardCharsets.UTF_8);
    final var bytes2 = FILE_CONTENT_2.getBytes(StandardCharsets.UTF_8);
    verify(mockTextProcessor, times(1)).process(FILENAME1, bytes1, DEFAULT_OPTIONS);
    verify(mockTextProcessor, times(1)).process(FILENAME2, bytes2, DEFAULT_OPTIONS);
    verifyNoMoreInteractions(mockTextProcessor);
    final List<Result> expectedResults =
        List.of(
            FAKE_COUNT_RESULT_1,
            FAKE_COUNT_RESULT_2,
            new ErrorResult(FILENAME_NOT_FOUND, ErrorCode.FILE_NOT_FOUND),
            new ErrorResult(DIRECTORY_NAME, ErrorCode.DIRECTORY));
    verify(mockResultPrinter, times(1)).getOutputStrings(expectedResults);
  }

  private Path createDirectory(String name) {
    try {
      return Files.createDirectory(homeDir.resolve(name));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Path createFile(String name, String fileContent) {
    try {
      return Files.write(homeDir.resolve(name), fileContent.getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
