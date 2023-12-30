package binikomi.wc.domain;

import java.util.ArrayList;
import java.util.List;

public record Options(List<CountOption> countTypes) {

  public boolean printBytes() {
    return countTypes.contains(CountOption.BYTES);
  }

  public boolean printChars() {
    return countTypes.contains(CountOption.CHARS);
  }

  public boolean printWords() {
    return countTypes.contains(CountOption.WORDS);
  }

  public boolean printLines() {
    return countTypes.contains(CountOption.LINES);
  }

  public static class Builder {

    private final ArrayList<CountOption> types;

    public Builder() {
      this.types = new ArrayList<>();
    }

    public Builder printBytes(boolean requested) {
      return updateTypes(requested, CountOption.BYTES);
    }

    public Builder printChars(boolean requested) {
      return updateTypes(requested, CountOption.CHARS);
    }

    public Builder printWords(boolean requested) {
      return updateTypes(requested, CountOption.WORDS);
    }

    public Builder printLines(boolean requested) {
      return updateTypes(requested, CountOption.LINES);
    }

    public Options build() {
      return new Options(List.copyOf(types));
    }

    private Builder updateTypes(boolean requested, CountOption type) {
      if (requested) {
        types.add(type);
      } else if (types.contains(type)) {
        types.remove(type);
      }
      return this;
    }
  }
}
