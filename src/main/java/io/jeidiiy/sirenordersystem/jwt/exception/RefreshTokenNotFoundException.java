package io.jeidiiy.sirenordersystem.jwt.exception;

public class RefreshTokenNotFoundException extends RuntimeException {
  public RefreshTokenNotFoundException(String message) {
    super(message);
  }
}
