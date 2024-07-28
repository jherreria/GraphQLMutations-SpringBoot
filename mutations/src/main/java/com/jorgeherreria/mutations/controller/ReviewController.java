package com.jorgeherreria.mutations.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorgeherreria.mutations.model.Review;
import com.jorgeherreria.mutations.repository.ReviewRepository;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
  private final ReviewRepository reviewRepo;

  public ReviewController(ReviewRepository reviewRepo) {
    this.reviewRepo = reviewRepo;
  }

  @QueryMapping
  public List<Review> findAllReviews() {
    List<Review> list = reviewRepo.findAll();
    if (list == null)
      list = new ArrayList<>();
    return list;
  }

  @MutationMapping
  public String deleteReview(@Argument Integer id) {
    reviewRepo.deleteById(id);
    return "Done";
  }
}
