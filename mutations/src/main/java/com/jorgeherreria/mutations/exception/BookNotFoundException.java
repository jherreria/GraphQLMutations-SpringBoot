package com.jorgeherreria.mutations.exception;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(String message) {
    super(message);
  }

  public static BookNotFoundException bookNotFound() {
    return new BookNotFoundException("Book not found or access is not autorized");
  }
}
