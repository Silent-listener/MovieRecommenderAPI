package com.stackroute.movieservice;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Command Line Runner Activated");

        Movie clrMovie = new Movie();
        clrMovie.setMovieTitle("12 Angry Men");
        clrMovie.setImdbID("3");
        clrMovie.setPosterUrl("#");
        clrMovie.setRating(9.9);
        clrMovie.setYearOfRelease("1950");
        clrMovie.setComments("Great");
        movieRepository.save(clrMovie);

        return;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        System.out.println("The App Startup has Initiated");

        Movie startupMovie = new Movie();
        startupMovie.setMovieTitle("Kimimonawa");
        startupMovie.setImdbID("1");
        startupMovie.setPosterUrl("#");
        startupMovie.setRating(9.6);
        startupMovie.setYearOfRelease("2016");
        startupMovie.setComments("Best");
        movieRepository.save(startupMovie);


        return;
    }

}

