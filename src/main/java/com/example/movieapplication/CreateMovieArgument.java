package com.example.movieapplication;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMovieArgument {
	private int id;
	private String title;
	private String comment;
}
