package binikomi.wc.core;

import binikomi.wc.domain.CountOption;
import binikomi.wc.domain.CountResult;
import binikomi.wc.domain.ErrorResult;
import binikomi.wc.domain.Result;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.TreeMap;
import java.util.stream.Stream;

/** Class to generate strings for a list of {@code Result} for output. */
public final class ResultPrinter {

  private static final int PADDING_SIZE = 1;
  private static final String TOTAL_ROW_NAME = "total";
  private static final List<CountOption> COUNT_OPTION_ORDER =
      List.of(CountOption.LINES, CountOption.WORDS, CountOption.CHARS, CountOption.BYTES);

  public ResultPrinter() {}

  public List<String> getOutputStrings(List<Result> results) {
    return switch (results.size()) {
      case 0 -> List.of();
      case 1 -> {
        final var maxWidth = maxWidth(results.get(0));
        yield List.of(getOutputString(results.get(0), maxWidth + PADDING_SIZE));
      }
      default -> {
        final var total = totalResult(results);
        final var maxWidth = maxWidth(total);
        yield Stream.concat(results.stream(), Stream.of(total))
            .map(result -> getOutputString(result, maxWidth + PADDING_SIZE))
            .toList();
      }
    };
  }

  private static Result totalResult(List<Result> results) {
    final var map = new TreeMap<CountOption, Integer>();
    for (var countOption : COUNT_OPTION_ORDER) {
      final var optionalValue = totalField(results, countOption);
      optionalValue.ifPresent(value -> map.put(countOption, value));
    }
    return new CountResult(Optional.of(TOTAL_ROW_NAME), Optional.empty(), Map.copyOf(map));
  }

  private static OptionalInt totalField(List<Result> results, CountOption countOption) {
    final boolean hasValue =
        results.stream()
            .filter(CountResult.class::isInstance)
            .map(CountResult.class::cast)
            .allMatch(result -> result.resultMap().containsKey(countOption));
    return hasValue
        ? OptionalInt.of(
            results.stream()
                .filter(CountResult.class::isInstance)
                .map(CountResult.class::cast)
                .map(result -> result.resultMap().get(countOption))
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum())
        : OptionalInt.empty();
  }

  private static int maxWidth(Result result) {
    return switch (result) {
      case CountResult countResult -> presentValueStream(countResult)
          .map(String::valueOf)
          .mapToInt(String::length)
          .max()
          .orElse(0);
      case ErrorResult errorResult -> 0;
    };
  }

  private static String getOutputString(Result result, int width) {
    return switch (result) {
      case CountResult countResult -> getCountResultOutputString(countResult, width);
      case ErrorResult errorResult -> errorResult.toString();
    };
  }

  private static String getCountResultOutputString(CountResult countResult, int width) {
    final var format = "%" + width + "d";
    final var stringBuilder = new StringBuilder();
    presentValueStream(countResult)
        .map(value -> String.format(format, value))
        .forEach(stringBuilder::append);
    countResult.filename().ifPresent(filename -> stringBuilder.append(" ").append(filename));
    return stringBuilder.toString();
  }

  private static Stream<Integer> presentValueStream(CountResult result) {
    return COUNT_OPTION_ORDER.stream()
        .map(countOption -> Optional.ofNullable(result.resultMap().get(countOption)))
        .flatMap(Optional::stream);
  }
}
