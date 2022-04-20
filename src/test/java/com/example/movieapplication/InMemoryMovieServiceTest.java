package com.example.movieapplication;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InMemoryMovieServiceTest {

	@Test
	void get() {
		// Arrange
		HashMap<Integer, Movie> storage = mock(HashMap.class);
		Movie movie = mock(Movie.class);
		when(storage.get(1)).thenReturn(movie);
		InMemoryMovieService inMemoryMovieService = new InMemoryMovieService(storage);
		// Act
		Movie result = inMemoryMovieService.get(1);
		// Assert
		assertThat(result).isEqualTo(movie);
		verify(storage).get(1);
	}
}