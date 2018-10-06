package com.stackroute.movieservice.services;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.EmptyDbException;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;//@Component("Impl2")

import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("Impl2")
public class MovieServiceImpl2 implements MovieService{
    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl2 (MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovie (Movie movie) throws MovieAlreadyExistsException {
        if(movieRepository.existsById(movie.getImdbID())){
            throw new MovieAlreadyExistsException("Movie already Exists 2");
        }
        Movie savedMovie = movieRepository.save(movie);
        if(savedMovie == null){
            throw new MovieAlreadyExistsException("Movie Already Exists 2");
        }
        return savedMovie;
    }

    @Override
    public List<Movie> getAllMovies () throws EmptyDbException {
        List<Movie> emptyList =(List)movieRepository.findAll();
        if(emptyList.size() == 0)  {
            throw new EmptyDbException("No entry in database 2");
        }
        return (List)movieRepository.findAll();
    }

    @Override
    public Movie deleteMovie (Movie movie) throws MovieNotFoundException {
        if(movieRepository.existsById(movie.getImdbID()) == false){
            throw new MovieNotFoundException("No such movie entry 2");
        }
        Movie deletedMovie = movie;
        movieRepository.delete(movie);
        return deletedMovie;
    }

    @Override
    public Movie updateMovie (Movie movie,String imdbId) throws MovieNotFoundException{
        if(movieRepository.findById(imdbId).isPresent() == false){
            throw new MovieNotFoundException("No such movie entry 2");
        }
        Movie updateMovie = movieRepository.findById(imdbId).get();
        Movie afterUpdates = (Movie)updateMovie;
        afterUpdates.setComments(movie.getComments());
//        afterUpdates.setPosterUrl(movie.getPosterUrl());
//        afterUpdates.setRating(movie.getRating());
        return movieRepository.save(afterUpdates);
    }

    @Override
    public Movie getByID(String imdbId) throws MovieNotFoundException {
        if(movieRepository.findById(imdbId).isPresent() == false){
            throw new MovieNotFoundException("No such movie entry 2");
        }
        Movie returnedMovie = movieRepository.findById(imdbId).get();
        return returnedMovie;
    }

    @Override
    public List<Movie> getMovieByTitle (String movieTitle) throws MovieNotFoundException {
        if(movieRepository.findByMovieTitle(movieTitle) == null) {
            throw new MovieNotFoundException("No such movie entry 2");
        }
        return (List)movieRepository.findByMovieTitle(movieTitle);
    }

}
