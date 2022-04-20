package com.example.movieapplication;

import java.util.List;

public interface MovieService {
	Movie get(int id);

	List<Movie> list();

	Movie create(int id, String title, String comment);

	Movie create(CreateMovieArgument argument);
}
