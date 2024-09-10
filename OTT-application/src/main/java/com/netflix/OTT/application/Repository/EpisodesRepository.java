package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.Episodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface EpisodesRepository extends JpaRepository<Episodes, Integer> {

    @Query(value = "select e from Episodes e")
    List<Episodes> getAllEpisodes();


    @Query(value = "select s from Episodes s where episode_id= :id")
    Episodes getEpisodeById(@Param("id") Integer id);


    @Transactional
    @Modifying
    @Query(value = "insert into episodes (descriptions, episode_name, rating, runningtime, season_id ) values(" +
            ":descrp, :ename, :rating, :runtime, :sid)", nativeQuery = true)
    void saveEpisode(@Param("descrp") String descrp, @Param("ename") String ename,
                     @Param("rating") float rating, @Param("runtime") Integer runtime,
                     @Param("sid") Integer sid);


    @Transactional
    @Modifying
    @Query(value = "update episodes set descriptions= :d, episode_name= :e, rating= :r, runningtime= :rt, season_id = :sid " +
            "where episode_id= :id", nativeQuery = true)
    void updateEpisode(@Param("d") String descrp, @Param("e") String ename,
                       @Param("r") float r, @Param("rt") Integer rt, @Param("sid") Integer side,
                       @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "delete from episodes where episode_id= :id", nativeQuery = true)
    void deleteEpisode(@Param("id") Integer id);
}
