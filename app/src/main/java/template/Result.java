package template;

import java.util.Optional;
import org.immutables.builder.Builder;

public record Result(
    Optional<Integer> bytes,
    Optional<Integer> chars,
    Optional<Integer> words,
    Optional<Integer> lines) {

  @Builder.Constructor
  public Result {}
}
