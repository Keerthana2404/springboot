package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.Movie;
import com.netflix.OTT.application.Entity.TVShow;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {


    //Get all movies
    @Query(value = "select m from Movie m")
    public List<Movie> getAllMovies();

    @Query(value = "select s from Movie s where created_at> :created_at")
    List<Movie> getLatestMovies(@Param("created_at") Date created_at);

    @Query(value = "select s from Movie s where year> :year")
    List<Movie> getUpcomingMovies(@Param("year") LocalDate year);

    //Get Movie by id
    @Query(value = "select m from Movie m where m.movieId= :movieid")
    public Movie getMovieById(@Param("movieid") Integer id);


    //Insert a movie
    @Transactional
    @Modifying
    @Query(value = "insert into Movie (certification,description,director,genre,language,name,producer,rating,running_time,release_date)" +
            " values (:#{#m.certification}, :#{#m.description}, :#{#m.director}, :#{#m.genre}, " +
            ":#{#m.language}, :#{#m.name}, :#{#m.producer}, :#{#m.rating}, :#{#m.runningTime}, :#{#m.year})", nativeQuery = true)
     void saveMovies(@Param("m") Movie movie);

    //Update a Movie
    @Transactional
    @Modifying
    @Query(value = "UPDATE Movie u SET u.certification = :#{#m.certification}, u.description = :#{#m.description}, " +
            "u.director = :#{#m.director}, u.genre = :#{#m.genre}, u.language = :#{#m.language}," +
            "u.name = :#{#m.name}, u.producer = :#{#m.producer}, u.rating = :#{#m.rating}," +
            "u.running_time = :#{#m.runningTime}, u.release_date = :#{#m.year}" +
            " WHERE u.movie_id = :#{#m.MovieId}", nativeQuery = true)
     void updateMovie(@Param("m") Movie movie);


    //delete a movie by id
    @Modifying
    @Transactional
    @Query(value = "delete from Movie m  where m.movieId= :movieid")
    public void deleteMovie(@Param("movieid") Integer id);

}
