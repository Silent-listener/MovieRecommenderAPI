package com.stackroute.movieservice.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @ApiModelProperty(notes = "The movie ID", required = true)
    @Size(min = 1, message = "ID size should be more than 1")
    private String imdbID;
    @ApiModelProperty(notes = "The movie Title", required = true)
    @Size(min = 1, max= 200, message = "Movie title should have characters bw 1 to 200")
    private String movieTitle;
    @ApiModelProperty(notes = "The movie poster URL")
    private String posterUrl;
    @ApiModelProperty(notes = "The movie Ratings", required = true)
    @NotNull(message = "Ratings is required")
    private double rating;
    @ApiModelProperty(notes = "The year when movie was released", required = true)
    @NotNull(message = "Release year of the movie is required")
    private String yearOfRelease;
    @ApiModelProperty(notes = "Comments on the movie")
    @Size(min = 2, message = "Comments should have atleast 2 characters")
    private String comments;

    public Movie() {
    }

    public Movie(String imdbID, String movieTitle, String posterUrl, double rating, String yearOfRelease, String comments) {
        this.imdbID = imdbID;
        this.movieTitle = movieTitle;
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.yearOfRelease = yearOfRelease;
        this.comments = comments;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imdbID='" + imdbID + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", rating=" + rating +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
