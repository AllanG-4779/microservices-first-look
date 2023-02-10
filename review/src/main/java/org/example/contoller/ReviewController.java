package org.example.contoller;

import lombok.RequiredArgsConstructor;
import org.example.dto.MovieRatingDto;
import org.example.service.ReviewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rate")
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping("/new")
    public void rateMovie( @RequestBody MovieRatingDto movieRatingDto){
        reviewService.addReview(movieRatingDto);
    }
}
