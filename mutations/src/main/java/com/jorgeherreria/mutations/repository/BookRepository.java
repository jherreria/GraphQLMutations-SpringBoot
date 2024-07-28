package com.jorgeherreria.mutations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorgeherreria.mutations.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
