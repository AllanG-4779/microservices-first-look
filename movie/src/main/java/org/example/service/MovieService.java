package org.example.service;

import org.example.dto.MovieDto;

import java.util.List;

public interface MovieService {
//    Add a movie
    MovieDto addMovie(MovieDto movieDto);

    List<MovieDto> getMovies();

    String generateSlug();

    String getMovieSlug(String title);
}
