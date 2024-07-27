package com.jorgeherreria.mutations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorgeherreria.mutations.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
