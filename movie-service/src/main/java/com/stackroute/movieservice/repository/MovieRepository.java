package com.stackroute.movieservice.repository;
import com.stackroute.movieservice.domain.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {
    public List<Movie> findByMovieTitle(String movieTitle);
}
