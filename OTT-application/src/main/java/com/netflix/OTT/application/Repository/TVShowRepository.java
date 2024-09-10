package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Repository
public interface TVShowRepository extends JpaRepository <TVShow, Integer>{


    //get all shows
    @Query(value = "select s from TVShow s")
    List<TVShow> getAllTvShows();


    //get show by id
    @Query(value = "select s from TVShow s where show_id= :id")
    TVShow getTvShowsById(@Param("id") Integer id);


    @Query(value = "select s from TVShow s where created_at> :created_at")
    List<TVShow> getLatestTVShows(@Param("created_at") Date created_at);


    //insert a show
    @Transactional
    @Modifying
    @Query(value = "insert into tvshow (description, genre, show_name, start_year) values " +
            "(:description, :genre, :showname, :startyear)", nativeQuery = true)
    void saveTVShow(@Param("description") String description, @Param("genre") String genre,
                    @Param("showname") String showname, @Param("startyear") Integer startyear);



    //update a tv show
    @Transactional
    @Modifying
    @Query(value = "update tvshow set description = :descr, genre= :genre, show_name= :showname," +
            "start_year= :year where show_id= :id", nativeQuery = true)
    public void updateShow(@Param("descr")String descr, @Param("genre") String genre,
                           @Param("showname") String showname, @Param("year") int year,
                           @Param("id") Integer id);


    //delete a tvshow
    @Modifying
    @Transactional
    @Query(value = "delete from tvshow where show_id= :id", nativeQuery = true)
    void deleteShowById(@Param("id") Integer id);
}
