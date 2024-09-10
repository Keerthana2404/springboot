package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Repository
public interface WatchListRepository extends JpaRepository<WatchList, Integer> {

    @Query(value = "select s from WatchList s")
    List<WatchList> getAllWtchList();

    @Query(value = "select s from WatchList s where watchId= :id")
    WatchList getWatchListById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO watchlist (profile_id, status, last_watched, watched_time_stamp) VALUES " +
            "(:userProfileId, :status, :lastWatched, :wts)", nativeQuery = true)
    void insertWatchList(@Param("userProfileId") Integer userProfileId, @Param("status") String status,
                         @Param("lastWatched") LocalDateTime lastWatched, @Param("wts") String wts);

    @Transactional
    @Modifying
    @Query(value = "insert into watchlist_episodes(watch_id, episode_id) values(:watchid, :epiid)", nativeQuery = true)
    void insertwatchepisodes(@Param("watchid") Integer watchid, @Param("epiid") Integer epiid);

    @Query(value = "SELECT l.watchId FROM WatchList l ORDER BY l.watchId DESC LIMIT 1")
    Integer getWatchIdofLast();

    @Transactional
    @Modifying
    @Query(value = "insert into watchlist_seasons(watch_id, season_id) values(:watchid, :seasonid)", nativeQuery = true)
    void insertwatchseasons(@Param("watchid") Integer watchid, @Param("seasonid") Integer seasonid);

    @Transactional
    @Modifying
    @Query(value = "insert into watchlist_tvshows(watch_id, show_id) values(:watchid, :showid)", nativeQuery = true)
    void insertwatchshows(@Param("watchid") Integer watchid, @Param("showid") Integer showid);

    @Transactional
    @Modifying
    @Query(value = "insert into watchlist_movies(watch_id, movie_id) values(:watchid, :movieid)", nativeQuery = true)
    void insertwatchmovies(@Param("watchid") Integer watchid, @Param("movieid") Integer movieid);

    @Transactional
    @Modifying
    @Query(value = "delete from watchlist_episodes where watch_id= :id", nativeQuery = true)
    void deleteepisodes(@Param("id") Integer id);


    @Transactional
    @Modifying
    @Query(value = "delete from watchlist_seasons where watch_id= :id", nativeQuery = true)
    void deleteseasons(@Param("id") Integer id);


    @Transactional
    @Modifying
    @Query(value = "delete from watchlist_tvshows where watch_id= :id", nativeQuery = true)
    void deleteshows(@Param("id") Integer id);


    @Transactional
    @Modifying
    @Query(value = "delete from watchlist_movies where watch_id= :id", nativeQuery = true)
    void deletemovies(@Param("id") Integer id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE WatchList w SET w.status = :status,  " +
            " w.last_watched = :lastWatched, watched_time_stamp= :wts WHERE w.watch_id = :watchId", nativeQuery = true)
    void updateWatchList(@Param("watchId") Integer watchId, @Param("status") WatchList.WatchStatus status,
                         @Param("lastWatched") LocalDateTime lastWatched, @Param("wts") String wts);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM watchlist w WHERE w.watch_id = :watchId", nativeQuery = true)
    void deleteWatchList(@Param("watchId") Integer watchId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM watchlist w WHERE w.status = 'completed'", nativeQuery = true)
    void deleteCompletedWatchLists();


    @Query(value = "SELECT m.name AS name, " +
            "COALESCE(SUM(TIME_TO_SEC(w.watched_time_stamp)), 0) AS totalWatchTime, p.country " +
            "FROM watchlist w " +
            "JOIN watchlist_movies wm ON w.watch_id = wm.watch_id " +
            "JOIN movie m ON wm.movie_id = m.movie_id " +
            "JOIN userprofile p ON p.profile_id= w.profile_id " +
            "WHERE w.last_watched BETWEEN :startDate AND :endDate " +
            "AND (:language IS NULL OR :language = '' OR m.language = :language) " +
            "AND (p.country = :country OR :country IS NULL OR :country = '') " +
            "GROUP BY m.movie_id, p.country " +
            "ORDER BY totalWatchTime desc , p.country DESC LIMIT :limit",
            nativeQuery = true)
    List<Map<String, Object>> findTopMovies(@Param("language") String language, @Param("startDate") LocalDateTime startDate,
                                            @Param("country") String country,
                                            @Param("endDate") LocalDateTime endDate, @Param("limit") int limit);


    @Query(value = "SELECT tv.show_name AS name, " +
            "COALESCE(SUM(TIME_TO_SEC(w.watched_time_stamp)), 0) AS totalWatchTime, p.country  " +
            "FROM watchlist w " +
            "JOIN watchlist_tvshows wt ON w.watch_id = wt.watch_id " +
            "JOIN tvshow tv ON wt.show_id = tv.show_id " +
            "JOIN userprofile p ON p.profile_id= w.profile_id " +
            "WHERE w.last_watched BETWEEN :startDate AND :endDate " +
            "AND (:language IS NULL OR :language = '' OR tv.language = :language) " +
            "AND (p.country = :country OR :country IS NULL OR :country = '') " +
            "GROUP BY tv.show_id, p.country " +
            "ORDER BY totalWatchTime DESC , p.country DESC " +
            "LIMIT :limit",
            nativeQuery = true)
    List<Map<String, Object>> findTopTVShows(@Param("language") String language, @Param("startDate") LocalDateTime startDate,
                                             @Param("country") String country,
                                             @Param("endDate") LocalDateTime endDate, @Param("limit") int limit);


}