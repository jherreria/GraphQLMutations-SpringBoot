package com.jorgeherreria.mutations.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorgeherreria.mutations.model.Book;
import com.jorgeherreria.mutations.model.BookInput;
import com.jorgeherreria.mutations.repository.BookReposotory;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final BookReposotory bookReposotory;

  public BookController(BookReposotory bookReposotory) {
    this.bookReposotory = bookReposotory;
  }

  @QueryMapping
  public List<Book> findAllBooks() {
    return bookReposotory.findAll();
  }

  @MutationMapping
  public Book createBook(@Argument String title, @Argument Integer pages, @Argument String author) {
    Book book = new Book(title, pages, author);
    bookReposotory.save(book);
    return book;
  }

  @MutationMapping
  public Book addBook(@Argument BookInput book) {
    return bookReposotory.save(new Book(book));
  }

  @MutationMapping
  public List<Book> batchCreate(@Argument List<BookInput> books) {
    return bookReposotory.saveAll(books.stream().map(book -> new Book(book)).toList());
  }

};