package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.CastCrew;
import org.apache.logging.log4j.util.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface CastCrewRepository extends JpaRepository<CastCrew, Integer> {

    //get all cast crew
    @Query(value = "select c from CastCrew c")
    List<CastCrew> getAllCastCrew();

    //get CastCrew By id
    @Query(value = "select c from CastCrew c where c.personId= :personid")
    CastCrew getCastCrewById(@Param("personid") Integer id);

    @Transactional
    @Modifying
    @Query(value = "insert into CastCrew(name, profession) values (:#{#c.name}, :#{#c.profession} )")
    void saveCastCrew(@Param("c") CastCrew castCrew);

    @Transactional
    @Modifying
    @Query(value = "update CastCrew set name = :#{#c.name}, profession= :#{#c.profession} where personId= :#{#c.personId}")
    public void updateCastCrew(@Param("c")CastCrew castcrew);

    @Transactional
    @Modifying
    @Query(value = "delete from CastCrew where personId= :personid")
    public void deleteCastCrew(@Param("personid") Integer id);

}
