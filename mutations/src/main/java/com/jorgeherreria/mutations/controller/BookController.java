package com.jorgeherreria.mutations.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorgeherreria.mutations.exception.BookNotFoundException;
import com.jorgeherreria.mutations.model.Book;
import com.jorgeherreria.mutations.model.BookInput;
import com.jorgeherreria.mutations.model.BookInput4Update;
import com.jorgeherreria.mutations.model.Review;
import com.jorgeherreria.mutations.model.ReviewInput;
import com.jorgeherreria.mutations.repository.BookRepository;
import com.jorgeherreria.mutations.repository.ReviewRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final BookRepository bookRepository;
  private final ReviewRepository reviewRepository;

  public BookController(BookRepository bookRepository, ReviewRepository reviewRepository) {
    this.bookRepository = bookRepository;
    this.reviewRepository = reviewRepository;
  }

  @QueryMapping
  public List<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  @QueryMapping
  public Book findBookById(@Argument Integer id) {
    return bookRepository.findById(id).orElseThrow(BookNotFoundException::bookNotFound);
  }

  @MutationMapping
  public Book createBook(@Argument String title, @Argument Integer pages, @Argument String author) {
    Book book = new Book(title, pages, author);
    bookRepository.save(book);
    return book;
  }

  @MutationMapping
  public Book addBook(@Argument BookInput book) {
    return bookRepository.save(new Book(book));
  }

  @MutationMapping
  public List<Book> batchCreate(@Argument List<BookInput> books) {
    return bookRepository.saveAll(books.stream().map(Book::new).toList());
  }

  @MutationMapping
  public Book updateBook(@Argument BookInput4Update bookInput) {
    Book existingBook = bookRepository.findById(bookInput.id())
        .orElseThrow(BookNotFoundException::bookNotFound);
    existingBook.setAuthor(choose(existingBook.getAuthor(), bookInput.author()));
    existingBook.setPages(choose(existingBook.getPages(), bookInput.pages()));
    existingBook.setTitle(choose(existingBook.getTitle(), bookInput.title()));
    return bookRepository.save(existingBook);
  }

  @MutationMapping
  public Book addReview(@Argument(name = "review") ReviewInput reviewInput) {
    Book book = bookRepository.findById(reviewInput.book_id())
        .orElseThrow(BookNotFoundException::bookNotFound);
    Review review = reviewRepository.save(new Review(reviewInput.title(), reviewInput.comment()));
    book.getReviews().add(review);
    return bookRepository.save(book);
  }

  private String choose(String oldValue, String newValue) {
    return notEmpty(newValue) ? newValue : oldValue;
  }

  private Integer choose(Integer oldValue, Integer newValue) {
    return newValue != null ? newValue : oldValue;
  }

  public static boolean notEmpty(String str) {
    return str != null && !str.isEmpty();
  }
}
