package com.sdm.MovieApp.Controller;

import com.sdm.MovieApp.Model.Movie;
import com.sdm.MovieApp.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/allMovies")
    public List<Movie> getAllMovies(){
        return movieService.allMovies();
    }

    @GetMapping("/{imdbId}")
    public  Optional<Movie> getMovieById(@PathVariable String imdbId){
        return movieService.getMovieById(imdbId);
    }

    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        System.out.println(movie);
        return movieService.addMovie(movie);
    }

    @DeleteMapping("/removeMovie/{imdbId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String imdbId){
        return movieService.removeMovieByImdbId(imdbId);
    }



}
