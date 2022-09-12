package com.jmhreif.service4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class Service4Application {

	public static void main(String[] args) {
		SpringApplication.run(Service4Application.class, args);
	}

}

@RestController
@RequestMapping("/neo")
@AllArgsConstructor
class ReviewController {
	private final ReviewRepository reviewRepo;

	@GetMapping
	String liveCheck() { return "Service4 is up"; }

	@GetMapping("/reviews")
	Flux<Review> getReviews() { return reviewRepo.findFirst1000By(); }

	@GetMapping("/reviews/{book_id}")
	Flux<Review> getBookReviews(@PathVariable String book_id) { return reviewRepo.findReviewsByBook(book_id); }
}

interface ReviewRepository extends ReactiveCrudRepository<Review, Long> {

	Flux<Review> findFirst1000By();

	@Query("MATCH (r:Review)-[rel:WRITTEN_FOR]->(b:Book {book_id: $book_id}) RETURN r;")
	Flux<Review> findReviewsByBook(String book_id);
}

@Data
@Node
class Review {
	@Id
	@GeneratedValue
	private Long neoId;
	@NonNull
	private String review_id;

	private String book_id, review_text, date_added, date_updated, started_at, read_at;
	private Integer rating, n_comments, n_votes;
}