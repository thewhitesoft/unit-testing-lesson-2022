package com.example.movieapplication;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
class MovieControllerIntegrationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void getList() {
		// Arrange
		// Act
		List<Movie> movies = webTestClient.get()
		                                  .uri("/movies/list")
		                                  .exchange()
		                                  .expectStatus()
		                                  .isOk()
		                                  .expectBodyList(Movie.class)
		                                  .returnResult()
		                                  .getResponseBody();
		// Assert
		assertThat(movies).contains(new Movie(2, "Shrek"),
		                            new Movie(1, "The Matrix"));
	}

	@Test
	void get() {
		// Arrange
		// Act
		Movie movie = webTestClient.get()
		                           .uri("/movies/{id}", 2)
		                           .exchange()
		                           .expectBody(Movie.class)
		                           .returnResult()
		                           .getResponseBody();
		// Assert
		assertThat(movie).isEqualTo(new Movie(2, "Shrek"));
	}

	@Test
	void create() {
		// Arrange
		// Act
		Movie createdMovie = webTestClient.post()
		                                  .uri("/movies/create")
		                                  .bodyValue(new CreateMovieDto(3, "Terminator", "test"))
		                                  .exchange()
		                                  .expectStatus()
		                                  .isCreated()
		                                  .expectBody(Movie.class)
		                                  .returnResult()
		                                  .getResponseBody();
		// Assert
		assertThat(createdMovie).isEqualTo(new Movie(3, "Terminator"));
	}
}