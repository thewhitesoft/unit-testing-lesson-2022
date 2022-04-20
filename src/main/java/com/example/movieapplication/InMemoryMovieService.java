package com.example.movieapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
public class InMemoryMovieService implements MovieService {

	private final HashMap<Integer, Movie> storage;

	@Override
	public Movie get(int id) {
		return storage.get(id);
	}

	@Override
	public List<Movie> list() {
		return new ArrayList<>(storage.values());
	}

	@Override
	public Movie create(int id, String title, String comment) {
		Movie movie = new Movie(id, title);
		storage.put(id, movie);
		return movie;
	}

	@Override
	public Movie create(CreateMovieArgument argument) {
		return create(argument.getId(),
		              argument.getTitle(),
		              argument.getComment());
	}
}
