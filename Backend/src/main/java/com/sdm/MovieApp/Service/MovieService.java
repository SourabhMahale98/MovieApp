package com.sdm.MovieApp.Service;

import com.sdm.MovieApp.Model.Movie;
import com.sdm.MovieApp.DAO.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepo movieRepo;

    public List<Movie> allMovies(){
        return movieRepo.findAll();
    }


    public Optional<Movie> getMovieById(String id){
        return movieRepo.findByImdbId(id);
    }

    public ResponseEntity<String> addMovie(Movie movie) {
        try{
            movieRepo.save(movie);
            return new ResponseEntity<String>("Successfully add Movie",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("something wrong Not able to add Moive",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> removeMovieByImdbId(String id) {
        try{
            movieRepo.deleteByImdbId(id);
            return new ResponseEntity<String>("Successfully Deleted Movie by ID",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("something wrong Not able to add movie",HttpStatus.BAD_REQUEST);
    }
}
