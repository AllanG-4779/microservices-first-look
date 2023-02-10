package org.example.repository;

import org.example.model.MovieRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieReviewRepository extends JpaRepository<MovieRating, Long> {

    Optional<MovieRating> findMovieRatingByMovieId(String aLong);
}
