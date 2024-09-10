package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.MovieCastCrew;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface MovieCastCrewRepository extends JpaRepository<MovieCastCrew, Integer> {

    @Query(value = "select s from MovieCastCrew s")
    List<MovieCastCrew> getAllMovieCastCrew();

    @Query(value = "select s from MovieCastCrew s where id= :id")
    MovieCastCrew getMovieCastCrewById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into movie_cast_crew (profession, role, movie_id, person_id) values " +
            "(:profession, :role, :movieid, :personid)", nativeQuery = true)
    public void insertMovieCastCrew(@Param("profession") String profession, @Param("role") String role,
                                    @Param("movieid") Integer movieid, @Param("personid") Integer personid);


    @Transactional
    @Modifying
    @Query(value = "update movie_cast_crew set profession= :profession, role= :role, movie_id= :movieid, person_id= :personid where id= :id", nativeQuery = true)
    public void updateMovieCastCrew(@Param("profession") String profession, @Param("role") String role,
                                    @Param("movieid") Integer movieid, @Param("personid") Integer personid,
                                    @Param("id") Integer id);


    @Transactional
    @Modifying
    @Query(value = "delete from movie_cast_crew where id= :id", nativeQuery = true)
    public void deleteMovieCastCrew(@Param("id") Integer id);
}
