package binikomi.wc.core;

import static org.mockito.Mockito.mock;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import java.nio.file.FileSystem;
import org.junit.jupiter.api.BeforeEach;

/** Unit tests for {@code WordCounter}. */
class WordCounterTest {

  private static final String HOME_DIRECTORY = "/home/binikomi";

  private TextProcessor mockTextProcessor = mock(TextProcessor.class);
  private ResultPrinter mockResultPrinter = mock(ResultPrinter.class);

  private FileSystem fileSystem;

  @BeforeEach
  void setup() {
    fileSystem =
        Jimfs.newFileSystem(Configuration.unix().toBuilder().setRoots(HOME_DIRECTORY).build());
  }
}
