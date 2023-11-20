package com.ssafy.pathpartner.travelgroup.exception;

public class UnauthoriedGroupRequestException extends RuntimeException{
  public UnauthoriedGroupRequestException(String msg) {
    super(msg);
  }
}
