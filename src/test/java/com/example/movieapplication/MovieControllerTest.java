package com.example.movieapplication;

import java.util.Arrays;
import java.util.List;

import javax.lang.model.util.Types;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MovieControllerTest {

	public static final int THE_MATRIX_ID = 1;
	private static final int SHREK_ID = 2;

	@Test
	void get() {
		// Arrange
		MovieService movieService = mock(MovieService.class);
		MovieController movieController = new MovieController(movieService);
		Movie theMatrix = new Movie(THE_MATRIX_ID, "The Matrix");
		when(movieService.get(eq(THE_MATRIX_ID))).thenReturn(theMatrix);
		// Act
		Movie movie = movieController.get(THE_MATRIX_ID);
		// Assert
		verify(movieService).get(THE_MATRIX_ID);
		assertThat(movie).isSameAs(theMatrix);
	}

	@Test
	void list() {
		// Arrange
		MovieService movieService = mock(MovieService.class);
		MovieController movieController = new MovieController(movieService);
		Movie theMatrix = new Movie(THE_MATRIX_ID, "The Matrix");
		Movie shrek = new Movie(SHREK_ID, "Shrek");
		when(movieService.list()).thenReturn(Arrays.asList(theMatrix, shrek));
		// Act
		List<Movie> movies = movieController.list();
		// Assert
		assertThat(movies).contains(theMatrix, shrek);
		verify(movieService).list();
	}

	@Test
	void create() {
		// Arrange
		MovieService movieService = mock(MovieService.class);
		MovieController movieController = new MovieController(movieService);
		CreateMovieDto createDto = CreateMovieDto.builder()
		                                         .id(1)
		                                         .comment("test comment 123")
		                                         .title("The Matrix")
		                                         .build();
		// Act
		movieController.create(createDto);
		// Assert
		ArgumentCaptor<CreateMovieArgument> captor = ArgumentCaptor.forClass(CreateMovieArgument.class);
		verify(movieService).create(captor.capture());
		assertThat(captor.getValue()).extracting(CreateMovieArgument::getId,
		                                         CreateMovieArgument::getTitle)
		                             .contains(1, "The Matrix");
	}


}