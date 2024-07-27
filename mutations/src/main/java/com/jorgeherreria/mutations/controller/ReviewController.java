package com.jorgeherreria.mutations.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorgeherreria.mutations.model.Review;
import com.jorgeherreria.mutations.repository.BookReposotory;
import com.jorgeherreria.mutations.repository.ReviewRepository;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {
  private final ReviewRepository reviewRepo;
  private final BookReposotory bookRepo;

  public ReviewController(ReviewRepository reviewRepo, BookReposotory bookRepo) {
    this.reviewRepo = reviewRepo;
    this.bookRepo = bookRepo;
  }

  @MutationMapping
  public List<Review> findAllReviews() {
    return reviewRepo.findAll();
  }
}
