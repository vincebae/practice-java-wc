package template;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/**
 * {@code ResultBuilder} collects parameters and invokes the static factory method:
 * {@code new template.Result(..)}.
 * Call the {@link #build()} method to get a result of type {@code template.Result}.
 * <p><em>{@code ResultBuilder} is not thread-safe and generally should not be stored in a field or collection,
 * but instead used immediately to create instances.</em>
 */
@Generated(from = "template.Result", generator = "Immutables")
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
@NotThreadSafe
public final class ResultBuilder {
  private Optional<Integer> bytes = Optional.empty();
  private Optional<Integer> chars = Optional.empty();
  private Optional<Integer> words = Optional.empty();
  private Optional<Integer> lines = Optional.empty();

  /**
   * Creates a {@code ResultBuilder} factory builder.
   * <pre>
   * new ResultBuilder()
   *    .bytes(Integer) // optional {@code bytes}
   *    .chars(Integer) // optional {@code chars}
   *    .words(Integer) // optional {@code words}
   *    .lines(Integer) // optional {@code lines}
   *    .build();
   * </pre>
   */
  public ResultBuilder() {
  }

  /**
   * Initializes the optional value {@code bytes} to bytes.
   * @param bytes The value for bytes
   * @return {@code this} builder for chained invocation
   */
  @CanIgnoreReturnValue 
  public final ResultBuilder bytes(int bytes) {
    this.bytes = Optional.of(bytes);
    return this;
  }

  /**
   * Initializes the optional value {@code bytes} to bytes.
   * @param bytes The value for bytes
   * @return {@code this} builder for use in a chained invocation
   */
  @CanIgnoreReturnValue 
  public final ResultBuilder bytes(Optional<Integer> bytes) {
    this.bytes = (Optional<Integer>) Objects.requireNonNull(bytes, "bytes");
    return this;
  }

  /**
   * Initializes the optional value {@code chars} to chars.
   * @param chars The value for chars
   * @return {@code this} builder for chained invocation
   */
  @CanIgnoreReturnValue 
  public final ResultBuilder chars(int chars) {
    this.chars = Optional.of(chars);
    return this;
  }

  /**
   * Initializes the optional value {@code chars} to chars.
   * @param chars The value for chars
   * @return {@code this} builder for use in a chained invocation
   */
  @CanIgnoreReturnValue 
  public final ResultBuilder chars(Optional<Integer> chars) {
    this.chars = (Optional<Integer>) Objects.requireNonNull(chars, "chars");
    return this;
  }

  /**
   * Initializes the optional value {@code words} to words.
   * @param words The value for words
   * @return {@code this} builder for chained invocation
   */
  @CanIgnoreReturnValue 
  public final ResultBuilder words(int words) {
    this.words = Optional.of(words);
    return this;
  }

  /**
   * Initializes the optional value {@code words} to words.
   * @param words The value for words
   * @return {@code this} builder for use in a chained invocation
   */
  @CanIgnoreReturnValue 
  public final ResultBuilder words(Optional<Integer> words) {
    this.words = (Optional<Integer>) Objects.requireNonNull(words, "words");
    return this;
  }

  /**
   * Initializes the optional value {@code lines} to lines.
   * @param lines The value for lines
   * @return {@code this} builder for chained invocation
   */
  @CanIgnoreReturnValue 
  public final ResultBuilder lines(int lines) {
    this.lines = Optional.of(lines);
    return this;
  }

  /**
   * Initializes the optional value {@code lines} to lines.
   * @param lines The value for lines
   * @return {@code this} builder for use in a chained invocation
   */
  @CanIgnoreReturnValue 
  public final ResultBuilder lines(Optional<Integer> lines) {
    this.lines = (Optional<Integer>) Objects.requireNonNull(lines, "lines");
    return this;
  }

  /**
   * Invokes {@code new template.Result(..)} using the collected parameters and returns the result of the invocation
   * @return A result of type {@code template.Result}
   * @throws java.lang.IllegalStateException if any required attributes are missing
   */
  public Result build() {
    return new Result(bytes, chars, words, lines);
  }
}
