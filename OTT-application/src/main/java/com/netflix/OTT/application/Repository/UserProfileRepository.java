package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.Movie;
import com.netflix.OTT.application.Entity.TVShow;
import com.netflix.OTT.application.Entity.User;
import com.netflix.OTT.application.Entity.UserProfile;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    @Query(value = "select p from UserProfile p")
    public List<UserProfile> getAllUserProfiles();



    @Query(value = "SELECT u FROM UserProfile u where u.profile_id=:id")
    public UserProfile getUserProfileById(@Param ("id") Integer id);



    @Query(value = "SELECT s.show_name FROM TVShow s INNER JOIN user_fav_shows ufs ON s.show_id = ufs.show_id WHERE ufs.profile_id = :profileId", nativeQuery = true)
    public List<String> findFavoriteShowsByProfileId(@Param("profileId") Integer profileId);



    @Query(value = "SELECT s.name FROM Movie s INNER JOIN user_fav_movies ufs ON s.movie_id = ufs.movie_id WHERE ufs.profile_id = :profileId", nativeQuery = true)
    public List<String> findFavoriteMoviesByProfileId(@Param("profileId") Integer profileId);



    // Custom query for inserting a UserProfile
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO userprofile (name, age, role, pref_lang, user_id, country) VALUES (:name, :age, :type, " +
            ":prefLang, :userId, :country)", nativeQuery = true)
    void insertUserProfile(@Param("name") String name, @Param("age") int age, @Param("type") String type,
                           @Param("prefLang") String prefLang, @Param("userId") int userId,
                            @Param("country") String country);




    // Custom query for updating a UserProfile
    @Modifying
    @Transactional
    @Query(value = "UPDATE userprofile SET age = :#{#profile.age}, name = :#{#profile.name}, " +
            "pref_lang = :#{#profile.pref_lang}, role = :#{#profile.type.name()}, " +
            "country = :#{#profile.country}" +
            "WHERE profile_id = :#{#profile.profile_id}",
            nativeQuery = true)
    void updateUserProfile(@Param("profile") UserProfile profile);


    // Custom query for deleting a UserProfile
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM userprofile WHERE profile_id = :profileId", nativeQuery = true)
    void deleteUserProfile(@Param("profileId") Integer profileId);




    // Custom query to insert favorite movies for a UserProfile
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_fav_movies (profile_id, movie_id) VALUES (:profileId, :movieId)", nativeQuery = true)
    void insertFavoriteMovie(@Param("profileId") Integer profileId, @Param("movieId") Integer movieId);




    // Custom query to insert favorite shows for a UserProfile
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_fav_shows (profile_id, show_id) VALUES (:profileId, :showId)", nativeQuery = true)
    void insertFavoriteShow(@Param("profileId") Integer profileId, @Param("showId") Integer showId);




    // Custom query to delete all favorite movies for a UserProfile
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_fav_movies WHERE profile_id = :profileId", nativeQuery = true)
    void deleteAllFavoriteMovies(@Param("profileId") Integer profileId);




    // Custom query to delete all favorite shows for a UserProfile
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_fav_shows WHERE profile_id = :profileId", nativeQuery = true)
    void deleteAllFavoriteShows(@Param("profileId") Integer profileId);
}