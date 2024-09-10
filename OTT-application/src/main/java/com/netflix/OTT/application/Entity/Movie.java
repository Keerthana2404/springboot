package com.netflix.OTT.application.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "release_date")
    private LocalDate year;

    @Column(name = "description")
    private String description;

    @Column(name = "genre")
    private String genre;

    @Column(name = "certification")
    private String certification;

    @Column(name = "director")
    private String director;

    @Column(name = "producer")
    private String producer;

    @Column(name = "running_time")
    private int runningTime;

    @Column(name = "language")
    private String language;

    @Column(name = "rating")
    private float rating;

    @Column(name="created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date created_at;

    @OneToMany(mappedBy = "movie")
    @JsonManagedReference("movie-cast-crew")
    private List<MovieCastCrew> movieCastCrews;

    @ManyToMany(mappedBy = "favoriteMovies")
    @JsonIgnoreProperties("favmovies")
    @JsonBackReference("favmovies")
    private List<UserProfile> userProfiles=new ArrayList<>();

    @ManyToMany(mappedBy = "movies")
    @JsonBackReference("movies")
    private List<WatchList> watchLists = new ArrayList<>();
}
