package com.sdm.MovieApp.DAO;

import com.sdm.MovieApp.Model.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MovieRepo extends MongoRepository<Movie, ObjectId> {

    
    Optional<Movie> findByImdbId(String id);

    Optional<String> deleteByImdbId(String id);


//    @Query(value = "SELECT imdbId FROM Movie m where m.imdbId=:id ",nativeQuery = true)
//    String deleteByImdbID(Integer id);
}
