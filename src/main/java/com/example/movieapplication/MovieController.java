package com.example.movieapplication;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/list")
	public List<Movie> list() {
		return movieService.list();
	}

	@GetMapping("/{id}")
	public Movie get(@PathVariable("id") int id) {
		return movieService.get(id);
	}

	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Movie create(@RequestBody CreateMovieDto movieDto) {
		CreateMovieArgument argument = CreateMovieArgument.builder()
		                                               .id(movieDto.getId())
		                                               .comment(movieDto.getComment())
		                                               .title(movieDto.getTitle())
		                                               .build();
		return movieService.create(argument);
	}
}
