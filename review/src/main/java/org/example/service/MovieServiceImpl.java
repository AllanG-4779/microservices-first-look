package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.MovieRatingDto;
import org.example.model.MovieRating;
import org.example.model.Review;
import org.example.repository.MovieReviewRepository;
import org.example.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements ReviewService {
    private final MovieReviewRepository movieReviewRepository;
    private final ReviewRepository reviewRepository;
 @Autowired
    private final WebClient.Builder webClientBuilder;

    @Override
    @Transactional
    public void updateMovieRating(String movieId, int stars) {
        Optional<MovieRating> currentRating = movieReviewRepository
                .findMovieRatingByMovieId(movieId);

        if(currentRating.isPresent())
        {
         MovieRating updatedRating = currentRating.get();
         updatedRating.setReviews(updatedRating.getReviews()+1);
         updatedRating.setRating((updatedRating.getRating()+stars)/2);
         movieReviewRepository.save(updatedRating);
        }
        else
        {
            MovieRating newRating = MovieRating.builder()
                    .rating(stars)
                    .reviews(1L)
                    .movieId(movieId)
                    .build();
            movieReviewRepository.save(newRating);
        }
    }

    @Override
    @Transactional
    public void addReview(MovieRatingDto movieRatingDto) {
//        Webclient will be used here
//        Check that the movie exists
        log.info("The ID of we are finding is {}", movieRatingDto.getTitle());
 String movieId = webClientBuilder.build()
         .get()

         .uri(String.format("http://movie-service/api/v1/movies/?title=%s", movieRatingDto.getTitle()))
         .retrieve()
         .bodyToMono(String.class)
         .block();

        Review review = Review.builder()
                .reviewText(movieRatingDto.getDescription())
                .userId(movieRatingDto.getUserId())
                .movieId(movieId)
                .stars(movieRatingDto.getStars())
                .build();
        reviewRepository.save(review);

      updateMovieRating(movieId, movieRatingDto.getStars());

    }

}
