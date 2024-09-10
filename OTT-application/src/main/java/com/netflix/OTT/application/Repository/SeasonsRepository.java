package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.Seasons;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface SeasonsRepository extends JpaRepository<Seasons, Integer> {

    @Query(value = "select s from Seasons s")
    List<Seasons> getAllSeasons();

    @Query(value = "select s from Seasons s where season_id= :id")
    Seasons getSeasonsById(@Param("id") Integer id);




    @Transactional
    @Modifying
    @Query(value = "insert into season (director, producer, season_number, start_year, show_id) values(" +
            ":director, :producer, :seasonnumber, :year, :showid)", nativeQuery = true)

    void saveSeasons(@Param("director") String director, @Param("producer") String producer,
                        @Param("seasonnumber") Integer seasonnumber, @Param("year") Integer year,
                        @Param("showid") Integer id);



    @Transactional
    @Modifying
    @Query(value = "update season set director= :dir, producer= :prod, season_number= :sn," +
            "start_year= :sy, show_id= :sid where season_id= :id", nativeQuery = true)

    public void updateSeasons(@Param("dir") String dir, @Param("prod") String prod,
                              @Param("sn") Integer sn, @Param("sy") Integer sy,
                              @Param("sid") Integer sid, @Param("id") Integer id );

    @Transactional
    @Modifying
    @Query(value = "delete from season where season_id= :id", nativeQuery = true)

    void deleteSeason(@Param("id") Integer id);
}
