package com.example.movieapplication;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {
	private int id;
	private String title;
}
