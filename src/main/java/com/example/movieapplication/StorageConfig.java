package com.example.movieapplication;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

	@Bean
	public MovieService movieService(){
		HashMap<Integer, Movie> storage = new HashMap<>();
		storage.put(1, new Movie(1, "The Matrix"));
		storage.put(2, new Movie(2, "Shrek"));
		return new InMemoryMovieService(storage);
	}
}
