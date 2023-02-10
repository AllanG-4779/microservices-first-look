package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.MovieDto;
import org.example.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @PostMapping("/add")
    ResponseEntity<MovieDto> postNewMovie(@RequestBody MovieDto movieDto){
      MovieDto movieReturned =  movieService.addMovie(movieDto);
      return ResponseEntity.status(201).body(movieReturned);
    }

    @GetMapping("/all")

    ResponseEntity<List<MovieDto>> getAllMovies (){
        List<MovieDto> movies = movieService.getMovies();

        return ResponseEntity.status(200).body(movies);
    }
    @GetMapping("/")
    ResponseEntity<String> getMovieByTitle(@RequestParam String title){
        String slug = movieService.getMovieSlug(title);
        return ResponseEntity.status(200).body(slug);
    }


}
