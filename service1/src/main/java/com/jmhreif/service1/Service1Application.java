package com.jmhreif.service1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class Service1Application {

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}
}

@RestController
@RequestMapping("/db")
@AllArgsConstructor
class BookController {
	private final BookRepository bookRepository;

	@GetMapping
	String liveCheck() { return "Service1 is up"; }

	@GetMapping("/books")
	Flux<Book> getBooks() { return bookRepository.findAll(); }

	@GetMapping("/book/{mongoId}")
	Mono<Book> getBook(@PathVariable String mongoId) { return bookRepository.findById(mongoId); }
}

interface BookRepository extends ReactiveCrudRepository<Book, String> {
}

@Data
@Document
class Book {
	@Id
	private String mongoId;
	@NonNull
	private String book_id;

	private String title, format, isbn, isbn13, edition_information;
}