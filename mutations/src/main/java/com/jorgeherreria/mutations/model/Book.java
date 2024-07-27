package com.jorgeherreria.mutations.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Book {
  @Id
  @GeneratedValue
  private Integer id;
  private String title;
  private Integer pages;
  private String author;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "book_id")
  private List<Review> reviews;

  public Book() {
  }

  public Book(String title, Integer pages, String author) {
    this.title = title;
    this.pages = pages;
    this.author = author;
  }

  public Book(BookInput book) {
    this(book.title(), book.pages(), book.author());
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    this.pages = pages;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  @Override
  public String toString() {
    return "Book [id=" + id + ", title=" + title + ", pages=" + pages + ", author=" + author + ", reviews=" + reviews
        + "]";
  }

}
