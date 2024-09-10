package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.TVCastCrew;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface TVshowCastCrewRepository extends JpaRepository<TVCastCrew, Integer> {

    @Query(value = "select s from TVCastCrew s")
    List<TVCastCrew> getAllTVCastCrews();



    @Query(value = "select s from TVCastCrew s where id= :id")
    TVCastCrew getTVCastCrewById(@Param("id")Integer id);



    @Transactional
    @Modifying
    @Query(value = "insert into tvcast_crew(profession, role, episode_id, person_id, season_id, show_id) values " +
            "(:prof, :role, :eid, :pid, :sid, :shid)", nativeQuery = true)
    void insertTvCastCrew(@Param("prof") String Profession, @Param("role") String role,
                          @Param("eid") Integer eid, @Param("pid") Integer pid,
                          @Param("sid") Integer sid, @Param("shid") Integer shid);



    @Transactional
    @Modifying
    @Query(value = "update tvcast_crew set profession= :prof, role= :role, episode_id= :eid, person_id= :pid," +
            " season_id= :sid, show_id= :shid where id= :id", nativeQuery = true)
    void updateTVCastCrew(@Param("prof") String Profession, @Param("role") String role,
                          @Param("eid") Integer eid, @Param("pid") Integer pid,
                          @Param("sid") Integer sid, @Param("shid") Integer shid,
                          @Param("id") Integer id);



    @Transactional
    @Modifying
    @Query(value = "delete from tvcast_crew where id= :id", nativeQuery = true)
    void deleteTVCastCrew(@Param("id") Integer id);
}
