package com.jmhreif.service2;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class Service2Application {
	@Value("${backend.hostname:localhost}")
	private String hostname;

	public static void main(String[] args) {
		SpringApplication.run(Service2Application.class, args);
	}

	@Bean
	WebClient client() {
		return WebClient.create("http://" + hostname + ":8081");
	}

}

@RestController
@RequestMapping("/goodreads")
@AllArgsConstructor
class BookController {
	private final WebClient client;

	@GetMapping
	String liveCheck() { return "Service2 is up"; }

	@GetMapping("/books")
	Flux<Book> getBooks() {
		return client.get()
				.uri("/db/books")
				.retrieve()
				.bodyToFlux(Book.class);
	}
}

@Data
class Book {
	private String mongoId;
	private String book_id;
	private String title, format, isbn, isbn13, edition_information;
}
