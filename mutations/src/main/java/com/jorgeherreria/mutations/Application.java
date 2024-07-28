package com.jorgeherreria.mutations;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jorgeherreria.mutations.model.Book;
import com.jorgeherreria.mutations.model.Review;
import com.jorgeherreria.mutations.repository.BookRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BookRepository bookeRepository) {
		return args -> {
			Book reactiveString = new Book("Reactive String", 484, "John Long");
			Review review = new Review("Buen libro", "Si me ayudo an entender");
			reactiveString.setReviews(List.of(review));
			bookeRepository.save(reactiveString);
		};
	}
}
