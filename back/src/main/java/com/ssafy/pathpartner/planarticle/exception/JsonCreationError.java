package com.ssafy.pathpartner.planarticle.exception;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonCreationError extends JsonProcessingException {

    protected JsonCreationError(String msg) {
        super(msg);
    }
}
