package com.stackroute.movieservice.services;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.EmptyDbException;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;
    public List<Movie> getAllMovies() throws EmptyDbException;
    public Movie updateMovie(Movie movie, String imdbId) throws MovieNotFoundException;
    public List<Movie> getMovieByTitle(String movieTitle) throws MovieNotFoundException;
    public Movie getByID(String imdbId) throws MovieNotFoundException;
    public Movie deleteMovie(Movie movie) throws MovieNotFoundException;

}
