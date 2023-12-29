package binikomi.wc;

import java.util.Optional;
import org.immutables.builder.Builder;

record Result(
    Optional<Integer> bytes,
    Optional<Integer> chars,
    Optional<Integer> words,
    Optional<Integer> lines) {

  @Builder.Constructor
  Result {}
}
