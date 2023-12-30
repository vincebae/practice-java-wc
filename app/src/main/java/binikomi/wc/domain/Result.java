package binikomi.wc.domain;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.TreeMap;

public record Result(
    Optional<String> filename, Optional<String> errorMessage, Map<CountOption, Integer> resultMap) {

  public OptionalInt bytes() {
    return getOptional(CountOption.BYTES);
  }

  public OptionalInt chars() {
    return getOptional(CountOption.CHARS);
  }

  public OptionalInt words() {
    return getOptional(CountOption.WORDS);
  }

  public OptionalInt lines() {
    return getOptional(CountOption.LINES);
  }

  public OptionalInt getOptional(CountOption countOption) {
    return resultMap.containsKey(countOption)
        ? OptionalInt.of(resultMap.get(countOption))
        : OptionalInt.empty();
  }

  public static class Builder {

    private final TreeMap<CountOption, Integer> map;
    private Optional<String> filename;
    private Optional<String> errorMessage;

    public Builder() {
      this.map = new TreeMap<>();
      this.filename = Optional.empty();
      this.errorMessage = Optional.empty();
    }

    public Builder filename(String filename) {
      this.filename = Optional.of(filename);
      return this;
    }

    public Builder errorMessage(String errorMessage) {
      this.errorMessage = Optional.of(errorMessage);
      return this;
    }

    public Builder bytes(int count) {
      map.put(CountOption.BYTES, count);
      return this;
    }

    public Builder chars(int count) {
      map.put(CountOption.CHARS, count);
      return this;
    }

    public Builder words(int count) {
      map.put(CountOption.WORDS, count);
      return this;
    }

    public Builder lines(int count) {
      map.put(CountOption.LINES, count);
      return this;
    }

    public Result build() {
      return new Result(filename, errorMessage, Map.copyOf(map));
    }
  }
}
