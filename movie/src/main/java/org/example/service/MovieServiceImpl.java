package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.MovieDto;
import org.example.model.Movie;
import org.example.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;
    @Override
    public MovieDto addMovie(MovieDto movieDto) {
        Movie movie = Movie.builder()
                .genre(movieDto.getGenre())
                .title(movieDto.getTitle())
                .slug(generateSlug())
                .build();
        movieRepository.save(movie);


        return movieDto;
    }

    @Override
    public List<MovieDto> getMovies() {
      return movieRepository.findAll().stream().map(
               each->MovieDto.builder()
                       .genre(each.getGenre())
                       .title(each.getTitle())
                       .build()
       ).toList();
    }

    @Override
    public String generateSlug() {
      return UUID.randomUUID().toString();
    }

    @Override
    public String getMovieSlug(String title) {
        log.error("We are receiving {}", title);
        Optional<Movie> movie = movieRepository.findByTitle(title);

        if(movie.isPresent()){
            return movie.get().getSlug();
        }else{
            throw new RuntimeException("Movie not found with that ID");
        }
    }
}
