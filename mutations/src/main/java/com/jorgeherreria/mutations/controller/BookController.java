package com.jorgeherreria.mutations.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorgeherreria.mutations.model.Book;
import com.jorgeherreria.mutations.model.BookInput;
import com.jorgeherreria.mutations.model.Review;
import com.jorgeherreria.mutations.model.ReviewInput;
import com.jorgeherreria.mutations.model.BookInput4Update;
import com.jorgeherreria.mutations.repository.BookReposotory;
import com.jorgeherreria.mutations.repository.ReviewRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {
  private final BookReposotory bookReposotory;
  private final ReviewRepository reviewRepo;

  public BookController(BookReposotory bookReposotory, ReviewRepository reviewRepo) {
    this.bookReposotory = bookReposotory;
    this.reviewRepo = reviewRepo;
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

  @MutationMapping
  public Book updateBook(@Argument BookInput4Update book) {
    Book existing = bookReposotory.getReferenceById(book.id());
    existing.setAuthor(chooser(existing.getAuthor(), book.author()));
    existing.setPages(chooser(existing.getPages(), book.pages()));
    existing.setTitle(chooser(existing.getTitle(), book.title()));
    return bookReposotory.save(existing);
  }

  @MutationMapping
  public Book addReview(@Argument(name = "review") ReviewInput iReview) {
    Book book = bookReposotory.getReferenceById(iReview.book_id());
    Review review = reviewRepo.save(new Review(iReview.title(), iReview.comment()));
    review = reviewRepo.save(review);
    book.getReviews().add(review);
    return bookReposotory.save(book);
  }

  String chooser(String oldValue, String newValue) {
    return (notEmpty(newValue)) ? newValue : oldValue;
  }

  Integer chooser(Integer oldValue, Integer newValue) {
    return (newValue != null) ? newValue : oldValue;
  }

  public static boolean notEmpty(String str) {
    return str != null && !str.isEmpty();
  }

};