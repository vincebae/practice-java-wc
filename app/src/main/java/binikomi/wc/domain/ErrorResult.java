package binikomi.wc.domain;

public record ErrorResult(String filename, ErrorCode errorCode) implements Result {
  @Override
  public String toString() {
    return String.format("%s: %s", filename, errorCode.errorMessage());
  }
}
