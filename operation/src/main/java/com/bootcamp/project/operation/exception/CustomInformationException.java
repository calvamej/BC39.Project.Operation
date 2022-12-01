package com.bootcamp.project.operation.exception;

/**
 * Object that returns a message in case an exception occurs.
 */
public class CustomInformationException extends RuntimeException {

    private static final long serialVersionUID = 7307685192056731068L;

public CustomInformationException(String message) {
    super(message);
  }
}
