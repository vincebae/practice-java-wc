package binikomi.wc.domain;

public enum ErrorCode {
  FILE_NOT_FOUND("No such file or directory"),
  DIRECTORY("Is a directory");

  private final String errorMessage;

  private ErrorCode(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String errorMessage() {
    return this.errorMessage;
  }
}
