package org.example.service;

import org.example.dto.MovieRatingDto;

public interface ReviewService {
    void updateMovieRating(String movieId, int stars);

    void addReview(MovieRatingDto movieRatingDto);
}
