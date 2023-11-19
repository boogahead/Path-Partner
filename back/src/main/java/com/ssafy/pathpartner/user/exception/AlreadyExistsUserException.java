package com.ssafy.pathpartner.user.exception;

public class AlreadyExistsUserException extends RuntimeException {

  public AlreadyExistsUserException(String msg) {
    super(msg);
  }

}