package template;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/**
 * {@code OptionsBuilder} collects parameters and invokes the static factory method:
 * {@code new template.Options(..)}.
 * Call the {@link #build()} method to get a result of type {@code template.Options}.
 * <p><em>{@code OptionsBuilder} is not thread-safe and generally should not be stored in a field or collection,
 * but instead used immediately to create instances.</em>
 */
@Generated(from = "template.Options", generator = "Immutables")
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
@NotThreadSafe
public final class OptionsBuilder {
  private static final long INIT_BIT_PRINT_BYTES = 0x1L;
  private static final long INIT_BIT_PRINT_CHARS = 0x2L;
  private static final long INIT_BIT_PRINT_WORDS = 0x4L;
  private static final long INIT_BIT_PRINT_LINES = 0x8L;
  private long initBits = 0xfL;

  private boolean printBytes;
  private boolean printChars;
  private boolean printWords;
  private boolean printLines;

  /**
   * Creates a {@code OptionsBuilder} factory builder.
   * <pre>
   * new OptionsBuilder()
   *    .printBytes(boolean) // required {@code printBytes}
   *    .printChars(boolean) // required {@code printChars}
   *    .printWords(boolean) // required {@code printWords}
   *    .printLines(boolean) // required {@code printLines}
   *    .build();
   * </pre>
   */
  public OptionsBuilder() {
  }

  /**
   * Initializes the value for the {@code printBytes} attribute.
   * @param printBytes The value for printBytes 
   * @return {@code this} builder for use in a chained invocation
   */
  @CanIgnoreReturnValue 
  public final OptionsBuilder printBytes(boolean printBytes) {
    this.printBytes = printBytes;
    initBits &= ~INIT_BIT_PRINT_BYTES;
    return this;
  }

  /**
   * Initializes the value for the {@code printChars} attribute.
   * @param printChars The value for printChars 
   * @return {@code this} builder for use in a chained invocation
   */
  @CanIgnoreReturnValue 
  public final OptionsBuilder printChars(boolean printChars) {
    this.printChars = printChars;
    initBits &= ~INIT_BIT_PRINT_CHARS;
    return this;
  }

  /**
   * Initializes the value for the {@code printWords} attribute.
   * @param printWords The value for printWords 
   * @return {@code this} builder for use in a chained invocation
   */
  @CanIgnoreReturnValue 
  public final OptionsBuilder printWords(boolean printWords) {
    this.printWords = printWords;
    initBits &= ~INIT_BIT_PRINT_WORDS;
    return this;
  }

  /**
   * Initializes the value for the {@code printLines} attribute.
   * @param printLines The value for printLines 
   * @return {@code this} builder for use in a chained invocation
   */
  @CanIgnoreReturnValue 
  public final OptionsBuilder printLines(boolean printLines) {
    this.printLines = printLines;
    initBits &= ~INIT_BIT_PRINT_LINES;
    return this;
  }

  /**
   * Invokes {@code new template.Options(..)} using the collected parameters and returns the result of the invocation
   * @return A result of type {@code template.Options}
   * @throws java.lang.IllegalStateException if any required attributes are missing
   */
  public Options build() {
    checkRequiredAttributes();
    return new Options(printBytes, printChars, printWords, printLines);
  }

  private boolean printBytesIsSet() {
    return (initBits & INIT_BIT_PRINT_BYTES) == 0;
  }

  private boolean printCharsIsSet() {
    return (initBits & INIT_BIT_PRINT_CHARS) == 0;
  }

  private boolean printWordsIsSet() {
    return (initBits & INIT_BIT_PRINT_WORDS) == 0;
  }

  private boolean printLinesIsSet() {
    return (initBits & INIT_BIT_PRINT_LINES) == 0;
  }

  private void checkRequiredAttributes() {
    if (initBits != 0) {
      throw new IllegalStateException(formatRequiredAttributesMessage());
    }
  }

  private String formatRequiredAttributesMessage() {
    List<String> attributes = new ArrayList<>();
    if (!printBytesIsSet()) attributes.add("printBytes");
    if (!printCharsIsSet()) attributes.add("printChars");
    if (!printWordsIsSet()) attributes.add("printWords");
    if (!printLinesIsSet()) attributes.add("printLines");
    return "Cannot build Options, some of required attributes are not set " + attributes;
  }
}
