package com.stackroute.movieservice.controller;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.EmptyDbException;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;
import com.stackroute.movieservice.services.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value= "MovieApp",description = "Operations pertaining to online movie data")
public class MovieController {
    @Autowired
    @Qualifier("Impl2")
    private MovieService movieService;

    @ApiOperation(value = "Save a movie")
    @PostMapping("/movie")
    public ResponseEntity<?> saveMovie(@RequestBody @Valid Movie movie ) {
        ResponseEntity responseEntity;
        try {
            Movie storedMovie = movieService.saveMovie(movie);
            responseEntity = new ResponseEntity<Movie>(storedMovie, HttpStatus.CREATED);
        } catch (MovieAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @ApiOperation(value = "View a list of all movies in database",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping("/movie")
    public ResponseEntity<?> getAllUsers() {
        ResponseEntity responseEntity;
        try {
            List<Movie> movieList = movieService.getAllMovies();
            responseEntity = new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
        } catch (EmptyDbException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @ApiOperation(value = "Search a movie with Id")
    @GetMapping("/movieById/{imdbId}")
    public ResponseEntity<?> getById(@PathVariable @Valid String imdbId) {
        ResponseEntity responseEntity;
        try {
            Movie returnedMovie = movieService.getByID(imdbId);
            responseEntity = new ResponseEntity<Movie>(returnedMovie, HttpStatus.FOUND);
        } catch (MovieNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @ApiOperation(value = "Search a movie with title")
    @GetMapping("/movieBytitle/{movieTitle}")
    public ResponseEntity<?> getMovieByTitle(@PathVariable @Valid String movieTitle) {
        ResponseEntity responseEntity;
        try {
            List<Movie> returnedMovie = movieService.getMovieByTitle(movieTitle);
            responseEntity = new ResponseEntity<List<Movie>>(returnedMovie, HttpStatus.FOUND);
        } catch (MovieNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @ApiOperation(value = "Delete a movie")
    @DeleteMapping("/movie/{imdbId}")
    public ResponseEntity<?> deleteMovie(@PathVariable @Valid String imdbId) {
        ResponseEntity responseEntity;
        try {
            Movie returnedMovie = movieService.getByID(imdbId);
            movieService.deleteMovie(returnedMovie);
            responseEntity = new ResponseEntity<String>("Movie Deleted", HttpStatus.OK);
        }  catch (MovieNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @ApiOperation(value = "Update a movie")
    @PutMapping("/movie/{imdbId}")
    public ResponseEntity<?> updateMovie(@PathVariable @Valid String imdbId ,@RequestBody @Valid Movie movie) {
        ResponseEntity responseEntity;
        try {
            Movie updatedMovie = movieService.updateMovie(movie, imdbId);
            responseEntity = new ResponseEntity<Movie>(updatedMovie, HttpStatus.ACCEPTED);
        } catch (MovieNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
            ex.printStackTrace();
        }
        return responseEntity;
    }

}
